package io.github.sachithariyathilaka.service.Impl;

import io.github.sachithariyathilaka.resource.CourseAndDate;
import io.github.sachithariyathilaka.resource.CourseAndDateAndStudent;
import io.github.sachithariyathilaka.resource.CourseAndType;
import io.github.sachithariyathilaka.resource.CourseAndTypeAndDate;
import io.github.sachithariyathilaka.entity.*;
import io.github.sachithariyathilaka.repository.AttendanceRepository;
import io.github.sachithariyathilaka.repository.NoteRepository;
import io.github.sachithariyathilaka.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.github.sachithariyathilaka.service.TeacherService;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Teacher service implementation class.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
@Service
public class TeacherServiceImpl implements UserDetailsService, TeacherService {

    private static final Logger logger= Logger.getLogger(String.valueOf(TeacherServiceImpl.class));

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByUsername(s);
        if (teacher == null) {
            logger.info(getClass() + " << Teacher not found with username: " + s + ">>");
            throw new UsernameNotFoundException("Teacher not found with username: " + s);
        }
        return new org.springframework.security.core.userdetails.User(teacher.getUsername(), teacher.getPassword(),
                new ArrayList<>());
    }

    @Override
    public boolean teacherLogin(String Username, String Password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
            logger.info(getClass() + " << Teacher Authenticated >>");
            return true;
        } catch (BadCredentialsException e) {
            logger.info(getClass() +" << Bad Credentials >>");
            return false;
        }
    }

    @Override
    public Teacher getUserByName(String username) {
        logger.info(getClass().toString() +" << Get Teacher By Name >>");
        Teacher teacher = teacherRepository.findByUsername(username);
        return teacher;
    }

    @Override
    public Teacher getTeacherById(int id) {
        logger.info(getClass().toString() +" << Get Teacher By Id >>");
        Teacher teacher = teacherRepository.findById(id);
        return teacher;
    }

    @Override
    public Notes saveNote(Notes notes) {
        logger.info(getClass().toString() +" << Save E-notes >>");
        return noteRepository.save(notes);
    }

    @Override
    public ArrayList<String> getDatesForCourse(CourseAndType courseAndType) {
        ArrayList<Notes> notesList  = noteRepository.getAllByCourseAndType(courseAndType.getCourse(), courseAndType.getType());
        ArrayList<String> dateList = new ArrayList<>();
        for(int i=0; i<notesList.size(); i++){
            dateList.add(notesList.get(i).getDate());
        }
        return dateList;
    }

    @Override
    public String getNotesForDate(CourseAndTypeAndDate courseAndTypeAndDate) {
        Notes notes = noteRepository.getByCourseAndTypeAndDate(courseAndTypeAndDate.getCourse(), courseAndTypeAndDate.getType(), courseAndTypeAndDate.getDate());
        return notes.getUrl();
    }

    @Override
    public ArrayList<Course> getCoursesByTeacherId(int id) {
        Teacher teacher = teacherRepository.findById(id);
        return userServiceImpl.getCoursesByTeacher(teacher.getName());
    }

    @Override
    public boolean markAllAttendance(CourseAndDate courseAndDate) {
        ArrayList<String> allStudentNames = new ArrayList<>();
        ArrayList<StudentsInCourse> allStudents = userServiceImpl.getStudentByCourse(courseAndDate.getCourse());
        for(int i=0; i<allStudents.size(); i++){
            allStudentNames.add(allStudents.get(i).getStudent());
        }
        for(int j=0; j<allStudentNames.size(); j++){
            Attendance attendance = new Attendance();
            attendance.setCourse(courseAndDate.getCourse());
            attendance.setDate(courseAndDate.getDate());
            attendance.setCourse(allStudentNames.get(j));
            attendance.setAttendance(false);
            attendanceRepository.save(attendance);
        }
        return true;
    }

    @Override
    public boolean markAttendance(CourseAndDateAndStudent courseAndDateAndStudent) {
        Attendance attendance = new Attendance();
        attendance.setDate(courseAndDateAndStudent.getDate());
        attendance.setCourse(courseAndDateAndStudent.getCourse());
        attendance.setStudent(courseAndDateAndStudent.getStudent());
        attendance.setAttendance(true);
        attendanceRepository.save(attendance);
        return true;
    }

    @Override
    public ArrayList<String> getAllStudentsForAttendance(CourseAndDate courseAndDate) {
        ArrayList<Attendance> allAttendance = attendanceRepository.getAllByCourseAndAndDateAndAttendance(courseAndDate.getCourse(), courseAndDate.getDate(), true);
        ArrayList<String> allStudents = new ArrayList<>();
        for(int i=0; i<allAttendance.size(); i++){
            allStudents.add(allAttendance.get(i).getStudent());
        }
        return allStudents;
    }

    @Override
    public ArrayList<String> getAttendanceDatesForCourse(String course) {
        ArrayList<Attendance> allAttendanceForCourse = attendanceRepository.getAllByCourse(course);
        ArrayList<String> allDatesForCourse = new ArrayList<>();
        for (Attendance attendance : allAttendanceForCourse) {
            allDatesForCourse.add(attendance.getDate());
        }
        return allDatesForCourse;
    }
}
