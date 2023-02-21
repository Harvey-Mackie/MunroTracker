package com.munro.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MunroCompletedCommentDto {
    private Long userId;
    private String userName;
    private String comment;
    private LocalDateTime date;
}
