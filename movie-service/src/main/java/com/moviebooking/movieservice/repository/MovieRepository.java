package com.moviebooking.movieservice.repository;

import com.moviebooking.movieservice.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Page<Movie> findByGenre(String genre, Pageable pageable);

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
