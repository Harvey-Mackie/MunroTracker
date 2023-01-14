package com.munro.api.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
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

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MunroCompletedEntity> getMunroCompletedEntities() {
        return munroCompletedEntities;
    }

    public void setMunroCompletedEntities(List<MunroCompletedEntity> munroCompletedEntities) {
        this.munroCompletedEntities = munroCompletedEntities;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<MunroCompletedEntity> munroCompletedEntities;
}
