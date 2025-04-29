package com.asquare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Reels;
import com.asquare.models.User;
import com.asquare.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService {

  @Autowired
  private ReelsRepository reelsRepository;

  @Autowired
  private UserService userService;

  @Override
  public Reels createReel(Reels reel, User user) throws Exception {
    try {
      if (reel == null || user == null) {
        throw new Exception("Reel or User cannot be null");
      }

      Reels createReel = new Reels();
      createReel.setTitle(reel.getTitle());
      createReel.setUser(user);
      createReel.setVideo(reel.getVideo());
      createReel.setCreatedAt(LocalDateTime.now());

      return reelsRepository.save(createReel);
    } catch (Exception e) {
      throw new Exception("Failed to create reel: " + e.getMessage(), e);
    }
  }

  @Override
  public List<Reels> findAllReels() throws Exception {
    try {
      List<Reels> reels = reelsRepository.findAll();
      if (reels == null || reels.isEmpty()) {
        throw new Exception("No reels found");
      }
      return reels;
    } catch (Exception e) {
      throw new Exception("Failed to fetch all reels: " + e.getMessage(), e);
    }
  }

  @Override
  public List<Reels> findUsersReel(Integer userId) throws Exception {
    try {
      if (userId == null) {
        throw new Exception("User ID cannot be null");
      }

      User user = userService.findUserById(userId);
      if (user == null) {
        throw new Exception("User not found with ID: " + userId);
      }

      List<Reels> reels = reelsRepository.findByUserId(userId);
      if (reels == null || reels.isEmpty()) {
        throw new Exception("No reels found for user ID: " + userId);
      }

      return reels;
    } catch (Exception e) {
      throw new Exception("Failed to fetch user's reels: " + e.getMessage(), e);
    }
  }
}
