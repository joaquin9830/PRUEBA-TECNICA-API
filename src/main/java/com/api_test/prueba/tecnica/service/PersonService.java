package com.api_test.prueba.tecnica.service;

import com.api_test.prueba.tecnica.modelo.Person;
import com.api_test.prueba.tecnica.modelo.Movie;
import com.api_test.prueba.tecnica.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class PersonService {

    // Límite máximo de películas
    private static final int MAX_MOVIES = 5;

    // Logger para seguimiento
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    // Listar todas las personas (ordenado por apellido, nombre)
    public List<Person> getAllPersons() {
        List<Person> persons = personRepository.findAll()
                .stream()
                .sorted((p1, p2) -> (p1.getLastName() + p1.getFirstName()).compareTo(p2.getLastName() + p2.getFirstName()))
                .toList();
        if (persons.isEmpty()) {
            logger.info("No se encontraron personas.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron personas.");
        }
        logger.info("Lista de personas obtenida exitosamente.");
        return persons;
    }

    // Buscar una persona por id
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Persona con ID " + id + " no encontrada.");
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada");
                });
    }

    // Buscar una persona por nombre
    public List<Person> getPersonsByName(String name) {
        List<Person> persons = personRepository.findByFirstNameContainingIgnoreCase(name);
        if (persons.isEmpty()) {
            logger.info("No se encontraron personas con el nombre: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron personas con ese nombre.");
        }
        logger.info("Personas encontradas con el nombre: " + name);
        return persons;
    }

    // Crear una persona
    public Person createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        logger.info("Persona creada exitosamente con ID: " + savedPerson.getId());
        return savedPerson;
    }

    // Modificar una persona
    public Person updatePerson(Long id, Person newPersonData) {
        Person person = getPersonById(id);
        if (newPersonData.getFirstName() != null) person.setFirstName(newPersonData.getFirstName());
        if (newPersonData.getLastName() != null) person.setLastName(newPersonData.getLastName());
        if (newPersonData.getBirthdate() != null) person.setBirthdate(newPersonData.getBirthdate());
        person.setHasInsurance(newPersonData.isHasInsurance());
        Person updatedPerson = personRepository.save(person);
        logger.info("Persona con ID " + id + " actualizada exitosamente.");
        return updatedPerson;
    }

    // Eliminar una persona
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            logger.error("Persona con ID " + id + " no encontrada para eliminar.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada");
        }
        personRepository.deleteById(id);
        logger.info("Persona con ID " + id + " eliminada exitosamente.");
    }

    // Mostrar películas de una persona
    public List<Movie> getMoviesByPersonId(Long personId) {
        Person person = getPersonById(personId);
        List<Movie> movies = person.getFavouriteMovies();
        if (movies.isEmpty()) {
            logger.info("No se encontraron películas para la persona con ID: " + personId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron películas.");
        }
        logger.info("Películas obtenidas exitosamente para la persona con ID: " + personId);
        return movies;
    }

    // Agregar una película a una persona
    public Movie addMovie(Long personId, Movie movie) {
        Person person = getPersonById(personId);
        if (person.getFavouriteMovies().size() >= MAX_MOVIES) {
            logger.error("Límite máximo de películas alcanzado para la persona con ID: " + personId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Límite máximo de películas alcanzado");
        }
        person.getFavouriteMovies().add(movie);
        personRepository.save(person);
        logger.info("Película '" + movie.getTitle() + "' añadida exitosamente a la persona con ID: " + personId);
        return movie;
    }

    // Quitar una película de una persona
    public void removeMovie(Long personId, Long movieId) {
        Person person = getPersonById(personId);
        boolean removed = person.getFavouriteMovies().removeIf(movie -> movie.getId().equals(movieId));
        if (!removed) {
            logger.error("Película con ID " + movieId + " no encontrada para la persona con ID: " + personId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada");
        }
        personRepository.save(person);
        logger.info("Película con ID " + movieId + " eliminada exitosamente de la persona con ID: " + personId);
    }
}
