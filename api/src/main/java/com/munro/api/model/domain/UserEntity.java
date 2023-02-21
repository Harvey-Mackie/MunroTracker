package com.munro.api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<MunroCompletedEntity> munroCompletedEntities;

    @OneToMany(mappedBy = "from", fetch = FetchType.EAGER)
    private List<UserFollowersEntity> userFollowing;

    @OneToMany(mappedBy = "to", fetch = FetchType.EAGER)
    private List<UserFollowersEntity> userFollowers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MunroCompletedKudosEntity> userKudos;
}
