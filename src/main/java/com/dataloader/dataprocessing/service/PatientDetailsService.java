package com.dataloader.dataprocessing.service;

import com.dataloader.dataprocessing.entity.InvalidPatientDetails;
import com.dataloader.dataprocessing.entity.PatientDetails;
import com.dataloader.dataprocessing.exceptions.InvalidDateException;
import com.dataloader.dataprocessing.helper.Helper;
import com.dataloader.dataprocessing.repo.InvalidPatientDetailsRepo;
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

    private InvalidPatientDetails invalidPatientDetails = null;

    @Autowired
    private InvalidPatientDetailsRepo invalidPatientDetailsRepo;

    @Autowired
    private Helper helper;

    public void save(MultipartFile file) {

        try {
            List<PatientDetails> patientDetails = helper.convertExcelToListOfPatientDetails(file.getInputStream());
//            System.out.println("KATE KATE");
//            System.out.println(patientDetails);
            patientDetails.forEach(x-> System.out.println(x));
//            try {
//                patientDetails.forEach(patientDetail -> this.patientDetailsRepo.save(patientDetail));
//            } catch (ConstraintViolationException e) {
//                System.out.println("Validation error jizzzz");
//            }

            for(PatientDetails patientDetail : patientDetails) {
                try {
//                    System.out.println("Date Check for "+patientDetail.getPatientName()+":");
                    helper.validateDate(patientDetail.getPatientDateofBirth());
                    patientDetail.setStatus("INDUCTED");
                    this.patientDetailsRepo.save(patientDetail);
                } catch (ConstraintViolationException e) {
//                    System.out.println("ConstraintViolationException for "+patientDetail.getPatientName()+": "+e.getMessage());
                    if(invalidPatientDetailsRepo.findByPatientContactNumber(patientDetail.getPatientContactNumber())==null) {
                        invalidPatientDetails = new InvalidPatientDetails(patientDetail);
                        invalidPatientDetails.setStatus("FAILED");
                        e.getConstraintViolations().stream().forEach(x -> invalidPatientDetails.setRemarks(x.getMessage()));
                        e.getConstraintViolations().stream().forEach(x -> System.out.println(x.getMessage()));
                        System.out.println(e.getConstraintViolations().size());
                        this.invalidPatientDetailsRepo.save(invalidPatientDetails);
                    }
                } catch (InvalidDateException e) {
//                    System.out.println("Date Constraint Violation for "+patientDetail.getPatientName()+": "+e.getMessage());
                    if(invalidPatientDetailsRepo.findByPatientContactNumber(patientDetail.getPatientContactNumber())==null) {
                        invalidPatientDetails = new InvalidPatientDetails(patientDetail);
                        invalidPatientDetails.setStatus("FAILED");
                        invalidPatientDetails.setRemarks(e.getMessage());
                        System.out.println(e.getMessage());
                        this.invalidPatientDetailsRepo.save(invalidPatientDetails);
                    }
                } catch (ParseException e) {
//                    System.out.println("Date Constraint Violation for "+patientDetail.getPatientName()+": "+" Invalid Date Format");
                    if(invalidPatientDetailsRepo.findByPatientContactNumber(patientDetail.getPatientContactNumber())==null) {
                        invalidPatientDetails = new InvalidPatientDetails(patientDetail);
                        invalidPatientDetails.setStatus("FAILED");
                        invalidPatientDetails.setRemarks("Invalid Date Pattern: Format -> MM/dd/yyyy");
                        System.out.println("Invalid Date Pattern: Format -> MM/dd/yyyy");
                        this.invalidPatientDetailsRepo.save(invalidPatientDetails);
                    }
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
