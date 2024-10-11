package com.api_test.prueba.tecnica.service;


import com.api_test.prueba.tecnica.modelo.Person;
import com.api_test.prueba.tecnica.modelo.Movie;
import com.api_test.prueba.tecnica.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    //Límite máximo de películas
    private static final int MAX_MOVIES = 5;

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .sorted((p1, p2) -> (p1.getLastName() + p1.getFirstName()).compareTo(p2.getLastName() + p2.getFirstName()))
                .toList();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    public List<Person> getPersonsByName(String name) {
        return personRepository.findByFirstNameContainingIgnoreCase(name);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person newPersonData) {
        Person person = getPersonById(id);
        if (newPersonData.getFirstName() != null) person.setFirstName(newPersonData.getFirstName());
        if (newPersonData.getLastName() != null) person.setLastName(newPersonData.getLastName());
        if (newPersonData.getBirthdate() != null) person.setBirthdate(newPersonData.getBirthdate());
        person.setHasInsurance(newPersonData.isHasInsurance());
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<Movie> getMoviesByPersonId(Long personId) {
        Person person = getPersonById(personId);
        return person.getFavouriteMovies();
    }

    public Movie addMovie(Long personId, Movie movie) {
        Person person = getPersonById(personId);
        if (person.getFavouriteMovies().size() < MAX_MOVIES) {
            person.getFavouriteMovies().add(movie);
            personRepository.save(person);
            return movie;
        } else {
            throw new RuntimeException("Límite máximo de películas alcanzado");
        }
    }

    public void removeMovie(Long personId, Long movieId) {
        Person person = getPersonById(personId);
        person.getFavouriteMovies().removeIf(movie -> movie.getId().equals(movieId));
        personRepository.save(person);
    }

}
