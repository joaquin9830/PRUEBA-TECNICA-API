package com.api_test.prueba.tecnica.controller;

import com.api_test.prueba.tecnica.modelo.Person;
import com.api_test.prueba.tecnica.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador de la entidad Person
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    
    @Autowired
    private PersonService personService;

    //Método para obtener todas las personas
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    //Método para obtener una persona por ID
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    //Método para buscar personas por nombre
    @GetMapping("/search")
    public List<Person> searchByName(@RequestParam("name") String name) {
        return personService.getPersonsByName(name);
    }

    //Método para crear una persona
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    //Método para actualizar una persona por id
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    //Método para eliminar una persona mediante id
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
