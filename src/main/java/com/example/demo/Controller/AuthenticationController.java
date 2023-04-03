package com.example.demo.Controller;

import com.example.demo.JwtAuthentication.AuthenticationService;
import com.example.demo.requestResponseModels.AuthenticationRequest;
import com.example.demo.requestResponseModels.AuthenticationResponse;
import com.example.demo.requestResponseModels.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody AuthenticationRequest request) {
        //TODO How to invalidate a JWT?
        return ResponseEntity.internalServerError().build();
    }
}
