package com.cheertran.schoolhealth.service;

import com.cheertran.schoolhealth.model.Student;
import com.cheertran.schoolhealth.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean isParentOfStudent(Authentication authentication, Long studentId) {
        if (authentication == null || studentId == null) {
            return false;
        }

        // Get the authenticated user's ID
        Long parentId = null;
        try {
            parentId = (Long) authentication.getPrincipal().getClass().getMethod("getId").invoke(authentication.getPrincipal());
        } catch (Exception e) {
            return false;
        }

        // Check if the student belongs to this parent
        Optional<Student> student = studentRepository.findById(studentId);
        return student.isPresent() && student.get().getParent() != null && student.get().getParent().getId().equals(parentId);
    }
}
