package com.parcial_primer_corte.parcial_primer_corte.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial_primer_corte.parcial_primer_corte.data.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
