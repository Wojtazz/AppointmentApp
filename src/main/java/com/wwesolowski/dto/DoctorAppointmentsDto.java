package com.wwesolowski.dto;

import java.util.Date;

public class DoctorAppointmentsDto {

    private String doctorId;

    private Date appointmentDate;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
