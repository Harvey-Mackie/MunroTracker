package com.munro.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MunroFeedDetailsDto extends MunroDetailsDto{
    private String userName;

    public MunroFeedDetailsDto(String name, int height, double latitude, double longitude, String region, String meaningOfName, boolean completed, List<MunroCompletedCommentDto> munroCompletedCommentDtoList, List<MunroCompletedKudosDto> munroCompletedKudosDtoList, String userName) {
        super(name, height, latitude, longitude, region, meaningOfName, completed, munroCompletedCommentDtoList, munroCompletedKudosDtoList);
        this.userName = userName;
    }
}
