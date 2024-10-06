package com.parcial_primer_corte.parcial_primer_corte.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@Table(name = "administrativo")
public class Administrator extends Person {

    @Column(name = "rol", length = 255, nullable = false)
    private String rol;

    public Administrator() {
        super();
    }

    public Administrator(String name, String lastname, String email, String rol) {
        super(name, lastname, email);
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + getName() + '\'' +  // Llamada a getName() de la clase Person
                ", lastname='" + getLastname() + '\'' +  // Llamada a getLastname() de la clase Person
                ", email='" + getEmail() + '\'' +  // Llamada a getEmail() de la clase Person
                ", rol='" + rol + '\'' +  // Propiedad específica de Administrator
                '}';
    }

}
