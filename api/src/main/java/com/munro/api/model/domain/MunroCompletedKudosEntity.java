package com.munro.api.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class MunroCompletedKudosEntity {
    @Id
    @SequenceGenerator(
            name = "munro_completed_sequence",
            sequenceName = "munro_completed_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "munro_completed_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "munro_completed_id", referencedColumnName = "id")
    private MunroCompletedEntity munroCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    
    private LocalDateTime date;

    public MunroCompletedKudosEntity(MunroCompletedEntity munroCompleted, UserEntity user, LocalDateTime date) {
        this.munroCompleted = munroCompleted;
        this.user = user;
        this.date = date;
    }

    public MunroCompletedKudosEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MunroCompletedEntity getMunroCompleted() {
        return munroCompleted;
    }

    public void setMunroCompleted(MunroCompletedEntity munroCompleted) {
        this.munroCompleted = munroCompleted;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
