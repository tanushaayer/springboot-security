package edu.miu.cs.cs489appsd.security.auth.controller;

import edu.miu.cs.cs489appsd.security.auth.dto.AuthRequest;
import edu.miu.cs.cs489appsd.security.auth.dto.AuthResponse;
import edu.miu.cs.cs489appsd.security.auth.dto.RegisterRequest;
import edu.miu.cs.cs489appsd.security.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    public final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return  ResponseEntity.status(HttpStatus.CREATED).body (authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(authRequest));
    }


}
