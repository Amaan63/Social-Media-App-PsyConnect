package com.asquare.service.Reels;

import java.util.List;

import com.asquare.models.Reels;
import com.asquare.models.User;

public interface ReelsService {

  public Reels createReel(Reels reel, User user) throws Exception;

  public List<Reels> findAllReels() throws Exception;

  public List<Reels> findUsersReel(Integer userId) throws Exception;
}
