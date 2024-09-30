package com.parcial_primer_corte.parcial_primer_corte.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial_primer_corte.parcial_primer_corte.data.models.Person;
import com.parcial_primer_corte.parcial_primer_corte.facade.services.Person.IPersonService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/parcial/persona")
public class PersonController {

    private final IPersonService service;

    public PersonController(IPersonService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity getPerson(@PathVariable Integer id) {
        System.out.println("Buscando al usuario con id: " + id);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}
