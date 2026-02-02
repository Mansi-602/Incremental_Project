package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.edutech.progressive.dao.StudentDAO;
import com.edutech.progressive.dao.StudentDAOImpl;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.service.StudentService;

public class StudentServiceImplJdbc implements StudentService {
    StudentDAOImpl studentDAOImpl;
    
    public StudentServiceImplJdbc(StudentDAOImpl studentDAOImpl) {
        this.studentDAOImpl = studentDAOImpl;
    }
    public List<Student> getAllStudents() throws Exception{
        try{
            return studentDAOImpl.getAllStudents();
        }catch(SQLException e){
            throw new Exception("Unable to fetch students", e);
        }
    }
    public Integer addStudent(Student student) throws Exception{
        try{
            return studentDAOImpl.addStudent(student);
                }catch(SQLException e){
                    throw new Exception("Unable to add student", e);
                }
    }
    public List<Student>  getAllStudentSortedByName() throws Exception{
        try{
            List<Student> l = studentDAOImpl.getAllStudents();
            l.sort(Comparator.comparing(Student:: getFullName));
            return l;
        }catch(SQLException e){
            throw new Exception("Sorting failed", e);
        }
    }
    public void updateStudent(Student student) throws Exception{
        try{
            studentDAOImpl.updateStudent(student);
        }catch(SQLException e){
            throw new Exception("Unable to update student", e);
        }
    }
    public void deleteStudent(int studentId) throws Exception{
        try{
            studentDAOImpl.deleteStudent(studentId);
        }catch(SQLException e){
            throw new Exception("Unable to delete student", e);
        }

    }
    public Student getStudentById(int studentId) throws Exception{
        try{
            return studentDAOImpl.getStudentById(studentId);
        }catch(SQLException e){
            throw new Exception("Unable to get student", e);
        }
    }
   

}