package com.munro.api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
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
}
