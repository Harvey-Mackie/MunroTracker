package com.munro.api.model.domain;

import jakarta.persistence.*;


@Table
@Entity
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

    public UserFollowersEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getFrom() {
        return from;
    }

    public void setFrom(UserEntity from) {
        this.from = from;
    }

    public UserEntity getTo() {
        return to;
    }

    public void setTo(UserEntity to) {
        this.to = to;
    }
}
