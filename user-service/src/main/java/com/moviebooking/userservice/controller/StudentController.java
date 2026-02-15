package com.moviebooking.userservice.controller;


import com.moviebooking.userservice.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(
            List.of(
                    new Student(1, "Vikash", 60),
                    new Student(2, "Katiyar", 75)
            ));


    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getStudents() {
        return students;
    }


    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

}
