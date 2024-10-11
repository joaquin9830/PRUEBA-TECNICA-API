package com.api_test.prueba.tecnica.controller;

import com.api_test.prueba.tecnica.modelo.Movie;
import com.api_test.prueba.tecnica.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador de la entidad Movie
@RestController
@RequestMapping("/api/persons/{personId}/movies")
public class MovieController {

    // Inyección de dependencias
    @Autowired
    private PersonService personService;

    // Métodos del controlador
    // Obtener todas las películas de una persona
    @GetMapping
    public List<Movie> getMovies(@PathVariable Long personId) {
        return personService.getMoviesByPersonId(personId);
    }

    // Añadir una película a una persona
    @PostMapping
    public Movie addMovie(@PathVariable Long personId, @RequestBody Movie movie) {
        return personService.addMovie(personId, movie);
    }

    // Eliminar una película de una persona POR ID
    @DeleteMapping("/{movieId}")
    public void removeMovie(@PathVariable Long personId, @PathVariable Long movieId) {
        personService.removeMovie(personId, movieId);
    }
}
