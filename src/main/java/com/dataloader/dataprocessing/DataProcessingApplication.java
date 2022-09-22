package com.dataloader.dataprocessing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataProcessingApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DataProcessingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("this is testing");
    }
}
