package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.AuthResponseDTO;
import br.com.flosi.restaurant.dtos.LoginDTO;
import br.com.flosi.restaurant.dtos.RegisterDTO;
import br.com.flosi.restaurant.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterDTO dto) {
        return  ResponseEntity.status(201).body(authService.register(dto));
    }
}
