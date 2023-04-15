package com.munro.api.model.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class MunroCompletedEntity {
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

    @OneToMany(mappedBy = "munroCompleted", fetch = FetchType.EAGER)
    private List<MunroCompletedCommentEntity> munroCompletedCommentEntities;

    private LocalDate date;

}
