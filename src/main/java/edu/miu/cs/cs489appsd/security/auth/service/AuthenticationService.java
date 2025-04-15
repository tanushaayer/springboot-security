package edu.miu.cs.cs489appsd.security.auth.service;

import edu.miu.cs.cs489appsd.security.auth.dto.AuthRequest;
import edu.miu.cs.cs489appsd.security.auth.dto.AuthResponse;
import edu.miu.cs.cs489appsd.security.auth.dto.RegisterRequest;
import edu.miu.cs.cs489appsd.security.config.JwtService;
import edu.miu.cs.cs489appsd.security.user.User;
import edu.miu.cs.cs489appsd.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest authRequest) {
         Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.username(),
                        authRequest.password()
                )
        );
        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        //create user obj
        User user =new  User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.role()
        );

        //save it in db
        User registeredUser = userRepository.save(user);

        //generate token for this user
        String token = jwtService.generateToken(registeredUser);

        return new AuthResponse(token);
    }
}
