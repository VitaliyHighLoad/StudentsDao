package com.example.StudentsDao.Studetns;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    public CommandLineRunner initStart(StudentRrepository studentRepository){
        return args -> {
            studentRepository.saveAll(List.of(
                new Student("Sergey", "xx1@gmail.com", LocalDate.of(1990, Month.FEBRUARY, 16)),
                new Student("Vova", "xx2@gmail.com", LocalDate.of(2000, Month.JULY, 9))));
        };
    }
}
