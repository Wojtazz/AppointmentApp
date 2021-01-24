package com.wwesolowski.repository;

import com.wwesolowski.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorDAO extends CrudRepository<Doctor, Long> {
    Doctor findByDoctorDigitId(String doctorDigitId);
}
