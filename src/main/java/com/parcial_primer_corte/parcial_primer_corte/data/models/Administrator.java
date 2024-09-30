package com.parcial_primer_corte.parcial_primer_corte.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
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

}
