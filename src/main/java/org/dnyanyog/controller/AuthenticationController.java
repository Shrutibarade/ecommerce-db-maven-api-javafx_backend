package org.dnyanyog.controller;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    /**
     * API to add user credentials (Registration)
     */
    @PostMapping("/addCredentials")
    public LoginResponse addCredentials(@RequestBody LoginRequest loginRequest) {
        return loginService.addCredentials(loginRequest);
    }

    /**
     * API to validate user login credentials
     */
    @PostMapping(path="/validate", consumes={"application/json", "application/xml"}, produces={"application/json", "application/xml"})
    public LoginResponse validate(@RequestBody LoginRequest loginRequest) {
        return loginService.validateUser(loginRequest);
    }
}
