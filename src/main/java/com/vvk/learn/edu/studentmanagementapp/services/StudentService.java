package com.vvk.learn.edu.studentmanagementapp.services;

import com.vvk.learn.edu.studentmanagementapp.data.Student;
import com.vvk.learn.edu.studentmanagementapp.data.StudentRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        List<Student> students = this.studentRepository.findAll();
        students.sort((o1, o2) -> {
            if (o1.getLastName().equals(o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return students;
    }

    public List<Student> getStudentsByLastName(String name){
        Iterable<Student> s = this.studentRepository.findStudentsByLastName(name);
        List<Student> students = new ArrayList<>();
        s.forEach(students::add);
        students.sort(Comparator.comparing(Student::getFirstName));
        return students;
    }

    public void addStudent(Student student){
        if(student != null){
            this.studentRepository.save(student);
        }
        else{
            throw new NullPointerException("Invalid student received");
        }
    }

    public void deleteStudent(Long id){
        if(id != null){
            this.studentRepository.deleteById(id);
        }
        else{
            throw new NullPointerException("Invalid student received");
        }
    }

    public void editStudent(String jsonString){

        JSONObject jsonObject = new JSONObject(jsonString);
        Student student = this.studentRepository.getReferenceById(Long.valueOf(jsonObject.getString("studentId")));
        if (!jsonObject.getString("firstName").equals("")) {
            student.setFirstName(jsonObject.getString("firstName"));
        }
        if (!jsonObject.getString("lastName").equals("")) {
            student.setLastName(jsonObject.getString("lastName"));
        }
        if (!jsonObject.getString("emailAddress").equals("")) {
            student.setEmailAddress(jsonObject.getString("emailAddress"));
        }
        if (!jsonObject.getString("address").equals("")) {
            student.setAddress(jsonObject.getString("address"));
        }
        if (!jsonObject.getString("country").equals("")) {
            student.setCountry(jsonObject.getString("country"));
        }
        if (!jsonObject.getString("state").equals("")) {
            student.setState(jsonObject.getString("state"));
        }
        if (!jsonObject.getString("phoneNumber").equals("")) {
            student.setPhoneNumber(jsonObject.getString("phoneNumber"));
        }
        this.studentRepository.save(student);
    }

}
