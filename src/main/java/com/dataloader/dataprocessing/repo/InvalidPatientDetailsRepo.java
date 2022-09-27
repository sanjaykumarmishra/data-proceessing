package com.dataloader.dataprocessing.repo;

import com.dataloader.dataprocessing.entity.InvalidPatientDetails;
import com.dataloader.dataprocessing.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvalidPatientDetailsRepo extends JpaRepository<InvalidPatientDetails,Integer> {
    InvalidPatientDetails findByPatientContactNumber(String patientContactNumber);
}
