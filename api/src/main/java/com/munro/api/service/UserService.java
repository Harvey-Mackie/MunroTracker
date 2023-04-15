package com.munro.api.service;

import com.munro.api.model.entities.UserFollowersEntity;
import com.munro.api.repository.UserFollowersRepository;
import com.munro.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserFollowersRepository userFollowersRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserFollowersRepository userFollowersRepository, UserRepository userRepository) {
        this.userFollowersRepository = userFollowersRepository;
        this.userRepository = userRepository;
    }

    public void followUser(Long currentUserId, Long userToFollowId){
        logger.info("User {} attempting to start following user {}.", currentUserId, userToFollowId);

        var user = userRepository.findById(currentUserId).get();
        var userToFollow = userRepository.findById(userToFollowId).get();

        var userFollower = new UserFollowersEntity(user, userToFollow);
        userFollowersRepository.save(userFollower);

        logger.info("User {} is now following user {}.", currentUserId, userToFollowId);
    }

    public void unfollowUser(Long currentUserId, Long userToUnfollowId){
        logger.info("User {} attempting to start unfollow user {}.", currentUserId, userToUnfollowId);

        var user = userRepository.findById(currentUserId).get();
        var userToUnfollow = userRepository.findById(userToUnfollowId).get();

        var targetUsers = userFollowersRepository.findByFromAndTo( user, userToUnfollow);

        targetUsers.forEach( (target) -> {
            userFollowersRepository.delete(target);
        });

        logger.info("User {} is now unfollowing user {}.", currentUserId, userToUnfollowId);
    }
}
