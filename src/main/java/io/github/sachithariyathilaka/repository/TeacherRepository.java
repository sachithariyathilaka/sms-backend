package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.sachithariyathilaka.entity.Teacher;

import java.util.ArrayList;

/**
 * Teacher repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Teacher save(Teacher teacher);
    ArrayList<Teacher> getAllByUsernameNotNull();
    Teacher getTeacherByName(String Name);
    Teacher findByUsername(String Username);
    Teacher findById(int id);
}

