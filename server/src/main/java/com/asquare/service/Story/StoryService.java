package com.asquare.service.Story;

import java.util.List;

import com.asquare.models.Story;
import com.asquare.models.User;

public interface StoryService {

  public Story createStory(Story story, User user) throws Exception;

  public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
