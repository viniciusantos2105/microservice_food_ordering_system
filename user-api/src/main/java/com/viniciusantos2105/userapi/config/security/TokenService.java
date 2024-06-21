package com.viniciusantos2105.userapi.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.viniciusantos2105.userapi.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerandoToken(User user)  {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("manuOS-API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(gerandoTempoToken())
                    .sign(algorithm);
        } catch (Exception ex) {
            //TODO: Tratar exceção
            throw new RuntimeException();
        }
    }

    public String validarToken(String token)  {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("manuOS-API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception ex) {
            //TODO: Tratar exceção
            throw new RuntimeException();
        }
    }

    private Instant gerandoTempoToken() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }

}
