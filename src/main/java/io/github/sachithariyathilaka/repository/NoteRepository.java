package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import io.github.sachithariyathilaka.entity.Notes;

import java.util.ArrayList;

/**
 * Note repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public interface NoteRepository extends CrudRepository<Notes, Integer> {
    Notes save(Notes notes);
    ArrayList<Notes> getAllByCourseAndType(String Course, String Type);
    Notes getByCourseAndTypeAndDate(String Course, String Type, String Date);
}
