package com.dataloader.dataprocessing.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    private String patientName;

    private String patientAddress;

    private String patientDateofBirth;

    private String patientEmail;


    private String patientContactNumber;

    private String patientDrugId;

    public PatientDetails(String patientName, String patientAddress, String patientDateofBirth, String patientEmail, String patientContactNumber, String patientDrugId, String patientDrugName, String status) {
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientDateofBirth = patientDateofBirth;
        this.patientEmail = patientEmail;
        this.patientContactNumber = patientContactNumber;
        this.patientDrugId = patientDrugId;
        this.patientDrugName = patientDrugName;
        this.status = status;
    }

    private String patientDrugName;

    private String status;



}
