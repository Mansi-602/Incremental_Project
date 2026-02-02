package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.CourseDAO;
import com.edutech.progressive.dao.CourseDAOImpl;
import com.edutech.progressive.entity.Course;
import com.edutech.progressive.service.CourseService;

public class CourseServiceImplJdbc implements CourseService {
    CourseDAOImpl courseDAOImpl;
    
    
    public CourseServiceImplJdbc(CourseDAOImpl courseDAOImpl) {
        this.courseDAOImpl = courseDAOImpl;
    }
    public List<Course> getAllCourses() throws Exception{
        try{
        return courseDAOImpl.getAllCourses();
        }catch(SQLException e){
            throw new Exception("Unable to fetch courses",e);
        }
        
    }
    public Course getCourseById(int courseId)throws Exception{
        try{
        return courseDAOImpl.getCourseById(courseId);
        }catch(SQLException e){
            throw new Exception("Failed to add course",e);
        }
    }
    public Integer addCourse(Course course) throws Exception{
        try{
        return courseDAOImpl.addCourse(course);
        }catch(SQLException e){
            throw new Exception("Failed to add course",e);
        }
    }
    public void updateCourse(Course course) throws Exception{
        try{
        courseDAOImpl.updateCourse(course);
        }catch(SQLException e){
            throw new Exception("Failed to update course",e);
        }

    }
    public void deleteCourse(int courseId) throws Exception{
        try{
        courseDAOImpl.deleteCourse(courseId);
        }catch(SQLException e){
            throw new Exception("Failed to delete course",e);
        }
    }
    

}