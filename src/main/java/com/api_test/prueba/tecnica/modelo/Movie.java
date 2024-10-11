package com.api_test.prueba.tecnica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//Anotaciones
@Data
@Entity
public class Movie {
    //Atributo Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos de la clase
    private String title;
    private String genre;
}
