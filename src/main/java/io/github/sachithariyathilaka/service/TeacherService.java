package io.github.sachithariyathilaka.service;

import io.github.sachithariyathilaka.resource.CourseAndDate;
import io.github.sachithariyathilaka.resource.CourseAndDateAndStudent;
import io.github.sachithariyathilaka.resource.CourseAndType;
import io.github.sachithariyathilaka.resource.CourseAndTypeAndDate;
import io.github.sachithariyathilaka.entity.Course;
import io.github.sachithariyathilaka.entity.Notes;
import io.github.sachithariyathilaka.entity.Teacher;

import java.util.ArrayList;

/**
 * Teacher service interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public interface TeacherService {
    boolean teacherLogin(String Username, String Password) throws Exception;
    Teacher getUserByName(String username);
    Teacher getTeacherById(int id);
    Notes saveNote(Notes notes);
    ArrayList<String> getDatesForCourse(CourseAndType courseAndType);
    String getNotesForDate(CourseAndTypeAndDate courseAndTypeAndDate);
    ArrayList<Course> getCoursesByTeacherId(int id);
    boolean markAllAttendance(CourseAndDate courseAndDate);
    boolean markAttendance(CourseAndDateAndStudent courseAndDateAndStudent);
    ArrayList<String> getAllStudentsForAttendance(CourseAndDate courseAndDate);
    ArrayList<String> getAttendanceDatesForCourse(String course);
}
