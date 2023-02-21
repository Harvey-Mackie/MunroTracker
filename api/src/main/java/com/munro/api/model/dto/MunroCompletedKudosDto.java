package com.munro.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MunroCompletedKudosDto {
    private Long munroCompletedId;
    private String userName;
    private Long userId;
    private LocalDateTime date;
}
