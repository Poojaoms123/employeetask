package com.example.demo11.Repository;

import com.example.demo11.Model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "select * from demo11_Student where Student_is_deleted=false",nativeQuery = true)
    Page<Student> getAllByDeleted(Pageable pageable);
    Student findByStudentId( Long Student);

    @Modifying
    @Transactional
   @Query(value = "delete from demo11_Student where Student_id=:studentId ",nativeQuery = true)
   void deleteByStudentId(Long studentId);



    @Query(value = "select s from Student as s where s.studentEmail like %:studentEmail% and s.studentName like %:studentName% and s.studentIsDeleted=false order by s.studentCreatedAt desc")
    Page<Student> getAllByStudentNameAndStudentEmail(String studentName, String studentEmail, Pageable pageable);


    @Query(value = "select s from Student as s where  s.studentMobileNo like %:studentMobileNo% and s.studentEmail like %:studentEmail% and s.studentIsDeleted=false  order  by s.studentCreatedAt desc ")
    Page<Student> getAllByStudentEmailAndStudentMobileNo(String studentEmail, String studentMobileNo, Pageable pageable);

    @Query(value = " select s from Student as s where s.studentMobileNo like %:studentMobileNo% and s.studentEmail like %:studentEmail% and s.studentName like %:studentName% and s.studentIsDeleted=false order by s.studentCreatedAt desc ")
    Page<Student> getAllByStudentNameAndStudentEmailAndStudentMobileNo(String studentName, String studentEmail, String studentMobileNo, Pageable pageable);

    @Query(value = " select s from Student as s where s.studentMobileNo like %:studentMobileNo% and s.studentName like %:studentName% and s.studentIsDeleted=false order by s.studentCreatedAt desc ")
    Page<Student> getAllByStudentNameAndStudentMobileNo(String studentName, String studentMobileNo, Pageable pageable);

    @Query(value = "select s from Student as s where s.studentName like %:studentName% and s.studentIsDeleted =false order by s.studentCreatedAt desc ")
    Page<Student> getAllByStudentName(String studentName, Pageable pageable);

    @Query(value = " select s from Student as s where s.studentEmail like %:studentEmail% and s.studentIsDeleted=false order by s.studentCreatedAt desc ")
    Page<Student> getAllByStudentEmail(String studentEmail, Pageable pageable);

    @Query(value = "select s from Student as s where s.studentMobileNo like %:studentMobileNo% and s.studentIsDeleted=false order by s.studentCreatedAt desc ")
    Page<Student> getAllByStudentMobileNo(String studentMobileNo, Pageable pageable);

    Page<Student> findAllByStudentName(String studentName, Pageable pageable);
}
