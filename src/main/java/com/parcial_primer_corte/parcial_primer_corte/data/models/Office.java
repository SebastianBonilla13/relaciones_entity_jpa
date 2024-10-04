package com.parcial_primer_corte.parcial_primer_corte.data.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oficina")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", length = 100, nullable = false)
    private String name;
    @Column(name = "ubicacion", length = 100, nullable = false)
    private String location;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "office", cascade = { CascadeType.PERSIST })
    private Teacher teacher;
}
