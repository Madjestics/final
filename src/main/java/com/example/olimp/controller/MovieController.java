package com.example.olimp.controller;

import com.example.olimp.model.Movie;
import com.example.olimp.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findById(@PathVariable(name = "id") Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Movie addMovie(@RequestBody Movie Movie) {
        return movieService.add(Movie);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@RequestBody Movie Movie, @PathVariable(name = "id") Long id) {
        return movieService.update(Movie, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMovie(@PathVariable(name = "id") Long id) {
        movieService.delete(id);
    }
}
