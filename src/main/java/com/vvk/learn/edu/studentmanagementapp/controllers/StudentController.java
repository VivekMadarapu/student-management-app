package com.vvk.learn.edu.studentmanagementapp.controllers;

import com.vvk.learn.edu.studentmanagementapp.data.Student;
import com.vvk.learn.edu.studentmanagementapp.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudents(@RequestParam(name = "filter", required = false) String lastName, Model model){
        List<Student> students;
        students = (lastName == null) ? studentService.getAllStudents() : studentService.getStudentsByLastName(lastName);
        model.addAttribute("students", students);
        return "studentview";
    }
}
