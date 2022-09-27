package com.dataloader.dataprocessing.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InvalidPatientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private String patientName;
    private String patientAddress;
    private String patientDateofBirth;
    private String patientEmail;
    private String patientContactNumber;
    private String patientDrugId;
    private String patientDrugName;
    private String status;

    private String remarks;

    public InvalidPatientDetails(String patientName, String patientAddress, String patientDateofBirth, String patientEmail, String patientContactNumber, String patientDrugId, String patientDrugName, String status) {
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientDateofBirth = patientDateofBirth;
        this.patientEmail = patientEmail;
        this.patientContactNumber = patientContactNumber;
        this.patientDrugId = patientDrugId;
        this.patientDrugName = patientDrugName;
        this.status = status;
    }

    public InvalidPatientDetails(PatientDetails patientDetails) {
        this.patientName = patientDetails.getPatientName();
        this.patientAddress = patientDetails.getPatientAddress();
        this.patientDateofBirth = patientDetails.getPatientDateofBirth();
        this.patientEmail = patientDetails.getPatientEmail();
        this.patientContactNumber = patientDetails.getPatientContactNumber();
        this.patientDrugId = patientDetails.getPatientDrugId();
        this.patientDrugName = patientDetails.getPatientDrugName();
        this.status = patientDetails.getStatus();
    }




}
