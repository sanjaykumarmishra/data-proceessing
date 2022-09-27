package com.dataloader.dataprocessing.service;

import com.dataloader.dataprocessing.entity.PatientDetails;
import com.dataloader.dataprocessing.exceptions.InvalidDateException;
import com.dataloader.dataprocessing.helper.Helper;
import com.dataloader.dataprocessing.repo.PatientDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.text.ParseException;
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
//            System.out.println("KATE KATE");
            System.out.println(patientDetails);
//            try {
//                patientDetails.forEach(patientDetail -> this.patientDetailsRepo.save(patientDetail));
//            } catch (ConstraintViolationException e) {
//                System.out.println("Validation error jizzzz");
//            }

            for(PatientDetails patientDetail : patientDetails) {
                try {
//                    System.out.println("Date Check for "+patientDetail.getPatientName()+":");
                    helper.validateDate(patientDetail.getPatientDateofBirth());
                    this.patientDetailsRepo.save(patientDetail);
                } catch (ConstraintViolationException e) {
                    System.out.println("ConstraintViolationException for "+patientDetail.getPatientName()+": "+e.getMessage());
                } catch (InvalidDateException e) {
                    System.out.println("Date Constraint Violation for "+patientDetail.getPatientName()+": "+e.getMessage());
                } catch (ParseException e) {
                    System.out.println("Date Constraint Violation for "+patientDetail.getPatientName()+": "+" Invalid Date Format");
                }
            }

//            try {
//                this.patientDetailsRepo.saveAll(patientDetails);
//            } catch (ConstraintViolationException e) {
//                System.out.println(e.getMessage());
//            }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<PatientDetails> getAllPatientDetails() {
        return this.patientDetailsRepo.findAll();
    }


}
