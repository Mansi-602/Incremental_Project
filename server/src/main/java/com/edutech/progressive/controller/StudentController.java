package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Student;
import com.edutech.progressive.service.impl.StudentServiceImplArraylist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentServiceImplArraylist studentServiceImplArraylist;
    

    public StudentController(StudentServiceImplArraylist studentServiceImplArraylist) {
        this.studentServiceImplArraylist = studentServiceImplArraylist;
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = studentServiceImplArraylist.getAllStudents();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        return null;
    }
    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        Integer a= studentServiceImplArraylist.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    public ResponseEntity<Void> updateStudent(int studentId, Student student) {
        return null;
    }

    public ResponseEntity<Void> deleteStudent(int studentId) {
        return null;
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Student>> getAllStudentFromArrayList() {
        List<Student> list = studentServiceImplArraylist.getAllStudents();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addStudentToArrayList(Student student) {
        Integer a = studentServiceImplArraylist.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Student>> getAllStudentSortedByNameFromArrayList() {
        List<Student> st = studentServiceImplArraylist.getAllStudentSortedByName();
        return ResponseEntity.ok(st);
    }
}