package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Student;

public class StudentDAOImpl implements StudentDAO{
    private Connection connection;
    public StudentDAOImpl() throws SQLException{
        this.connection = DatabaseConnectionManager.getConnection();
    }
    public int addStudent(Student student) throws SQLException{
        String query = "INSERT INTO student (full_name, date_of_birth, contact_number,email, address) VALUES (?,?,?,?,?)";
        try(PreparedStatement statement= connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
        statement.setString(1, student.getFullName());
        statement.setDate(2, new Date(student.getDateOfBirth().getTime()));
        statement.setString(3, student.getContactNumber());
        statement.setString(4, student.getEmail());
        statement.setString(5, student.getAddress());
        statement.executeUpdate();
        ResultSet generatedKeys= statement.getGeneratedKeys();
        if(generatedKeys.next()){
            return generatedKeys.getInt(1);
        }
    } return student.getStudentId();
    }
    public Student getStudentById(int studentId) throws SQLException{
        String query="SELECT * FROM student WHERE student_id=?";
        Student student= null;
        try(PreparedStatement statement= connection.prepareStatement(query)){
        statement.setInt(1, studentId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            student=new Student(
            resultSet.getInt("student_id"),
            resultSet.getString("full_name"),
            resultSet.getDate("date_of_birth"),
            resultSet.getString("contact_number"),
            resultSet.getString("email"),
            resultSet.getString("address"));
            
        }
    }
    return student;
    }
    public void updateStudent(Student student) throws SQLException{
        String query="UPDATE student SET full_name=?, date_of_birth=?,contact_number=?, email=?,address=? WHERE student_id=?";
        try(PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, student.getFullName());
            ps.setDate(2, new Date(student.getDateOfBirth().getTime()));
            ps.setString(3, student.getContactNumber());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getAddress());
            ps.setInt(6, student.getStudentId());
            ps.executeUpdate();
        }
    }
    public void deleteStudent(int studentId) throws SQLException{
         String sql = "DELETE FROM student WHERE student_id=?";
         try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, studentId);
            ps.executeUpdate();
         }
    }
    public List<Student>  getAllStudents() throws SQLException{
        List<Student> l= new ArrayList<>();
        String sql= "SELECT * FROM student";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                l.add(new Student(
                    rs.getInt("student_id"),
                    rs.getString("full_name"),
                    rs.getDate("date_of_birth"),
                    rs.getString("contact_number"),
                    rs.getString("email"),
                    rs.getString("address")
                ));
            }
        }
        return l;
    }

}
