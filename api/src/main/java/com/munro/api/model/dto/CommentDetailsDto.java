package com.munro.api.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDetailsDto {
    private Long userId;
    private String comment;
}
