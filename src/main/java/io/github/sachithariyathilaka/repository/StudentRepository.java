package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import io.github.sachithariyathilaka.entity.Student;

import java.util.ArrayList;

/**
 * Student repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student save(Student student);
    ArrayList<Student> getAllByUsernameNotNull();
    Student getByName(String Name);
}
