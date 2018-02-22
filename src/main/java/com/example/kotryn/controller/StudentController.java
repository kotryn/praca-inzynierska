package com.example.kotryn.controller;

import com.example.kotryn.entity.Student;
import com.example.kotryn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> findAllUsers() {
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudent(@RequestBody Student addStudentRequest) {
        Student student = new Student();
        student.setName(addStudentRequest.getName());
        studentRepository.save(student);
    }
}
