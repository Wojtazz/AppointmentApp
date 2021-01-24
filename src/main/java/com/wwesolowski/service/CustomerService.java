package com.wwesolowski.service;

import com.wwesolowski.exceptions.*;
import com.wwesolowski.model.Appointment;
import com.wwesolowski.model.Customer;
import com.wwesolowski.model.Doctor;
import com.wwesolowski.repository.AppointmentDAO;
import com.wwesolowski.repository.CustomerDAO;
import com.wwesolowski.repository.DoctorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    Base64.Decoder pinDecoder = Base64.getDecoder();

    public Appointment createAppointment(String customerDigitId,
                                         String doctorDigitId,
                                         String customerPin,
                                         Appointment appointment) throws Exception {
        Customer customer = customerDAO.findByCustomerDigitId(customerDigitId);
        Doctor doctor = doctorDAO.findByDoctorDigitId(doctorDigitId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + customerDigitId + " is not found");
        }
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with id " + doctorDigitId + " is not found");
        }
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment cannot be null");
        }
        byte[] decodedBytesOfPin = pinDecoder.decode(customer.getCustomerPin());

        if (!customerPin.equals(new String(decodedBytesOfPin))) {
            throw new PinDoesNotMatchException("Pin " + customerPin + " doesn't match with customer pin");
        }
        Appointment doctorAppointment;
        doctorAppointment = appointmentDAO.findByDoctorAndAppointmentDate(doctor, appointment.getAppointmentDate());

        if (doctorAppointment != null) {
            throw new DoctorReservedException("Doctor with id " + doctorDigitId + " is already reserved at specific date");
        }
        appointment.setCustomer(customer);
        appointment.setDoctor(doctor);
        appointmentDAO.save(appointment);
        customer.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);
        customerDAO.save(customer);
        doctorDAO.save(doctor);

        return appointment;
    }

    public Appointment cancelAppointment(String customerDigitId,
                                         String customerPin,
                                         Date appointmentDate) throws Exception {

        Customer customer = customerDAO.findByCustomerDigitId(customerDigitId);
        Doctor doctor;
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + customerDigitId + " is not found");
        }
        byte[] decodedBytesOfPin = pinDecoder.decode(customer.getCustomerPin());
        if (!customerPin.equals(new String(decodedBytesOfPin))) {
            throw new PinDoesNotMatchException("Pin " + customerPin + " doesn't match with customer pin");
        }
        Appointment canceledAppointment = appointmentDAO.findByCustomerAndAppointmentDate(customer, appointmentDate);

        if (canceledAppointment == null) {
            throw new AppointmentNotFoundException("Appointment of customer " + customerDigitId + " not found");
        }
        doctor = doctorDAO.findById(canceledAppointment.getDoctor().getDoctorId()).orElse(null);
        customer.getAppointments().remove(canceledAppointment);
        doctor.getAppointments().remove(canceledAppointment);
        customerDAO.save(customer);
        doctorDAO.save(doctor);

        return canceledAppointment;
    }
}
