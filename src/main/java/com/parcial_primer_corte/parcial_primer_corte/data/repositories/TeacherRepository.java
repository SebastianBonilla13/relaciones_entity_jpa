package com.parcial_primer_corte.parcial_primer_corte.data.repositories;

import com.parcial_primer_corte.parcial_primer_corte.data.models.Course;
import com.parcial_primer_corte.parcial_primer_corte.data.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("SELECT t.courses FROM Teacher t WHERE t.id = :teacherId")
    List<Course> findAllCoursesById(@Param("teacherId") Integer teacherId);
}
