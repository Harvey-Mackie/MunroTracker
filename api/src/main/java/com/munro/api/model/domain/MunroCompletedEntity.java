package com.munro.api.model.domain;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class MunroCompletedEntity {
    public MunroCompletedEntity() {
    }

    public MunroCompletedEntity(UserEntity user, Long munro_id, LocalDate date) {
        this.user = user;
        this.munro_id = munro_id;
        this.date = date;
    }

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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    private Long munro_id;
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMunro_id() {
        return munro_id;
    }

    public void setMunro_id(Long munro_id) {
        this.munro_id = munro_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
