package com.wwesolowski.service;

import com.wwesolowski.exceptions.DateNotFoundException;
import com.wwesolowski.exceptions.DoctorNotFoundException;
import com.wwesolowski.model.Appointment;
import com.wwesolowski.model.Doctor;
import com.wwesolowski.repository.AppointmentDAO;
import com.wwesolowski.repository.DoctorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private DoctorDAO doctorDAO;

    public List<Appointment> getDoctorAppointmentsOfDay(String doctorDigitId, Date appointmentDate) throws Exception {

        List<Appointment> doctorAppointments;
        List<Appointment> doctorAppointmentsOfDay = new ArrayList<>();
        SimpleDateFormat dayDateFormat = new SimpleDateFormat("yyyyMMdd");
        Doctor doctor = doctorDAO.findByDoctorDigitId(doctorDigitId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with id " + doctorDigitId + " is not found");
        }
        if (appointmentDate == null) {
            throw new DateNotFoundException("Appointment date cannot be null");
        }
        doctorAppointments = appointmentDAO.findByDoctor(doctor);

        for (Appointment appointment : doctorAppointments) {
            if (dayDateFormat.format(appointmentDate).equals(dayDateFormat.format(appointment.getAppointmentDate()))) {
                doctorAppointmentsOfDay.add(appointment);
            }
        }
        return doctorAppointmentsOfDay;
    }

}
