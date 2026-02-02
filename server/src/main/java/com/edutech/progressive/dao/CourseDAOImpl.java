package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Course;

public class CourseDAOImpl implements CourseDAO{
    private Connection connection;
    
    public CourseDAOImpl()  throws SQLException{
        this.connection = DatabaseConnectionManager.getConnection();
    }
    public int addCourse(Course course)throws SQLException{
        String query = "INSERT INTO course (course_name, description, teacher_id) VALUES (?,?,?)";
        PreparedStatement statement= connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, course.getCourseName());
        statement.setString(2, course.getDescription());
        statement.setInt(3, course.getTeacherId());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if(generatedKeys.next()){
            return generatedKeys.getInt(1);
        }
        else{
            throw new SQLException("Failed to retrieve generated course ID");
        }
    }
    public Course getCourseById(int courseId) throws SQLException{
        String query= "SELECT * FROM course WHERE course_id=?";
        PreparedStatement statement= connection.prepareStatement(query);
        statement.setInt(1, courseId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Course course = new Course();
            course.setCourseId(resultSet.getInt("course_id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setTeacherId(resultSet.getInt("teacher_id"));
            return course;
        }else{
            return null;
        }
    }
    public void updateCourse(Course course) throws SQLException{
        String query= "UPDATE course SET course_name=?, description=? WHERE  course_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, course.getCourseName());
        statement.setString(2, course.getDescription());
        statement.setInt(3, course.getCourseId());
        statement.executeUpdate();
    }
    public void deleteCourse(int courseId) throws SQLException{
        String query = "DELETE FROM course WHERE course_id=?";
        PreparedStatement statement= connection.prepareStatement(query);
        statement.setInt(1,courseId);
        statement.executeUpdate();

    }
    public List<Course>  getAllCourses() throws SQLException{
        String query = "SELECT * FROM course";
        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(query);
        List<Course> courses= new ArrayList<>();
        while(resultSet.next()){
            Course course= new Course();
            course.setCourseId(resultSet.getInt("course_id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setDescription(resultSet.getString("description"));
            course.setTeacherId(resultSet.getInt("teacher_id"));
            courses.add(course);
        }
        return courses;
        
    }

}
