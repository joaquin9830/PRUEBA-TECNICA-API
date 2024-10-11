package com.api_test.prueba.tecnica.controller;

import com.api_test.prueba.tecnica.modelo.Movie;
import com.api_test.prueba.tecnica.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons/{personId}/movies")
public class MovieController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Movie> getMovies(@PathVariable Long personId) {
        return personService.getMoviesByPersonId(personId);
    }

    @PostMapping
    public Movie addMovie(@PathVariable Long personId, @RequestBody Movie movie) {
        return personService.addMovie(personId, movie);
    }

    @DeleteMapping("/{movieId}")
    public void removeMovie(@PathVariable Long personId, @PathVariable Long movieId) {
        personService.removeMovie(personId, movieId);
    }
}
