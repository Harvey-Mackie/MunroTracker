package com.munro.api.controller;

import com.munro.api.model.dto.CommentDetailsDto;
import com.munro.api.model.dto.MunroFeedDetailsDto;
import com.munro.api.service.MunroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/munros_completed")
public class MunroCompletedController {

    private final MunroService munroService;

    @Autowired
    public MunroCompletedController(MunroService munroService) {
        this.munroService = munroService;
    }

    @GetMapping
    public List<MunroFeedDetailsDto> GetFeed(
            @RequestParam Long currentUserId
    ){
        return munroService.feed(currentUserId);
    }

    @GetMapping(path="{munroCompletedId}")
    public MunroFeedDetailsDto GetMunroCompletedDetails(
            @PathVariable("munroCompletedId") Long munroCompletedId,
            @RequestParam("userId") Long userId
    ){
        return munroService.getMunroCompleted(munroCompletedId);
    }

    @PutMapping(path="{munroCompletedId}/actions/kudo")
    public void SetKudosOnMunroCompleted(
            @PathVariable("munroCompletedId") Long munroCompletedId,
            @RequestParam Long currentUserId
    ){
        munroService.giveKudos(munroCompletedId, currentUserId);
    }

    @PostMapping(path="{munroCompletedId}/actions/comment")
    public void SetKudosOnMunroCompleted(
            @PathVariable("munroCompletedId") Long munroCompletedId,
            @RequestBody CommentDetailsDto body
    ){
        munroService.postComment(munroCompletedId, body.getUserId(), body.getComment());
    }

}
