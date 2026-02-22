package com.moviebooking.movieservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieResponse {

    private String movieId;
    private String title;
    private String genre;
    private Integer durationMins;
}
