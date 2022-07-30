package com.vvk.learn.edu.studentmanagementapp.controllers;

import com.vvk.learn.edu.studentmanagementapp.data.Student;
import com.vvk.learn.edu.studentmanagementapp.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {

    private final StudentService studentService;

    public WebServiceController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam(name = "filter", required = false) String lastName) {
        List<Student> students;
        students = (lastName == null) ? studentService.getAllStudents() : studentService.getStudentsByLastName(lastName);
        return students;
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student){
        this.studentService.addStudent(student);
    }

    @DeleteMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@RequestBody String id){
        this.studentService.deleteStudent(Long.parseLong(id));
    }

    @PatchMapping("/admin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editStudent(@RequestBody String student){
        this.studentService.editStudent(student);
    }

}
