package com.api_test.prueba.tecnica.repository;

import com.api_test.prueba.tecnica.modelo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstNameContaingIgnoreCase(String firstName);
}
