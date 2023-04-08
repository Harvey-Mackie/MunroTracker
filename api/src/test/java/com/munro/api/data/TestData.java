package com.munro.api.data;

import com.munro.api.model.dto.MunroDetailsDto;

import java.util.List;

public final class TestData {

    public static List<MunroDetailsDto> getMunros(){
        return List.of(new MunroDetailsDto("test", 100, 100, 100, "test", "test", false));
    }

}
