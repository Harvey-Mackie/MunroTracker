package com.munro.api.controller;

import com.munro.api.model.dto.MunroDetailsDto;
import com.munro.api.service.MunroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/munros")
public class MunroController {
    private final MunroService munroService;

    @Autowired
    public MunroController(MunroService munroService) {
        this.munroService = munroService;
    }

    //Future Enhancement - UserId will be fetched from bearer token once authentication is implemented.
    @GetMapping(path="{userId}")
    public List<MunroDetailsDto> GetMunros(
            @PathVariable("userId") Long userId
    ){
        return munroService.GetMunros(userId);
    }

    @PostMapping(path = "{munroId}/complete")
    public void SetMunroToComplete(
            @PathVariable("munroId") Long munroId,
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) LocalDate date
    ){
        munroService.SetMunroToComplete(userId, munroId, date);
    }
}
