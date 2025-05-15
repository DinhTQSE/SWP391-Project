package com.cheertran.schoolhealth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vaccineName;

    @Column(nullable = false)
    private LocalDate vaccinationDate;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "health_record_id")
    private HealthRecord healthRecord;

    @ManyToOne
    @JoinColumn(name = "vaccination_event_id")
    private VaccinationEvent vaccinationEvent;
}