package com.asquare.service.Story;

import java.util.List;

import com.asquare.exceptions.StoryException;
import com.asquare.exceptions.UserException;
import com.asquare.models.Story;
import com.asquare.models.User;

public interface StoryService {

  public Story createStory(Story story, User user) throws StoryException;

  public List<Story> findStoryByUserId(Integer userId) throws StoryException, UserException;

}
