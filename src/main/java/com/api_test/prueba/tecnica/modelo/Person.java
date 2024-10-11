package com.api_test.prueba.tecnica.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private LocalDate birthdate;

    @Column(name = "has_insurance")
    private boolean hasInsurance;

    @OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private List<Movie> favouriteMovies;
}
