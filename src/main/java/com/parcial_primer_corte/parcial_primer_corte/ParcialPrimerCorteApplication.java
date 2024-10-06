package com.parcial_primer_corte.parcial_primer_corte;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parcial_primer_corte.parcial_primer_corte.data.models.Course;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Location;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Office;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Subject;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Teacher;
import com.parcial_primer_corte.parcial_primer_corte.data.models.TimeSlot;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.CourseRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.LocationRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.PersonRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.SubjectRepository;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.TimeSlotRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ParcialPrimerCorteApplication implements CommandLineRunner {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private PersonRepository servicioBDUsuarios;

	@Autowired
	private CourseRepository servicioBDCourses;

	@Autowired
	private TimeSlotRepository servicioBDTimeSlots;

	@Autowired
	private LocationRepository servicioBDLocations;

	public static void main(String[] args) {
		SpringApplication.run(ParcialPrimerCorteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createSubject();
		createTimeSlot1();
		createTimeSlot2();
		listTimeSlots();
		/* createTeacher(); */
		/* this.subjectRepo.findAll(); */
	}

	private void createSubject() {
		List<Course> cursos = new ArrayList<Course>();
		Subject asignatura = new Subject();
		asignatura.setCode("1234");
		asignatura.setName("Las guacas");
		asignatura.setCourses(cursos);

		Subject newSubject = subjectRepo.save(asignatura);
		System.out.println("SE HA CREADO LA NUEVA ASIGNATURA: " + newSubject);

		// para la creación de franja horaria

		Course objCurso1 = new Course(null, "Base de Datos II", asignatura, null);
		this.servicioBDCourses.save(objCurso1);
		System.out.println("SE HA CREADO UN CURSO 1");

		Course objCurso2 = new Course(null, "Base de Datos II", asignatura, null);
		this.servicioBDCourses.save(objCurso2);
		System.out.println("SE HA CREADO UN CURSO 2");

		// ---------------------

		Location Location1 = new Location(null, "Auditorio 1", 20, null);
		this.servicioBDLocations.save(Location1);
		System.out.println("SE HA CREADO UN ESPACIO FISICO 1");

		Location Location2 = new Location(null, "Sala 4", 15, null);
		this.servicioBDLocations.save(Location2);
		System.out.println("SE HA CREADO UN ESPACIO FISICO 2");

		// para el listado de franjas horarias

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

	private void createTimeSlot1() {
		Optional<Course> objCourse = this.servicioBDCourses.findById(1);
		Optional<Location> objLocation = this.servicioBDLocations.findById(1);

		if (objCourse.isPresent()) {
			if (objLocation.isPresent()) {
				TimeSlot objTimeSlot = new TimeSlot(null, "lunes", LocalTime.of(14, 00), LocalTime.of(16, 00),
						objCourse.get(), objLocation.get());
				this.servicioBDTimeSlots.save(objTimeSlot);
				System.out.println("SE HA CREADO LA FRANJA HORARIA");
			} else {
				System.out.println("El espacio fisico no existe en la base de datos");
			}
		} else {
			System.out.println("El Curso no existe en la base de datos");
		}

	}

	private void createTimeSlot2() {
		Optional<Course> objCourse = this.servicioBDCourses.findById(2);
		Optional<Location> objLocation = this.servicioBDLocations.findById(2);

		if (objCourse.isPresent()) {
			if (objLocation.isPresent()) {
				TimeSlot objTimeSlot = new TimeSlot(null, "martes", LocalTime.of(7, 00), LocalTime.of(9, 00),
						objCourse.get(), objLocation.get());
				this.servicioBDTimeSlots.save(objTimeSlot);
				System.out.println("SE HA CREADO LA FRANJA HORARIA");
			} else {
				System.out.println("El espacio fisico no existe en la base de datos");
			}
		} else {
			System.out.println("El Curso no existe en la base de datos");
		}

	}

	private void listTimeSlots() {

		System.out.println("LISTANDO FRANJAS HORARIAS \n");

		Iterable<TimeSlot> listTimeSlots = this.servicioBDTimeSlots.findAll();

		for (TimeSlot TimeSlot : listTimeSlots) {
			System.out.println("dia: " + TimeSlot.getDay());
			System.out.println("fecha inicio: " + TimeSlot.getStartTime());
			System.out.println("fecha fin: " + TimeSlot.getEndTime());

			System.out.println("curso: " + TimeSlot.getCourse());
			System.out.println("espacio fisico: " + TimeSlot.getLocation());

			System.out.println(" ---- ---- ----");
		}
	}
	// entity, relaciones y metodos - PREGUNTAS SUSTENTACIÓN

}
