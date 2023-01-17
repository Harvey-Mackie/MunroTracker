package com.munro.api.service;

import com.munro.api.model.domain.UserEntity;
import com.munro.api.model.domain.UserFollowersEntity;
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
        logger.info("User "+ currentUserId + " attempting to start following user " + userToFollowId);

        var user = userRepository.findById(currentUserId).get();
        var userToFollow = userRepository.findById(userToFollowId).get();

        var userFollower = new UserFollowersEntity(user, userToFollow);
        userFollowersRepository.save(userFollower);

        logger.info("User "+ currentUserId + " is now following user " + userToFollowId);
    }

    public void unfollowUser(Long currentUserId, Long userToUnfollowId){
        logger.info("User "+ currentUserId + " attempting to start unfollow user " + userToUnfollowId);

        var user = userRepository.findById(currentUserId).get();
        var userToUnfollow = userRepository.findById(userToUnfollowId).get();

        var targetUsers = userFollowersRepository.findByFromAndTo( user, userToUnfollow);

        targetUsers.forEach( (target) -> {
            userFollowersRepository.delete(target);
        });

        logger.info("User "+ currentUserId + " is now unfollowing user " + userToUnfollowId);
    }

    public UserEntity userDetails(Long currentUserId){
        var user = userRepository.findById(currentUserId);

        if(!user.isPresent()){
            throw new RuntimeException("User does not exist.");
        }

        return user.get();
    }
}
