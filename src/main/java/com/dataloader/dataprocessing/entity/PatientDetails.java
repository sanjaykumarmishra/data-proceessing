package com.dataloader.dataprocessing.entity;

import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PatientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @Size(min = 5, max = 30)
    @NotEmpty(message = "Name is empty")
    private String patientName;

    @NotEmpty(message = "Address is empty")
    private String patientAddress;

    @NotEmpty(message = "DOB is empty")
    private String patientDateofBirth;

    @NotEmpty(message = "Email ID is empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email ID")
    private String patientEmail;

    @NotNull
    private String patientContactNumber;

    @NotEmpty(message = "Drug ID is empty")
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

    @NotEmpty(message = "Drug name is empty")
    private String patientDrugName;

    private String status;



}
