package com.moviebooking.movieservice.service.impl;

import com.moviebooking.movieservice.dto.MovieRequest;
import com.moviebooking.movieservice.dto.MovieResponse;
import com.moviebooking.movieservice.entity.Movie;
import com.moviebooking.movieservice.exception.MovieNotFoundException;
import com.moviebooking.movieservice.repository.MovieRepository;
import com.moviebooking.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieResponse createMovie(MovieRequest request) {

        Movie movie = Movie.builder()
                .title(request.getTitle())
                .genre(request.getGenre())
                .durationMins(request.getDurationMins())
                .build();

        Movie saved = movieRepository.save(movie);

        return mapToResponse(saved);
    }

    @Override
    public MovieResponse getMovieById(String movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        return mapToResponse(movie);
    }

    @Override
    public Page<MovieResponse> getAllMovies(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public MovieResponse updateMovie(String movieId, MovieRequest request) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());
        movie.setDurationMins(request.getDurationMins());

        Movie updated = movieRepository.save(movie);

        return mapToResponse(updated);
    }

    @Override
    public void deleteMovie(String movieId) {

        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie not found");
        }

        movieRepository.deleteById(movieId);
    }

    private MovieResponse mapToResponse(Movie movie) {
        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .durationMins(movie.getDurationMins())
                .build();
    }
}
