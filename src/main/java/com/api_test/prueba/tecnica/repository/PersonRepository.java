package com.api_test.prueba.tecnica.repository;

import com.api_test.prueba.tecnica.modelo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
