package com.munro.api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserFollowersEntity {
    @Id
    @SequenceGenerator(
            name = "user_followers_sequence",
            sequenceName = "user_followers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_followers_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_follow_from_id", referencedColumnName = "id")
    private UserEntity from;

    @ManyToOne
    @JoinColumn(name = "user_follow_to_id", referencedColumnName = "id")
    private UserEntity to;

    public UserFollowersEntity(UserEntity from, UserEntity to) {
        this.from = from;
        this.to = to;
    }
}
