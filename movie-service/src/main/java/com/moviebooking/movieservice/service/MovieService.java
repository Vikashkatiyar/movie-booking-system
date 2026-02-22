package com.moviebooking.movieservice.service;

import com.moviebooking.movieservice.dto.MovieRequest;
import com.moviebooking.movieservice.dto.MovieResponse;
import org.springframework.data.domain.Page;

public interface MovieService {

    MovieResponse createMovie(MovieRequest request);

    MovieResponse getMovieById(String movieId);

    Page<MovieResponse> getAllMovies(int page, int size);

    MovieResponse updateMovie(String movieId, MovieRequest request);

    void deleteMovie(String movieId);
}
