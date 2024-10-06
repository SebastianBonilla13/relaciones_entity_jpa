package com.parcial_primer_corte.parcial_primer_corte;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.parcial_primer_corte.parcial_primer_corte.data.models.*;
import com.parcial_primer_corte.parcial_primer_corte.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	@Autowired
	private TeacherRepository servicioBDTeachers;

	public static void main(String[] args) {
		SpringApplication.run(ParcialPrimerCorteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/**
		 * EJECUCIÓN PRIMER PUNTO
		 * */
		createTeacher();

		/**
		 * EJECUCIÓN SEGUNDO PUNTO
		 * */
		createSubject();
		createCourse();
		asociateCourseAndTeacher();

		/**
		 * EJECUCION PUNTO 3
		 * */
		this.createLocations();
		this.createTimeSlot1();
		this.createTimeSlot2();
		this.createTimeSlot3();

		/**
		 * 	EJECUCIÓN PUNTO 4
		 * */
		this.listTimeSlots();


		/**
		 * 	EJECUCIPIN PUNTO 5
		 * */
		this.timeSlotByTeacherId(1);

		/**
		 * EJECUCIÓN PUNTO 6
		 * */
		this.deleteCourseById(1);
	}


	/**
	 * PRIMER PUNTO
	 * Creación de un docente con la asociación de una oficina
	 * */
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
		this.imprimirFormato("SE HA CEADO UN NUEVO DOCENTE", objTeacher.toString());
	}

	/**
	 * PUNTO 2
	 * CREAR CURSOS Y ASOCIARLES UNA ASIGNATURA Y UN DOCENTE
	 * */
	private void createSubject() {
		List<Course> cursos = new ArrayList<Course>();
		Subject asignatura1 = new Subject(null, "Mineria de datos", "1234", cursos);
		Subject asignatura2 = new Subject(null, "Estructura de datos I", "1235", cursos);
		Subject asignatura3 = new Subject(null, "Introducción a la ingenieria de sistemas", "1236", cursos);

		Subject newSubject1 = subjectRepo.save(asignatura1);
		Subject newSubject2 = subjectRepo.save(asignatura2);
		Subject newSubject3 = subjectRepo.save(asignatura3);

		this.imprimirFormato("SE HA CREADO LA NUEVA ASIGNATURA", newSubject1.toString());
		this.imprimirFormato("SE HA CREADO LA NUEVA ASIGNATURA", newSubject2.toString());
		this.imprimirFormato("SE HA CREADO LA NUEVA ASIGNATURA", newSubject3.toString());
	}

	private void createCourse(){
		Random random = new Random();
		List<Subject> asignaturas = this.subjectRepo.findAll();

		for (Subject asignatura : asignaturas){
			// Generar una letra aleatoria para el nombre del curso
			String randomizedCharacter = String.valueOf((char) (random.nextInt(26) + 'a'));
			Course newCurso = new Course(null, randomizedCharacter, asignatura, null);

			this.servicioBDCourses.save(newCurso);
			this.imprimirFormato("SE HA CREADO UN NUEVO CURSO", newCurso.toString());
		}
	}

	private void asociateCourseAndTeacher(){
		List<Course> courseList = this.servicioBDCourses.findAll();
		List<Teacher> teacherList = this.servicioBDTeachers.findAll();

		for(Teacher teacher : teacherList){
			teacher.setCourses(courseList);
			Teacher t = this.servicioBDUsuarios.save(teacher);
			this.imprimirFormato("SE HA REALIZADO LA ASOCIACIÓN ENTRE PROFESOR Y CURSOS", t.toString());
		}
	}


	/**
	 * PUNTO 3
	 * CREAR UNA FRANJA HORARIA ASOCIADA A UN CURSO Y UN ESPACIO FISICO
	 * */

	private void createLocations(){
		Location location1 = new Location(null, "Auditorio 1", 20, null);
		Location location2 = new Location(null, "Sala 4", 15, null);
		Location location3 = new Location(null, "Sala 332", 15, null);

		this.servicioBDLocations.save(location1);
		this.imprimirFormato("SE HA CREADO UN NUEVO ESPACIO FISICO", location1.toString());

		this.servicioBDLocations.save(location2);
		this.imprimirFormato("SE HA CREADO UN NUEVO ESPACIO FISICO", location2.toString());

		this.servicioBDLocations.save(location3);
		this.imprimirFormato("SE HA CREADO UN NUEVO ESPACIO FISICO", location3.toString());
	}

	private void createTimeSlot1() {
		Course objCourse = this.servicioBDCourses.findById(1).orElseThrow(()->new RuntimeException("El curso con id 1 no existe"));
		Location objLocation = this.servicioBDLocations.findById(1).orElseThrow(()->new RuntimeException("La ubicacion con id 1 no existe"));

		TimeSlot objTimeSlot = new TimeSlot(
				null,
				"lunes",
				LocalTime.of(14, 00),
				LocalTime.of(16, 00),
				objCourse,
				objLocation
		);

		this.servicioBDTimeSlots.save(objTimeSlot);
		this.imprimirFormato("SE HA CREADO UNA NUEVA FRANJA HORARIA", objTimeSlot.toString());
	}

	private void createTimeSlot2() {
		Course objCourse = this.servicioBDCourses.findById(2).orElseThrow(()->new RuntimeException("El curso con id 2 no existe"));
		Location objLocation = this.servicioBDLocations.findById(2).orElseThrow(()->new RuntimeException("La ubicacion con id 2 no existe"));

		TimeSlot objTimeSlot = new TimeSlot(
				null,
				"martes",
				LocalTime.of(7, 00),
				LocalTime.of(9, 00),
				objCourse,
				objLocation
		);
		this.servicioBDTimeSlots.save(objTimeSlot);
		this.imprimirFormato("SE HA CREADO UNA NUEVA FRANJA HORARIA", objTimeSlot.toString());
	}

	private void createTimeSlot3() {
		Course objCourse = this.servicioBDCourses.findById(3).orElseThrow(()->new RuntimeException("El curso con id 3 no existe"));
		Location objLocation = this.servicioBDLocations.findById(3).orElseThrow(()->new RuntimeException("La ubicacion con id 3 no existe"));

		TimeSlot objTimeSlot = new TimeSlot(
				null,
				"viernes",
				LocalTime.of(7, 00),
				LocalTime.of(9, 00),
				objCourse,
				objLocation
		);
		this.servicioBDTimeSlots.save(objTimeSlot);
		this.imprimirFormato("SE HA CREADO UNA NUEVA FRANJA HORARIA", objTimeSlot.toString());
	}


	/**
	 * PUNTO 4
	 * LISTAR TODAS LAS FRANJAS HORARIAS REGISTRADAS
	 * */
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

	/**
	 * PUNTO 5
	 * CONSULTAR LAS FRANJAS HORARIAS DE UN DOCENTE
	 * */
	private void timeSlotByTeacherId(Integer teacherId){
		List<Course> courseList = this.servicioBDTeachers.findAllCoursesById(teacherId);

		System.out.println("FRANJAS DE TRABAJO DEL DOCENTE CON ID " + teacherId.toString());
		for (Course course : courseList){
			TimeSlot timeSlot = this.servicioBDTimeSlots.findByCourseId(course.getId()).orElseThrow(() -> new RuntimeException("El curso no tiene una franja horaria asociada"));
			this.imprimirFormato("FRANJA HORARIA", timeSlot.toString());
		}

	}

	/**
	 * PUNTO 6
	 * ELIMINAR UN CURSO CON EFECTO CASCADA
	 * */
	private void deleteCourseById(Integer courseId){
		System.out.println("SE PROCEDE A ELIMINAR EL CURSO CON ID " + courseId.toString());
		this.servicioBDCourses.deleteById(courseId);
	}

	private void imprimirFormato(String titulo, String texto){
		System.out.println("*********************************\n");
		System.out.println(titulo);
		System.out.println("\n*********************************\n");
		System.out.println(texto);
		System.out.println("\n---------------------------------\n\n\n");
	}

}
