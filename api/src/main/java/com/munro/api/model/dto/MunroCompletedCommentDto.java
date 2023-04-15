package com.munro.api.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MunroCompletedCommentDto extends CommentDetailsDto{
    private String userName;
    private LocalDateTime date;

    public MunroCompletedCommentDto(Long userId, String userName, String comment, LocalDateTime date){
        super(userId, comment);
        setUserName(userName);
        setDate(date);
    }
}
