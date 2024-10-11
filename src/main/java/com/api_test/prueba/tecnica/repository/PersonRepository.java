package com.api_test.prueba.tecnica.repository;

import com.api_test.prueba.tecnica.modelo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositorio de la entidad Persona
public interface PersonRepository extends JpaRepository<Person, Long> {
    // Método para buscar personas por nombre
    List<Person> findByFirstNameContainingIgnoreCase(String firstName);
}
