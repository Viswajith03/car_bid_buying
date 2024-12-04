package com.fullstackproject.services.auth;

import com.fullstackproject.dto.SignupRequest;
import com.fullstackproject.dto.UserDTO;

public interface AuthService {

    UserDTO signup(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
