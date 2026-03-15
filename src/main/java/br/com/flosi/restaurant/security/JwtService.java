package br.com.flosi.restaurant.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // Lê os valores do application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Converte a String secret em uma chave criptográfica real
    // O algoritmo HS256 exige um objeto SecretKey, não uma String pura
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera o token JWT para um usuário
    // .subject()  → identifica o dono do token (usamos o email como identificador único)
    // .claim()    → dados extras que queremos guardar dentro do token
    // .issuedAt() → timestamp de criação
    // .expiration → timestamp de expiração (agora + 24h)
    // .signWith() → assina com nossa chave secreta para garantir integridade
    public String generateToken(br.com.flosi.restaurant.models.User user) {
        return Jwts.builder()
            .subject(user.getEmail())
            .claim("role", user.getRole().name())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey())
            .compact();
    }

    // Extrai o email (subject) de dentro do token
    // parseSignedClaims() já valida a assinatura — se alguém adulterou o token, lança exceção
    public String extractEmail(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    // Verifica se o token é válido:
    // 1. Extrai o email do token
    // 2. Compara com o email do usuário que está tentando se autenticar
    // 3. Verifica se ainda não expirou
    public boolean isTokenValid(String token, br.com.flosi.restaurant.models.User user) {
        String email = extractEmail(token);
        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }

    // Verifica se a data de expiração do token já passou
    private boolean isTokenExpired(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration()
            .before(new Date());
    }
}
