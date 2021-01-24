package com.wwesolowski.appointmentApp.repository;

import com.wwesolowski.model.Doctor;
import com.wwesolowski.repository.DoctorDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDAOTests {

    @Autowired
    DoctorDAO doctorDAO;

    @Test
    @Transactional
    public void createDoctorTest() throws Exception {
        Doctor doctor = new Doctor("1234");
        Doctor doctorCreated = doctorDAO.save(doctor);
        Assert.assertEquals(doctor.getDoctorDigitId(), doctorCreated.getDoctorDigitId());
    }

    @Test
    @Transactional
    public void deleteDoctorTest() throws Exception {
        Doctor doctor = new Doctor("1234");
        doctorDAO.save(doctor);
        doctorDAO.delete(doctor);
        Assert.assertEquals(null, doctorDAO.findByDoctorDigitId("1234"));
    }
}
