package com.asquare.service;

import java.util.List;

import com.asquare.models.Reels;
import com.asquare.models.User;

public interface ReelsService {

  public Reels createReel(Reels reel, User user);

  public List<Reels> findAllReels();

  public List<Reels> findUsersReel(Integer userId);
}
