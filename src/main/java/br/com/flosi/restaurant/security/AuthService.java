package br.com.flosi.restaurant.security;

import br.com.flosi.restaurant.dtos.AuthResponseDTO;
import br.com.flosi.restaurant.dtos.LoginDTO;
import br.com.flosi.restaurant.dtos.RegisterDTO;
import br.com.flosi.restaurant.models.User;
import br.com.flosi.restaurant.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO register(RegisterDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setName(user.getName());
        response.setRole(user.getRole());
        return response;
    }

    public AuthResponseDTO login(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setName(user.getName());
        response.setRole(user.getRole());
        return response;
    }
}
