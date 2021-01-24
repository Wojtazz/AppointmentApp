package com.wwesolowski.appointmentApp.service;

import com.wwesolowski.model.Appointment;
import com.wwesolowski.model.Customer;
import com.wwesolowski.model.Doctor;
import com.wwesolowski.repository.AppointmentDAO;
import com.wwesolowski.repository.CustomerDAO;
import com.wwesolowski.repository.DoctorDAO;
import com.wwesolowski.service.AppointmentService;
import com.wwesolowski.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTests {

    @Autowired
    CustomerDAO customerDao;
    @Autowired
    DoctorDAO doctorDAO;
    @Autowired
    AppointmentDAO appointmentDAO;
    @Autowired
    AppointmentService appointmentService;

    @Test
    @Transactional
    public void getDoctorAppointmentsOfDayTest() throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        Customer customer = new Customer("7890", encoder.encodeToString("7890".getBytes()));
        Doctor doctor = new Doctor("7890");
        customerDao.save(customer);
        doctorDAO.save(doctor);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(df.parse("2010-01-04 01:00:00 UTC"));
        appointment.setCustomer(customer);
        appointment.setDoctor(doctor);
        appointmentDAO.save(appointment);

        List<Appointment> doctorAppointmentsOfDay = appointmentService.getDoctorAppointmentsOfDay("7890", df1.parse("2010-01-04"));
        Assert.assertNotEquals(null, doctorAppointmentsOfDay);
    }
}
