package com.wwesolowski.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;
    @NaturalId
    @Column(name = "digit_id")
    private String doctorDigitId;
    @OneToOne
    @JoinColumn(name = "personal_id")
    private PersonalInfo personalInfo;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor() {

    }
    public Doctor(String doctorDigitId) {
        this.doctorDigitId = doctorDigitId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorDigitId() {
        return doctorDigitId;
    }

    public void setDoctorDigitId(String doctorDigitId) {
        this.doctorDigitId = doctorDigitId;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
