package pl.zak.auth.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.zak.auth.dto.UserCredentialsDTO;
import pl.zak.auth.entity.Users;

import java.util.*;

@Component
public class JwtUtils {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .subject(authentication.getName())
                .claim("role", authentication.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JwtConstants.JWT_EXPIRATION))
                .signWith(JwtConstants.JWT_SECRET)
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
                .signWith(JwtConstants.JWT_SECRET, JwtConstants.JWT_ALGORITHM)
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
                .parseSignedClaims(token) // Nowa metoda w jjwt-0.12.1
                .getPayload();
        return claims.get("email", String.class);
    }

    public JwtParser getJwtParserInstance() {
        return Jwts.parser()
                .verifyWith(JwtConstants.JWT_SECRET) // `setSigningKey()` zastąpiono w jjwt-0.12.1
                .build();
    }

    public boolean validateToken(String token) throws Exception {
        try {
            getJwtParserInstance()
                    .parseSignedClaims(token); // Nowa metoda w jjwt-0.12.1
            return true;
        } catch (Exception e) {
            throw new Exception("JWT problem expired or incorrect", e);
        }
    }

    public String getJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> CustomAuthorizationHeader.AUTHORIZATION_HEADER.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    public Authentication getAuthentication(UserCredentialsDTO userCredentialsDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(),
                        userCredentialsDTO.getPassword()));
    }
}
