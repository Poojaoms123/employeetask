package com.example.demo11.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "demo11_Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "student_mobile_no")
    private String studentMobileNo;

    @Column(name = "student_is_deleted")
    private Boolean studentIsDeleted;

    @Column(name = "student_is_active")
    private Boolean studentIsavtive;

    @CreationTimestamp
    @Column(name = "student_created_at",updatable = false)
    private LocalDateTime studentCreatedAt;

    @UpdateTimestamp
    @Column(name = "student_updated_at")
    private LocalDateTime studentUpdatedAt;


}
