package com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){
        super(message);
    }
    public TaskNotFoundException( String message, Throwable cause){
        super(message, cause);
    }
}
