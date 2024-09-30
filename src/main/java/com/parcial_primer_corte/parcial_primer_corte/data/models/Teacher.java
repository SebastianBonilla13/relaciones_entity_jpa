package com.parcial_primer_corte.parcial_primer_corte.data.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "docente")
public class Teacher extends Person {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id", nullable = false)
    private Office office;

    @ManyToMany
    @JoinTable(name = "curso_docente", joinColumns = @JoinColumn(name = "docente_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Course> courses;

    public Teacher() {
        super();
    }

    public Teacher(String name, String lastname, String email, Office office, List<Course> courses) {
        super(name, lastname, email);
        this.office = office;
        this.courses = courses;
    }

}
