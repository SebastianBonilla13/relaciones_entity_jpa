package com.parcial_primer_corte.parcial_primer_corte;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parcial_primer_corte.parcial_primer_corte.data.models.Course;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Location;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Office;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Subject;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Teacher;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.LocationRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.PersonRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.SubjectRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ParcialPrimerCorteApplication implements CommandLineRunner {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private PersonRepository servicioBDUsuarios;

	public static void main(String[] args) {
		SpringApplication.run(ParcialPrimerCorteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/* createSubject(); */
		createTeacher();
	}

	private void createSubject() {
		List<Course> cursos = new ArrayList<Course>();
		Subject asignatura = new Subject();
		asignatura.setCode("1234");
		asignatura.setName("Las guacas");
		asignatura.setCourses(cursos);

		Subject newSubject = subjectRepo.save(asignatura);
		System.out.println("SE HA CREADO LA NUEVA ASIGNATURA: " + newSubject);
	}

	private void createTeacher() {

		Teacher objTeacher = new Teacher();
		objTeacher.setName("Jhon");
		objTeacher.setLastname("Masso");
		objTeacher.setEmail("jmasso@unicauca.edu.co");

		Office objOffice = new Office();
		objOffice.setName("sala 4");
		objOffice.setLocation("FIET");

		objTeacher.setOffice(objOffice);
		objOffice.setTeacher(objTeacher);

		this.servicioBDUsuarios.save(objTeacher);
		System.out.println("SE HA CREADO EL USUARIO");

	}

}
