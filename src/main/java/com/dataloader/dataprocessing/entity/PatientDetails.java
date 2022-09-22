package com.dataloader.dataprocessing.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDetails {

    @Id
    private Integer patientId;

    private String patientName;

    private String patientAddress;

    private String patientDateofBirth;

    private String patientEmail;

    private String patientContactNumber;

    private String patientDrugId;

    private String patientDrugName;

    private String status;

}
