package com.munro.api.model.domain;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class MunroCompletedEntity {
    public MunroCompletedEntity() {
    }

    public MunroCompletedEntity(UserEntity user, MunroEntity munro, LocalDate date) {
        this.user = user;
        this.munro = munro;
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

    @ManyToOne
    @JoinColumn(name = "munro_id", referencedColumnName = "id")
    private MunroEntity munro;

    @OneToMany(mappedBy = "munroCompleted", fetch = FetchType.EAGER)
    private List<MunroCompletedKudosEntity> munroCompletedKudosEntities;

    public List<MunroCompletedCommentEntity> getMunroCompletedCommentEntities() {
        return munroCompletedCommentEntities;
    }

    public void setMunroCompletedCommentEntities(List<MunroCompletedCommentEntity> munroCompletedCommentEntities) {
        this.munroCompletedCommentEntities = munroCompletedCommentEntities;
    }

    @OneToMany(mappedBy = "munroCompleted", fetch = FetchType.EAGER)
    private List<MunroCompletedCommentEntity> munroCompletedCommentEntities;

    public List<MunroCompletedKudosEntity> getMunroCompletedKudosEntities() {
        return munroCompletedKudosEntities;
    }

    public void setMunroCompletedKudosEntities(List<MunroCompletedKudosEntity> munroCompletedKudosEntities) {
        this.munroCompletedKudosEntities = munroCompletedKudosEntities;
    }

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MunroEntity getMunro() {
        return munro;
    }

    public void setMunro(MunroEntity munro) {
        this.munro = munro;
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
