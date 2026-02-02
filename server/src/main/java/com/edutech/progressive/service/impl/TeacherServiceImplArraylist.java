package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.service.TeacherService;

public class TeacherServiceImplArraylist implements TeacherService {
    private static List<Teacher> teacherList=new ArrayList<>();
    public List<Teacher> getAllTeachers(){
        return teacherList;
    }
    public Integer addTeacher(Teacher teacher){
        teacherList.add(teacher);
        return teacherList.size();

    }
    public List<Teacher> getTeacherSortedByExperience(){
        Collections.sort(teacherList);
        return teacherList;
        
    }
    public void emptyArrayList(){
        teacherList.clear();

    }

}