package com.munro.api.controller;

import com.munro.api.model.dto.MunroDetailsDto;
import com.munro.api.service.MunroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/munros")
@RequiredArgsConstructor
public class MunroController {
    private final MunroService munroService;

    //Future Enhancement - UserId will be fetched from bearer token once authentication is implemented.
    @GetMapping
    public List<MunroDetailsDto> getMunros(
            @RequestParam Long userId
    ){
        return munroService.getMunros(userId);
    }

    @PostMapping(path = "{munroId}/complete")
    public void setMunroToComplete(
            @PathVariable("munroId") Long munroId,
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) LocalDate date
    ){
        munroService.setMunroToComplete(userId, munroId, date);
    }
}
