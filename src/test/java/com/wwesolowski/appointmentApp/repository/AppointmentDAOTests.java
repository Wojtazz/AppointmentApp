package com.wwesolowski.appointmentApp.repository;

import com.wwesolowski.model.Appointment;
import com.wwesolowski.repository.AppointmentDAO;
import com.wwesolowski.repository.CustomerDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentDAOTests {

    @Autowired
    AppointmentDAO appointmentDAO;

    @Test
    @Transactional
    public void createAppointmentTest() throws Exception {
        Appointment appointment = new Appointment();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        appointment.setAppointmentDate(df.parse("2010-01-04 01:32:27 UTC"));
        Appointment createdAppointment = appointmentDAO.save(appointment);
        Assert.assertEquals(appointment.getAppointmentId(), createdAppointment.getAppointmentId());
    }

    @Test
    @Transactional
    public void deleteAppointmentTest() throws Exception {
        Appointment appointment = new Appointment();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        appointment.setAppointmentDate(df.parse("2010-01-04 01:32:27 UTC"));
        appointmentDAO.save(appointment);
        appointmentDAO.delete(appointment);
        Assert.assertEquals(null, appointmentDAO.findByAppointmentDate(df.parse("2010-01-04 01:32:27 UTC")));
    }
}
