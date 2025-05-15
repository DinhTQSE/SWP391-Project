package com.cheertran.schoolhealth.service;

import com.cheertran.schoolhealth.model.Role;
import com.cheertran.schoolhealth.model.Role.ERole;
import com.cheertran.schoolhealth.model.User;
import com.cheertran.schoolhealth.repository.RoleRepository;
import com.cheertran.schoolhealth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User registerUser(String username, String password, String fullName, String email, String phone) {
        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);

        // Set default role (PARENT)
        Set<Role> roles = new HashSet<>();
        Role parentRole = roleRepository.findByName(ERole.ROLE_PARENT)
                .orElseThrow(() -> new RuntimeException("Error: Role PARENT is not found."));
        roles.add(parentRole);
        user.setRoles(roles);

        // Save user
        return userRepository.save(user);
    }
}