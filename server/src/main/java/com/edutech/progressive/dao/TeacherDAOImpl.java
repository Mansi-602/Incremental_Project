package com.edutech.progressive.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Teacher;

public class TeacherDAOImpl implements TeacherDAO {
    private Connection connection;
    
    public TeacherDAOImpl() throws SQLException {
        this.connection = DatabaseConnectionManager.getConnection();
    }
    public int addTeacher(Teacher teacher) throws SQLException{
        String sql =" INSERT INTO teacher(full_name, subject, contact_number, email,years_of_experience) values(?,?,?,?,?)";
        try(PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,teacher.getFullName());
            ps.setString(2, teacher.getSubject());
            ps.setString(3, teacher.getContactNumber());
            ps.setString(4, teacher.getEmail());
            ps.setInt(5, teacher.getYearsOfExperience());
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                teacher.setTeacherId(rs.getInt(1));
            }
        }
        return teacher.getTeacherId();
    }
    public Teacher getTeacherById(int teacherId) throws SQLException{
        String sql= "SELECT * FROM teacher WHERE teacher_id=?";
        Teacher teacher=null;
        try(PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setInt(1, teacherId);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                teacher= new Teacher(
                rs.getInt("teacher_id"),
                rs.getString("full_name"),
                rs.getString("subject"),
                rs.getString("contact_number"),
                rs.getString("email"),
                rs.getInt("years_of_experience"));
            }
        }
        return teacher;
    }
    public void updateTeacher(Teacher teacher) throws SQLException{
        String sql="UPDATE teacher set full_name=?, subject=?, contact_number=?, email=?,years_of_experience=? WHERE teacher_id=?";
        try(PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setString(1, teacher.getFullName());
            ps.setString(2, teacher.getSubject());
            ps.setString(3, teacher.getContactNumber());
            ps.setString(4, teacher.getEmail());
            ps.setInt(5, teacher.getYearsOfExperience());
            ps.setInt(6, teacher.getTeacherId());
            ps.executeUpdate();
        }

    }
    public void deleteTeacher(int teacherId) throws SQLException{
        String sql= "DELETE FROM teacher WHERE teacher_id=?";
        try(PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setInt(1,teacherId);
            ps.executeUpdate();
        }

    }
    public List<Teacher>  getAllTeachers() throws SQLException{
        List<Teacher> l=new ArrayList<>();
        String sql="SELECT * FROM teacher";
        try(PreparedStatement ps= connection.prepareStatement(sql)){
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                l.add(new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("full_name"),
                    rs.getString("subject"),
                    rs.getString("contact_number"),
                    rs.getString("email"),
                    rs.getInt("years_of_experience")
                ));
            }
        }
        return l;
        
    }



}
