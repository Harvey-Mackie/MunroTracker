package com.munro.api.controller;


import com.munro.api.model.domain.UserEntity;
import com.munro.api.model.dto.UserDetailsDto;
import com.munro.api.repository.UserRepository;
import com.munro.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping()
    public boolean create(
            @RequestBody UserDetailsDto user
    ){
        var savedUser = userRepository.save(new UserEntity(user.getName(), user.getEmail()));
        return savedUser != null;
    }

    @PutMapping("{userId}/actions/follow")
    public void followUser(
            @RequestParam("currentUserId") Long currentUserId,
            @PathVariable("userId") Long userToFollowId
    ) {
        userService.followUser(currentUserId, userToFollowId);
    }

    @PutMapping("{userId}/actions/unfollow")
    public void unfollowUser(
            @RequestParam("currentUserId") Long currentUserId,
            @PathVariable("userId") Long userToUnfollowId
    ){
        userService.unfollowUser(currentUserId, userToUnfollowId);
    }
}
