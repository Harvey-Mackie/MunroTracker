package com.munro.api.model.dto;

import java.util.List;

public class MunroFeedDetailsDto extends MunroDetailsDto{
    private String userName;

    public MunroFeedDetailsDto(String name, int height, double latitude, double longitude, String region, String meaningOfName, boolean completed, List<MunroCompletedCommentDto> munroCompletedCommentDtoList, List<MunroCompletedKudosDto> munroCompletedKudosDtoList, String userName) {
        super(name, height, latitude, longitude, region, meaningOfName, completed, munroCompletedCommentDtoList, munroCompletedKudosDtoList);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
