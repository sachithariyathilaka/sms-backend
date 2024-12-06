package io.github.sachithariyathilaka.controller;

import io.github.sachithariyathilaka.config.JwtRequest;
import io.github.sachithariyathilaka.config.JwtResponse;
import io.github.sachithariyathilaka.entity.Notes;
import io.github.sachithariyathilaka.entity.Teacher;
import io.github.sachithariyathilaka.entity.User;
import io.github.sachithariyathilaka.repository.UserRepository;
import io.github.sachithariyathilaka.resource.*;
import io.github.sachithariyathilaka.service.Impl.TeacherServiceImpl;
import io.github.sachithariyathilaka.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Teacher controller class.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
@RestController
@CrossOrigin
public class TeacherController {

    private static final Logger logger= Logger.getLogger(String.valueOf(TeacherController.class));

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/teacherLogin", method = RequestMethod.POST)
    public ResponseEntity<?> teacherLogin(@RequestBody JwtRequest credentials) throws Exception {
        logger.info(getClass() + " << Teacher Login Controller >>");
        User user = userRepository.findByUsernameAndPosition(credentials.getUsername(), "Teacher");
        if(user != null){
            boolean auth = teacherServiceImpl.teacherLogin(credentials.getUsername(), credentials.getPassword());
            if(auth){
                final UserDetails userDetails = teacherServiceImpl.loadUserByUsername(credentials.getUsername());
                final Teacher teacher = teacherServiceImpl.getUserByName(credentials.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new UserReturn(token, teacher.getId()));
            } else{
                logger.info(getClass() + " << Teacher Credentials Invalid >>");
                return ResponseEntity.ok(new JwtResponse("Invalid"));
            }
        } else {
            return ResponseEntity.ok(new JwtResponse("Invalid"));
        }
    }

    @RequestMapping(value= "/getTeacherById", method = RequestMethod.POST)
    public ResponseEntity<?> getTeacherById(@RequestBody String id) throws Exception {
        logger.info(getClass().toString()+" << Get Teacher By Id Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.getTeacherById(Integer.parseInt(id)));
    }

    @RequestMapping(value= "/saveNote", method = RequestMethod.POST)
    public ResponseEntity<?> saveNote(@RequestBody Notes notes) throws Exception {
        logger.info(getClass().toString()+" << Save E-notes Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.saveNote(notes));
    }

    @RequestMapping(value= "/saveAssignment", method = RequestMethod.POST)
    public ResponseEntity<?> saveAssignment(@RequestBody Notes notes) throws Exception {
        logger.info(getClass().toString()+" << Save saveAssignment Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.saveNote(notes));
    }

    @RequestMapping(value= "/getDatesForCourse", method = RequestMethod.POST)
    public ResponseEntity<?> getDatesForCourse(@RequestBody CourseAndType courseAndType) throws Exception {
        logger.info(getClass().toString()+" << Get Dates For Course Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.getDatesForCourse(courseAndType));
    }

    @RequestMapping(value= "/getNotesForDate", method = RequestMethod.POST)
    public ResponseEntity<?> getNotesForDate(@RequestBody CourseAndTypeAndDate courseAndTypeAndDate) throws Exception {
        logger.info(getClass().toString()+" << Get Notes For Date Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.getNotesForDate(courseAndTypeAndDate));
    }

    @RequestMapping(value= "/getCoursesByTeacherId", method = RequestMethod.POST)
    public ResponseEntity<?> getCoursesByTeacherId(@RequestBody String id) throws Exception {
        logger.info(getClass().toString()+" << Get Courses By Teacher Id Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.getCoursesByTeacherId(Integer.parseInt(id)));
    }

    @RequestMapping(value= "/markAllAttendance", method = RequestMethod.POST)
    public ResponseEntity<?> markAllAttendance(@RequestBody CourseAndDate courseAndDate) throws Exception {
        logger.info(getClass().toString()+" << Mark All Students Attendance Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.markAllAttendance(courseAndDate));
    }

    @RequestMapping(value= "/markAttendance", method = RequestMethod.POST)
    public ResponseEntity<?> markAttendance(@RequestBody CourseAndDateAndStudent courseAndDateAndStudent) throws Exception {
        logger.info(getClass().toString()+" << Mark Students Attendance Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.markAttendance(courseAndDateAndStudent));
    }

    @RequestMapping(value= "/getAllStudentsForAttendance", method = RequestMethod.POST)
    public ResponseEntity<?> getAllStudentsForAttendance(@RequestBody CourseAndDate courseAndDate) throws Exception {
        logger.info(getClass().toString()+" << Get All Students for Attendance Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.getAllStudentsForAttendance(courseAndDate));
    }

    @RequestMapping(value= "/getAttendanceDatesForCourse", method = RequestMethod.POST)
    public ResponseEntity<?> getAttendanceDatesForCourse(@RequestBody String course) throws Exception {
        logger.info(getClass().toString()+" << Get All Dates for Attendance Controller >>");
        return ResponseEntity.ok(teacherServiceImpl.getAttendanceDatesForCourse(course));
    }
}
