package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import io.github.sachithariyathilaka.entity.Course;

import java.util.ArrayList;

/**
 * Course repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course save(Course course);
    ArrayList<Course> getAllByIdNotNull();
    Course getByCourseName(String Name);
    ArrayList<Course> getAllByTeacher(String teacher);
}
