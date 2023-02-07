package com.munro.api.model.dto;

import java.time.LocalDateTime;

public class MunroCompletedKudosDto {
    private Long munroCompletedId;
    private String userName;
    private Long userId;
    private LocalDateTime date;

    public MunroCompletedKudosDto() {
    }

    public MunroCompletedKudosDto(Long munroCompletedId, String userName, Long userId, LocalDateTime date) {
        this.munroCompletedId = munroCompletedId;
        this.userName = userName;
        this.userId = userId;
        this.date = date;
    }

    public Long getMunroCompletedId() {
        return munroCompletedId;
    }

    public void setMunroCompletedId(Long munroCompletedId) {
        this.munroCompletedId = munroCompletedId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
