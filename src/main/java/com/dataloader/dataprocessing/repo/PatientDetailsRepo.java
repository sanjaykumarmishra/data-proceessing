package com.dataloader.dataprocessing.repo;

import com.dataloader.dataprocessing.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientDetailsRepo extends JpaRepository<PatientDetails,Integer> {

}
