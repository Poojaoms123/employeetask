package com.example.demo11.Service;

import com.example.demo11.Model.SaveRequest.SaveStudentRequest;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    String saveOrUpdateStudent(SaveStudentRequest saveStudentRequest);

    Object getById(Long studentId) throws Exception;

    Object deleteById(Long studentId) throws Exception;

    Object changestatus(Long studentId)throws Exception;

    Object getAllByDeleted(String studentName, Pageable pageable);

    Object getAllByDeleted(String studentName, String studentEmail, String studentMobileNo, Pageable pageable);
}
