package com.moviebooking.movieservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @Min(1)
    private Integer durationMins;
}
