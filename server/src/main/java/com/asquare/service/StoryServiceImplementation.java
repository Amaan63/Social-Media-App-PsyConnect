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
    Story createdStory = new Story();
    createdStory.setCaption(story.getCaption());
    createdStory.setImage(story.getImage());
    createdStory.setUser(user);
    createdStory.setCreatedAt(LocalDateTime.now());
    return storyRepository.save(createdStory);
  }
}
