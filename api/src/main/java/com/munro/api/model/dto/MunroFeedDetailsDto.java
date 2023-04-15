package com.munro.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class MunroFeedDetailsDto extends MunroDetailsDto{
    private String userName;
}
