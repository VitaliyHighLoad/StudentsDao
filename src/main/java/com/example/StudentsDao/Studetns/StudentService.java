package com.example.StudentsDao.Studetns;

import com.example.StudentsDao.response.RestApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
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
        boolean existingStudent = studentRepository.existsById(studentId);
        if (!existingStudent) {
            throw new RestApiException("No student with sush id");
        }
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
                    logger.info("in block edit name");
                    item.setName(student.getName());
                }
                if (student.getDob() != null) {
                    logger.info("in block edit dob");
                    item.setDob(student.getDob());
                }
                if (student.getEmail() != null) {
                    logger.info("in block edit email");
                    item.setEmail("stub@yandex.ru");
                }
                studentRepository.save(item);
            } else {
                logger.info("no such student");
            }
        }
    }

    public void updateParam(Long studentId, String name, String email) {
        Student studentChanged = studentRepository.findById(studentId).orElseThrow(() -> new RestApiException("such id = " + studentId + " no exist in DataBase"));
        logger.info("we in method Put updateParam = " + studentChanged);
        if (name != null && name.length() >0 && !Objects.equals(studentChanged.getName(), name)) {
            logger.info("we change name on = " + name);
            studentChanged.setName(name);
        }
        if (email != null && email.length() >0 && !Objects.equals(studentChanged.getEmail(), email)) {
            logger.info("we change email on = " + email);
            studentChanged.setEmail(email);
        }
        logger.info("We save our student");
        studentRepository.save(studentChanged);
    }
}
