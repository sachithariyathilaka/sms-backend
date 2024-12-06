package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import io.github.sachithariyathilaka.entity.StudentsInCourse;

import java.util.ArrayList;

/**
 * Student course repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public interface StudentsInCourseRepository extends CrudRepository<StudentsInCourse, Integer> {
    StudentsInCourse save(StudentsInCourse studentsInCourse);
    ArrayList<StudentsInCourse> getAllByStudent(String student);
    ArrayList<StudentsInCourse> getAllByCourse(String course);
}
