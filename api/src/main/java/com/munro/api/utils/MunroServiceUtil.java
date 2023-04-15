package com.munro.api.utils;

import com.munro.api.model.dto.MunroCompletedCommentDto;
import com.munro.api.model.dto.MunroCompletedKudosDto;
import com.munro.api.model.dto.MunroFeedDetailsDto;
import com.munro.api.model.entities.MunroCompletedCommentEntity;
import com.munro.api.model.entities.MunroCompletedEntity;
import com.munro.api.model.entities.MunroCompletedKudosEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MunroServiceUtil {
    private MunroServiceUtil(){}

    public static List<MunroCompletedCommentDto> mapCompletedCommentEntityToDetailsDto(List<MunroCompletedCommentEntity> entity){
        List<MunroCompletedCommentDto> commentCollection = new ArrayList<>();

        entity.forEach(completedCommentEntity ->
                commentCollection.add(
                        new MunroCompletedCommentDto(completedCommentEntity.getUser().getId(), completedCommentEntity.getUser().getName(), completedCommentEntity.getComment(), LocalDateTime.now())
                )
        );

        return commentCollection;
    }
    public static List<MunroCompletedKudosDto> mapCompletedKudosEntityToDetailsDto(List<MunroCompletedKudosEntity> entity){
        List<MunroCompletedKudosDto> kudosCollection = new ArrayList<>();

        entity.forEach(munroEntry ->
                kudosCollection.add(new MunroCompletedKudosDto(
                        munroEntry.getMunroCompleted().getId(),
                        munroEntry.getUser().getName(),
                        munroEntry.getUser().getId(),
                        LocalDateTime.now()
                ))
        );

        return kudosCollection;
    }

    public static MunroFeedDetailsDto mapMunroCompletedEntityToMunroFeedDetailsDto(MunroCompletedEntity completedMunro){
        var munro = completedMunro.getMunro();
        var comments = mapCompletedCommentEntityToDetailsDto(completedMunro.getMunroCompletedCommentEntities());
        var kudos = mapCompletedKudosEntityToDetailsDto(completedMunro.getMunroCompletedKudosEntities());

        return MunroFeedDetailsDto.builder()
                .userName(completedMunro.getUser().getName())
                .name(munro.getName())
                .height(munro.getHeight())
                .latitude(munro.getLatitude())
                .longitude(munro.getLongitude())
                .region(munro.getRegion())
                .meaningOfName(munro.getMeaningOfName())
                .completed(true)
                .munroCompletedCommentDtoList(comments)
                .munroCompletedKudosDtoList(kudos)
                .build();
}

}
