package com.dataloader.dataprocessing;

import com.dataloader.dataprocessing.repo.PatientDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataProcessingApplication implements CommandLineRunner {

    @Autowired
    PatientDetailsRepo p;
    public static void main(String[] args) {
        SpringApplication.run(DataProcessingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("this is testing");
//        System.out.println(p.findDistinctByPatientContactNumber("7662254815"));
//        System.out.println(p.findByPatientContactNumber("7662254815"));
    }


}
