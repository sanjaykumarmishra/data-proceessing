package com.dataloader.dataprocessing.service;

import com.dataloader.dataprocessing.entity.PatientDetails;
import com.dataloader.dataprocessing.helper.Helper;
import com.dataloader.dataprocessing.repo.PatientDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientDetailsService {

    @Autowired
    private PatientDetailsRepo patientDetailsRepo;

    @Autowired
    private Helper helper;

    public void save(MultipartFile file) {

        try {
            List<PatientDetails> patientDetails = helper.convertExcelToListOfPatientDetails(file.getInputStream());
            System.out.println(patientDetails);
            this.patientDetailsRepo.saveAll(patientDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<PatientDetails> getAllPatientDetails() {
        return this.patientDetailsRepo.findAll();
    }


}
