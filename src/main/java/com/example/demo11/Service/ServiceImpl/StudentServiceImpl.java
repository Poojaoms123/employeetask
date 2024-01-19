package com.example.demo11.Service.ServiceImpl;

import com.example.demo11.Model.Response.pageDTO;
import com.example.demo11.Model.SaveRequest.SaveStudentRequest;
import com.example.demo11.Model.Student;
import com.example.demo11.Repository.StudentRepository;
import com.example.demo11.Service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;


    @Override
    public String saveOrUpdateStudent(SaveStudentRequest saveStudentRequest) {
        if (studentRepository.existsById(saveStudentRequest.getStudentId())) {
            Student student = studentRepository.findById(saveStudentRequest.getStudentId()).get();
            student.setStudentName(saveStudentRequest.getStudentName());
            student.setStudentEmail(saveStudentRequest.getStudentEmail());
            student.setStudentMobileNo(saveStudentRequest.getStudentMobileNo());
            student.setStudentIsDeleted(false);
            student.setStudentIsavtive(true);
            studentRepository.save(student);
            return "update Sucessfully";
        } else {
            Student student = new Student();
            student.setStudentName(saveStudentRequest.getStudentName());
            student.setStudentEmail((saveStudentRequest.getStudentEmail()));
            student.setStudentMobileNo(saveStudentRequest.getStudentMobileNo());
            student.setStudentIsDeleted(false);
            student.setStudentIsavtive(true);
            studentRepository.save(student);
            return "save sucessfully";
        }
    }

    @Override
    public Object getById(Long studentId) throws Exception {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.findById(studentId).get();
            return student;
        } else {
            throw new Exception("student not found");
        }
    }

    @Override
    public Object deleteById(Long studentId) throws Exception {
        if (studentRepository.existsById(studentId)) {
         /*   Student student=studentRepository.findById(studentId).get();
            student.setStudentIsDeleted(false);
            studentRepository.save(student);
            return "Deleted sucessfully";*/
            studentRepository.deleteByStudentId(studentId);
            return "Deleted sucessfully";
        } else {
            throw new Exception("Student not found");
        }
    }

    @Override
    public Object changestatus(Long studentId) throws Exception {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.findByStudentId(studentId);
            if (student.getStudentIsavtive()) {
                student.setStudentIsavtive(true);
                return "student active";
            } else {
                student.setStudentIsavtive(false);
                return "student inactive";
            }
        } else {
            throw new Exception("student not exits");
        }
    }

    @Override
    public Object getAllByDeleted(String studentName, Pageable pageable) {
        Page<Student> students;
        if (studentName != null && !studentName.isEmpty()) {
            students = studentRepository.findAllByStudentName(studentName, pageable);
        } else {
            students = studentRepository.getAllByDeleted(pageable);
        }
        return new pageDTO(students.getContent(), students.getTotalElements(), students.getNumber(), students.getTotalPages());
    }

    @Override
    public Object getAllByDeleted(String studentName, String studentEmail, String studentMobileNo, Pageable pageable) {
        Page<Student> students;
        if (StringUtils.isNotBlank(studentName)) {
            studentName = studentName.toLowerCase();
        }
        if (StringUtils.isNotBlank(studentEmail)) {
            studentEmail = studentEmail.toLowerCase();
        }
        if (StringUtils.isNotBlank(studentMobileNo)) {
            studentMobileNo = studentMobileNo.toLowerCase();
        }
        if (StringUtils.isNotBlank(studentName) && StringUtils.isBlank(studentEmail) && StringUtils.isBlank(studentMobileNo)) {
            System.out.println("Search By StudentName");
            students = studentRepository.getAllByStudentName(studentName, pageable);
        } else if (StringUtils.isBlank(studentName) && StringUtils.isNotBlank(studentEmail) && StringUtils.isBlank(studentMobileNo)) {
            System.out.println("Search By StudentEmail");
            students = studentRepository.getAllByStudentEmail(studentEmail, pageable);
        } else if (StringUtils.isBlank(studentName) && StringUtils.isBlank(studentEmail) && StringUtils.isNotBlank(studentMobileNo)) {
            System.out.println("Search By StudentMobileNo");
            students = studentRepository.getAllByStudentMobileNo(studentMobileNo, pageable);
        } else if (StringUtils.isNotBlank(studentName) && StringUtils.isNotBlank(studentEmail) && StringUtils.isNotBlank(studentMobileNo)) {
            System.out.println("Search By StudentNameAndStudentEmail");
            students = studentRepository.getAllByStudentNameAndStudentEmail(studentName, studentEmail, pageable);
        } else if (StringUtils.isNotBlank(studentName) && StringUtils.isBlank(studentEmail) && StringUtils.isNotBlank(studentMobileNo)) {
            System.out.println("Search By StudentNameAndStudentMobileNo");
            students = studentRepository.getAllByStudentNameAndStudentMobileNo(studentName, studentMobileNo, pageable);
        } else if (StringUtils.isBlank(studentName) && StringUtils.isNotBlank(studentEmail) && StringUtils.isNotBlank(studentMobileNo)) {
            System.out.println("Search By StudentEmailAndStudentMobileNo");
            students = studentRepository.getAllByStudentEmailAndStudentMobileNo(studentEmail, studentMobileNo, pageable);
        } else if (StringUtils.isNotBlank(studentName) && StringUtils.isNotBlank(studentEmail) && StringUtils.isNotBlank(studentMobileNo)) {
            System.out.println("search By StudentNameAndStudentEmailAndStudentMobileNo");
            students = studentRepository.getAllByStudentNameAndStudentEmailAndStudentMobileNo(studentName, studentEmail, studentMobileNo, pageable);
        } else {
            students = studentRepository.getAllByDeleted(pageable);
        }
        return new pageDTO(students.getContent(), students.getTotalElements(), students.getNumber(), students.getTotalPages());
    }
}
