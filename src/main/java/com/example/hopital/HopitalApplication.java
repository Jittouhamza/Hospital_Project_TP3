package com.example.hopital;

import com.example.hopital.entities.Patient;
import com.example.hopital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HopitalApplication {

    public static void main(String[] args) {

        SpringApplication.run(HopitalApplication.class, args);
    }
    //@Bean
    public CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            Patient p1 = new Patient(); // NoArgsConstructor
            p1.setNom("Someone");
            p1.setPrenom("someone");
            p1.setScore(1000);
            p1.setMalade(false);
            p1.setDateNaissance(new Date());

            Patient p2 = new Patient(null,"Yassine","Med",new Date(),1200,false); //AllArgsConstructor

            Patient p3 = Patient.builder() // Builder
                    .nom("Imane")
                    .dateNaissance(new Date())
                    .score(2000)
                    .build();
            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);

            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p->{
                System.out.println(p.toString());
            });
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
