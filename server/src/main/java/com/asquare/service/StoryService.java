package com.asquare.service;

import java.util.List;

import com.asquare.models.Story;

public interface StoryService {

  public Story createStory(Story story, Integer userId);

  public List<Story> findStoryByUserId(Integer userId);

}
