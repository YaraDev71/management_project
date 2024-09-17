package com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException( String message, Throwable cause){
        super(message, cause);
    }
}
