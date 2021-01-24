package com.wwesolowski.exceptions;

public class AppointmentNotFoundException extends  Exception{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
