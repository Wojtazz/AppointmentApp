package com.wwesolowski.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @NaturalId
    @Column(name = "digit_id")
    private String customerDigitId;
    @Column(name = "customer_pin")
    private String customerPin;
    @OneToOne
    @JoinColumn(name = "personal_id")
    private PersonalInfo personalInfo;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<>();

    public Customer() {

    }

    public Customer(String customerDigitId, String customerPin) {
        this.customerDigitId = customerDigitId;
        this.customerPin = customerPin;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerDigitId() {
        return customerDigitId;
    }

    public void setCustomerDigitId(String customerDigitId) {
        this.customerDigitId = customerDigitId;
    }

    public String getCustomerPin() {
        return customerPin;
    }

    public void setCustomerPin(String customerPin) {
        this.customerPin = customerPin;
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
