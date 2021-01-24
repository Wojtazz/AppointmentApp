package com.wwesolowski.controller;

import com.wwesolowski.dto.DoctorAppointmentsDto;
import com.wwesolowski.exceptions.DateNotFoundException;
import com.wwesolowski.exceptions.DoctorNotFoundException;
import com.wwesolowski.model.Appointment;
import com.wwesolowski.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/doctorAppointments")
    public ResponseEntity getDoctorAppointmentsOfDay(@Valid @RequestBody DoctorAppointmentsDto doctorAppointmentsDto) {
        try {
            List<Appointment> doctorAppointments = appointmentService.getDoctorAppointmentsOfDay(doctorAppointmentsDto.getDoctorId(),
                    doctorAppointmentsDto.getAppointmentDate());
            return ResponseEntity.status(HttpStatus.OK).body(doctorAppointments);
        } catch (DoctorNotFoundException | DateNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
