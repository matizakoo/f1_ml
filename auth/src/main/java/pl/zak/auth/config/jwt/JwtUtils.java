package pl.zak.auth.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.zak.auth.entity.Users;
import java.util.*;

import static pl.zak.auth.config.jwt.JwtConstants.JWT_SECRET;

@Component
@AllArgsConstructor
public class JwtUtils {
    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .subject(authentication.getName())
                .claim("role", authentication.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JwtConstants.JWT_EXPIRATION))
                .signWith(JWT_SECRET)
                .compact();
    }

    public String generateToken(Users users) {
        return Jwts.builder()
                .subject(users.getUsername())
                .claim("role", users.getUserRole())
                .claim("email", users.getEmail())
                .claim("id", users.getId())
                .claim("username", users.getUsername())
                .claim("surname", users.getSurname())
                .claim("phone", users.getPhone())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JwtConstants.JWT_EXPIRATION))
                .signWith(JWT_SECRET)
                .compact();
    }

    public List<String> getRolesFromJWT(String token) {
        Claims claims = getJwtParserInstance()
                .parseSignedClaims(token)
                .getPayload();

        return Collections.singletonList(claims.get("role", String.class));
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = getJwtParserInstance()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("email", String.class);
    }

    public JwtParser getJwtParserInstance() {
        return Jwts.parser()
                .verifyWith(JWT_SECRET)
                .build();
    }

    public boolean validateToken(String token) throws Exception {
        try {
            getJwtParserInstance()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new Exception("JWT problem expired or incorrect", e);
        }
    }
}
