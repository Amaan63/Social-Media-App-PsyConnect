package com.asquare.service.Reels;

import java.util.List;

import com.asquare.exceptions.ReelsException;
import com.asquare.models.Reels;
import com.asquare.models.User;

public interface ReelsService {

  public Reels createReel(Reels reel, User user) throws ReelsException, Exception;

  public List<Reels> findAllReels() throws ReelsException, Exception;

  public List<Reels> findUsersReel(Integer userId) throws ReelsException, Exception;
}
