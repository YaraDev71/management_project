package com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message){
        super(message);
    }
    public ProjectNotFoundException( String message, Throwable cause){
        super(message, cause);
    }
}
