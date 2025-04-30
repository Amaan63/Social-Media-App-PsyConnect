package com.asquare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Story;
import com.asquare.models.User;
import com.asquare.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService {

  @Autowired
  private StoryRepository storyRepository;

  @Autowired
  private UserService userService;

  @Override
  public Story createStory(Story story, User user) {
    if (story.getCaption() == null || story.getImage() == null) {
      throw new IllegalArgumentException("Caption and image cannot be null.");
    }

    Story createdStory = new Story();
    createdStory.setCaption(story.getCaption());
    createdStory.setImage(story.getImage());
    createdStory.setUser(user);
    createdStory.setCreatedAt(LocalDateTime.now());
    return storyRepository.save(createdStory);
  }

  @Override
  public List<Story> findStoryByUserId(Integer userId) throws Exception {
    User user = userService.findUserById(userId);
    if (user == null) {
      throw new Exception("User not found with id: " + userId);
    }

    List<Story> stories = storyRepository.findByUserId(user.getId());
    if (stories == null || stories.isEmpty()) {
      throw new Exception("No stories found for user with id: " + userId);
    }

    return stories;
  }
}
