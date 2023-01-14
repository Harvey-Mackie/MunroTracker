package com.munro.api.data;

import com.munro.api.model.dto.MunroDetailsDto;

import java.util.ArrayList;
import java.util.List;

public final class TestData {

    public static List<MunroDetailsDto> getMunros(){
        var munros = new ArrayList<MunroDetailsDto>();
        munros.add(
                new MunroDetailsDto("test", 100, 100, 100, "test", "test", false)
        );
        return munros;
    }

}
