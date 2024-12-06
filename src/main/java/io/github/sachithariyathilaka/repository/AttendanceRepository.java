package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import io.github.sachithariyathilaka.entity.Attendance;

import java.util.ArrayList;

/**
 * Attendance repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {
    Attendance save(Attendance attendance);
    boolean deleteByCourseAndDateAndStudent(String course, String date, String student);
    ArrayList<Attendance> getAllByCourseAndAndDateAndAttendance(String course, String date, boolean attendance);
    ArrayList<Attendance> getAllByCourse(String course);

}
