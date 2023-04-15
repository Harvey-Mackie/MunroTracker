package com.munro.api.repository;

import com.munro.api.model.entities.UserEntity;
import com.munro.api.model.entities.UserFollowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowersRepository extends JpaRepository<UserFollowersEntity, Long> {
    List<UserFollowersEntity> findByFromAndTo(UserEntity from, UserEntity to);
}
