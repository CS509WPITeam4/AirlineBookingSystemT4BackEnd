package com.cs509team4.AirlineReservation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {
    private static final String SECRET_KEY = "CS509Team4SuperSecretKeyForJWTGeneration123456";
    private static final byte[] SECRET_BYTES = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    private static final String ALG = SignatureAlgorithm.HS256.getJcaName();
    private static final SecretKeySpec KEY = new SecretKeySpec(SECRET_BYTES, ALG);

    @Test
    void testGenerateAndValidateToken() {
        JwtUtil jwtUtil = new JwtUtil();
        User user = new User("testuser", "user@example.com", "password");

        String token = jwtUtil.generateToken(user);
        assertNotNull(token, "Token should not be null");

        // Should be valid
        assertTrue(jwtUtil.validateToken(token), "Generated token should be valid");

        // And extractEmail() should match
        assertEquals("user@example.com", jwtUtil.extractEmail(token),
                "Extracted email should match the subject");
    }

    @Test
    void testExpiredTokenIsInvalid() {
        // Manually create an expired token with the same key
        String expiredToken = Jwts.builder()
                .setSubject("user@example.com")
                .setIssuedAt(new Date(System.currentTimeMillis() - 10_000))
                .setExpiration(new Date(System.currentTimeMillis() - 1))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();

        JwtUtil jwtUtil = new JwtUtil();
        assertFalse(jwtUtil.validateToken(expiredToken),
                "Expired token should not be valid");
    }
}
