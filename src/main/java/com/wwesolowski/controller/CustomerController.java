package com.wwesolowski.controller;

import com.wwesolowski.dto.AppointmentDto;
import com.wwesolowski.dto.CancelAppointmentDto;
import com.wwesolowski.exceptions.*;
import com.wwesolowski.model.Appointment;
import com.wwesolowski.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createAppointment")
    public ResponseEntity createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        try {
            Appointment appointment = customerService.createAppointment(appointmentDto.getCustomerId(),
                    appointmentDto.getDoctorId(),
                    appointmentDto.getCustomerPin(),
                    appointmentDto.getAppointment());
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch (CustomerNotFoundException | DoctorNotFoundException | AppointmentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (PinDoesNotMatchException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (DoctorReservedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/cancelAppointment")
    public ResponseEntity cancelAppointment(@Valid @RequestBody CancelAppointmentDto cancelAppointmentDto) {
        try {
            Appointment appointment = customerService.cancelAppointment(cancelAppointmentDto.getCustomerId(),
                    cancelAppointmentDto.getCustomerPin(),
                    cancelAppointmentDto.getAppointmentDate());
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch (CustomerNotFoundException | AppointmentNotFoundException | AppointmentsEmptyException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (PinDoesNotMatchException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
