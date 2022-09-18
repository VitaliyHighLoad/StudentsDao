package com.example.StudentsDao.Studetns;

import com.example.StudentsDao.response.RestApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRrepository studentRepository;

    public StudentService(StudentRrepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> list(){
        return studentRepository.findAll();

//        List<Student> list = List.of(
//                new Student("Vitaliy", LocalDate.of(1990, Month.FEBRUARY, 16)),
//                new Student("Vova", LocalDate.of(2000, Month.JULY, 9))
//        );
//        return list;
    }

    public void add(Student student) {
        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new RestApiException("Email is busy");
        }
        studentRepository.save(student);
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void update(Student student) {
        if (student.getId() == null) {
            logger.info("no id in put request");
            return;
        } else {
            Optional<Student> row = studentRepository.findById(student.getId());
            if (row.isPresent()) {
                logger.info("such student is present");
                Student item = row.get();
                if (!student.getName().isEmpty()) {
                    item.setName(student.getName());
                }
                if (student.getDob() != null) {
                    item.setDob(student.getDob());
                }
                studentRepository.save(item);
            } else {
                logger.info("no such student");
            }
        }
    }
}
