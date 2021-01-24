package com.wwesolowski.repository;

import com.wwesolowski.model.Appointment;
import com.wwesolowski.model.Customer;
import com.wwesolowski.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentDAO extends CrudRepository<Appointment, Long> {
    Appointment findByCustomerAndAppointmentDate(Customer customer, Date appointmentDate);

    List<Appointment> findByDoctor(Doctor doctor);

    Appointment findByDoctorAndAppointmentDate(Doctor doctor, Date appointmentDate);

    Appointment findByAppointmentDate(Date appointmentDate);
}
