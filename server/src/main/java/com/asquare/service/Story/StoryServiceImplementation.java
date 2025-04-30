package com.asquare.service.Story;

import java.time.LocalDateTime;
import java.util.List;

import com.asquare.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.exceptions.StoryException;
import com.asquare.exceptions.UserException;
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
  public Story createStory(Story story, User user) throws StoryException {
    if (story.getCaption() == null || story.getImage() == null) {
      throw new StoryException("Caption and image cannot be null.");
    } else if (story.getImage().isEmpty()) {
      throw new StoryException("Image Cannot Be null");
    } else if (story.getCaption().isEmpty()) {
      throw new StoryException("Caption Cannot be Null");
    }

    Story createdStory = new Story();
    createdStory.setCaption(story.getCaption());
    createdStory.setImage(story.getImage());
    createdStory.setUser(user);
    createdStory.setCreatedAt(LocalDateTime.now());
    return storyRepository.save(createdStory);
  }

  @Override
  public List<Story> findStoryByUserId(Integer userId) throws StoryException, UserException {
    User user = userService.findUserById(userId);
    if (user == null) {
      throw new StoryException("User not found with id: " + userId);
    }

    List<Story> stories = storyRepository.findByUserId(user.getId());
    if (stories == null || stories.isEmpty()) {
      throw new StoryException("No stories found for user with id: " + userId);
    }

    return stories;
  }
}
