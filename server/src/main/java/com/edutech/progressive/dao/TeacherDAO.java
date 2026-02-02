package com.edutech.progressive.dao;

import com.edutech.progressive.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    int addTeacher(Teacher teacher) throws Exception;
    Teacher getTeacherById(int teacherId) throws Exception;
    void updateTeacher(Teacher teacher)throws Exception;
    void deleteTeacher(int teacherId) throws Exception ;
    List<Teacher> getAllTeachers() throws Exception;
}
