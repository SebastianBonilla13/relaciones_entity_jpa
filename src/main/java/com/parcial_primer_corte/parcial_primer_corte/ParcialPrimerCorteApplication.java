package com.parcial_primer_corte.parcial_primer_corte;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parcial_primer_corte.parcial_primer_corte.data.models.Course;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Location;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Subject;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.LocationRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.SubjectRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ParcialPrimerCorteApplication implements CommandLineRunner {

	@Autowired
	private SubjectRepository subjectRepo;

	public static void main(String[] args) {
		SpringApplication.run(ParcialPrimerCorteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cerateSubject();
	}

	private void cerateSubject() {
		List<Course> cursos = new ArrayList<Course>();
		Subject asignatura = new Subject();
		asignatura.setCode("1234");
		asignatura.setName("Las guacas");
		asignatura.setCourses(cursos);

		Subject newSubject = subjectRepo.save(asignatura);
		System.out.println("SE HA CREADO LA NUEVA ASIGNATURA: " + newSubject);
	}

}
