package com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(String message){
        super(message);
    }
    public ActivityNotFoundException( String message, Throwable cause){
        super(message, cause);
    }
}
