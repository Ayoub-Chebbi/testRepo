package com.project.controllers;

import com.project.models.Course;
import com.project.models.LoginUser;
import com.project.models.Role;
import com.project.models.User;
import com.project.services.CourseService;
import com.project.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private boolean isLoggedIn(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return userId != null;
    }

    @GetMapping("/")
    public String showUserForm(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "login.jsp";
    }

    @PostMapping("/user/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }

        if (newUser.getRoles() == null || newUser.getRoles().isEmpty()) {
            newUser.getRoles().add(Role.STUDENT);
        } else {
            newUser.getRoles().add(Role.INSTRUCTOR);
        }

        User registeredUser = userService.register(newUser, result);
        if (registeredUser == null) {
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        model.addAttribute("successMessage", "Registration successful!");
        return "login.jsp";
    }

    @PostMapping("/user/login")
    public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "login.jsp";
        }

        User user = userService.login(loginUser, result);
        if (user == null) {
            model.addAttribute("newUser", new User());
            return "login.jsp";
        }

        session.setAttribute("userId", user.getId());

        String redirectUrl;
        if (user.getRoles().contains(Role.INSTRUCTOR)) {
            redirectUrl = "redirect:/api/instructor";
        } else if (user.getRoles().contains(Role.STUDENT)) {
            redirectUrl = "redirect:/api/student";
        } else {
            model.addAttribute("newUser", new User());
            model.addAttribute("errors", "User has no valid roles.");
            return "login.jsp";
        }

        return redirectUrl;
    }

    @GetMapping("/api/courses/new")
    public String showNewCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "new_course.jsp";
    }

    @PostMapping("/api/courses/new")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult bindingResult, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        if (bindingResult.hasErrors()) {
            return "new_course.jsp"; // Return to the form page with errors displayed
        }
        User instructor = userService.findById((Long) session.getAttribute("userId"));
        course.setInstructor(instructor);
        courseService.saveCourse(course);
        return "redirect:/courses/" + course.getId();
    }

    @GetMapping("/api/courses/{id}/edit")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        Course course = courseService.getCourseById(id).orElse(null);
        if (course == null) {
            // Handle course not found error
            return "error.jsp";
        }
        model.addAttribute("course", course);
        return "edit_course.jsp";
    }

    @PostMapping("/api/courses/{id}/edit")
    public String updateCourse(@PathVariable("id") Long id,
                               @Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        if (bindingResult.hasErrors()) {
            return "edit_course.jsp"; // Return to the form page with errors displayed
        }
        course.setId(id);
        courseService.saveCourse(course);
        return "redirect:/courses/" + course.getId();
    }

    @GetMapping("/api/courses/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        courseService.deleteCourse(id);
        return "redirect:/course_deleted.jsp";
    }

    @GetMapping("/api/courses/{id}")
    public String showCourseDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        Course course = courseService.getCourseById(id).orElse(null);
        if (course == null) {
            // Handle course not found error
            return "error.jsp";
        }
        model.addAttribute("course", course);
        return "show_course_details.jsp";
    }

    @GetMapping("/api/courses/all")
    public String getAllCourses(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "all_courses.jsp";
    }
    
    @GetMapping("/api/instructor/add-course-to-user")
    public String showAddCourseToUserForm(Model model) {
        // Get all users to display in the form
        List<User> users = userService.getAllUsers();
        // Get all courses to display in the form
        List<Course> courses = courseService.getAllCourses();
        
        model.addAttribute("users", users);
        model.addAttribute("courses", courses);
        return "add_course_to_user.jsp";
    }

    @PostMapping("/api/instructor/add-course-to-user")
    public String addCourseToUser(@RequestParam("userId") Long userId, 
                                  @RequestParam("courseId") Long courseId,
                                  HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login.jsp"; // Redirect to login page if user not logged in
        }
        
        // Retrieve the user and course from the database
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);
        
        // Check if user and course exist
        if (user == null || course == null) {
            // Handle user or course not found error
            return "error.jsp";
        }
        
        // Add the course to the user
        user.getCourses().add(course);
        userService.saveUser(user);
        
        // Redirect to a page confirming the successful addition
        return "redirect:/courseAdded.jsp";
    }



}
