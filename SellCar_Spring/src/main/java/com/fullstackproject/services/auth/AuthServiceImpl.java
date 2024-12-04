package com.fullstackproject.services.auth;

import com.fullstackproject.dto.SignupRequest;
import com.fullstackproject.dto.UserDTO;
import com.fullstackproject.entities.User;
import com.fullstackproject.enums.UserRole;
import com.fullstackproject.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;


    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> optionalAdmin = userRepository.findByUserRole(UserRole.ADMIN);
        if(optionalAdmin.isEmpty()){
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@test.com");
            admin.setUserRole(UserRole.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(admin);
            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exist!");
        }
    }

    @Override
    public UserDTO signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        return userRepository.save(user).getUserDTO();
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
