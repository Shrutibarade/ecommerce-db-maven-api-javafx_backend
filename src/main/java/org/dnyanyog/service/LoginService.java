package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.entity.Login;
import org.dnyanyog.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepo loginRepository;

    @Autowired
    public LoginService(LoginRepo loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * ✅ Method to Add Credentials (Registration)
     */
    public LoginResponse addCredentials(LoginRequest request) {
        LoginResponse response = new LoginResponse();

        // ✅ Check if login name already exists
        Optional<Login> existingUser = loginRepository.findByLoginName(request.getLoginName());
        if (existingUser.isPresent()) {
            response.setResponseCode("400");
            response.setResponseMessage("Username already exists! Please choose another.");
            return response;
        }

        Login user = new Login();
        user.setLoginName(request.getLoginName());
        user.setPassword(request.getPassword());

        try {
            loginRepository.save(user);
            response.setResponseCode("0000");
            response.setResponseMessage("Credentials added successfully!");
        } catch (Exception e) {
            response.setResponseCode("500");
            response.setResponseMessage("Error adding credentials: " + e.getMessage());
        }

        return response;
    }

    /**
     * ✅ Validate user credentials for Login
     */
    public LoginResponse validateUser(LoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();

        Optional<Login> userOptional = loginRepository.findByLoginNameAndPassword(
            loginRequest.getLoginName(), 
            loginRequest.getPassword()
        );

        if (userOptional.isPresent()) {
            response.setResponseCode("0000");
            response.setResponseMessage("Login successful!");
        } else {
            response.setResponseCode("401");
            response.setResponseMessage("Invalid username or password.");
        }

        return response;
    }
}
