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
public class PatientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;


//    @Size(min = 5, max = 30, message = "Name should have a min length of 5 and max length of 30")
    @Pattern(regexp = "^[a-zA-Z ]{5,30}", message = "Name min-len:5 max-len:30, only alpahbets and spaces")
    private String patientName;

    @NotEmpty(message = "Address is empty")
    private String patientAddress;

    @NotEmpty(message = "DOB is empty")
    private String patientDateofBirth;


//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "MM/dd/yyyy")
//    private Date patientDateofBirth;

//    @NotEmpty(message = "Email ID is empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email ID")
    private String patientEmail;

    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "Should be a valid 10 digit number")
    private String patientContactNumber;

//    @NotEmpty(message = "Drug ID is empty")
    @Pattern(regexp = "^[\\d]{5}-[\\d]{4}-[\\d]{2}$", message = "Drug ID should be of format XXXX-XXXX-XX, all digits")
    private String patientDrugId;


    @NotEmpty(message = "Drug name is empty")
    private String patientDrugName;

    private String status;

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





}
