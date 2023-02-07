package com.munro.api.model.dto;

import java.time.LocalDateTime;

public class MunroCompletedCommentDto {
    private Long userId;
    private String userName;
    private String comment;
    private LocalDateTime date;

    public MunroCompletedCommentDto(Long userId, String userName, String comment, LocalDateTime date) {
        this.userId = userId;
        this.userName = userName;
        this.comment = comment;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
