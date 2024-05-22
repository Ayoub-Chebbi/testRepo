package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.services.CourseService;
import com.project.services.UserService;
import com.project.models.Course;
import com.project.models.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CourseControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private boolean isLoggedIn(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return userId != null;
    }

    @GetMapping("/courses/new")
    public ResponseEntity<?> showNewCourseForm(HttpSession session) {
        if (!isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        return ResponseEntity.ok(new Course());
    }

    @PostMapping("/courses/new")
    public ResponseEntity<?> saveCourse(@Valid @ModelAttribute("course") Course course,
                                        BindingResult bindingResult, HttpSession session) {
        if (!isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        User instructor = userService.findById((Long) session.getAttribute("userId"));
        course.setInstructor(instructor);
        courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("/courses/{id}/edit")
    public ResponseEntity<?> showEditCourseForm(@PathVariable("id") Long id, HttpSession session) {
        if (!isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        Course course = courseService.getCourseById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping("/courses/{id}/edit")
    public ResponseEntity<?> updateCourse(@PathVariable("id") Long id,
                                          @Valid @ModelAttribute("course") Course course,
                                          BindingResult bindingResult, HttpSession session) {
        if (!isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        course.setId(id);
        courseService.saveCourse(course);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courses/{id}/delete")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id, HttpSession session) {
        if (!isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> showCourseDetails(@PathVariable("id") Long id, HttpSession session) {
        if (!isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        Course course = courseService.getCourseById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        return ResponseEntity.ok(course);
    }
}
