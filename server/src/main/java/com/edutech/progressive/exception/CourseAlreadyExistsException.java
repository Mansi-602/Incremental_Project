package com.edutech.progressive.exception;

public class CourseAlreadyExistsException extends Exception{
    public CourseAlreadyExistsException(String message){
        super(message);
    }
}