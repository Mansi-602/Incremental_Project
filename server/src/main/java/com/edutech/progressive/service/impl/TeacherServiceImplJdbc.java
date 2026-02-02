package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.edutech.progressive.dao.TeacherDAO;
import com.edutech.progressive.dao.TeacherDAOImpl;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.service.TeacherService;

public class TeacherServiceImplJdbc implements TeacherService{
    TeacherDAOImpl teacherDAO;
    
    public TeacherServiceImplJdbc(TeacherDAOImpl teacherDAO) {
        this.teacherDAO = teacherDAO;
    }
    public List<Teacher> getAllTeachers() throws Exception{
        try{
            return teacherDAO.getAllTeachers();
        }catch(SQLException e){
            throw new Exception("Unable to fetch teachers", e);
        }
    }
    public Integer addTeacher(Teacher teacher) throws Exception{
         try{
            return teacherDAO.addTeacher(teacher);
        }catch(SQLException e){
            throw new Exception("Unable to add teachers", e);
        }
    }
    public List<Teacher> getTeacherSortedByExperience() throws Exception{
         try{
            List<Teacher> l= teacherDAO.getAllTeachers();
            l.sort(Comparator.comparingInt(Teacher:: getYearsOfExperience));
            return l;
        }catch(SQLException e){
            throw new Exception("Sorting failed", e);
        }
    }
    public void updateTeacher(Teacher teacher) throws Exception{
         try{
            teacherDAO.updateTeacher(teacher);
        }catch(SQLException e){
            throw new Exception("Unable to update teacher", e);
        }
    }
    public void deleteTeacher(int teacherId) throws Exception{
         try{
            teacherDAO.deleteTeacher(teacherId);
        }catch(SQLException e){
            throw new Exception("Unable to add teacher", e);
        }
    }
    public Teacher getTeacherById(int teacherId) throws Exception{
         try{
            return teacherDAO.getTeacherById(teacherId);
        }catch(SQLException e){
            throw new Exception("Unable to get teacher", e);
        }
    }

}