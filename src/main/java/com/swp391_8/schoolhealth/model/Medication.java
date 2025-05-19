package com.swp391_8.schoolhealth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private int quantity;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private MedicationType medicationType;

    public enum MedicationType {
        SCHOOL_SUPPLY,
        STUDENT_SUPPLY
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student; // only for STUDENT_SUPPLY

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User providedBy; // only for STUDENT_SUPPLY
}