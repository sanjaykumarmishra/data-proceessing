package com.dataloader.dataprocessing.controller;

import com.dataloader.dataprocessing.entity.PatientDetails;
import com.dataloader.dataprocessing.helper.Helper;
import com.dataloader.dataprocessing.service.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class PatientDetailsController {

    @Autowired
    private PatientDetailsService patientDetailsService;

    @PostMapping("/patientDetails/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (Helper.checkExcelFormat(file)) {
            //true
            this.patientDetailsService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/patientDetails")
    public List<PatientDetails> getAllProduct() {
        return this.patientDetailsService.getAllPatientDetails();
    }

}
