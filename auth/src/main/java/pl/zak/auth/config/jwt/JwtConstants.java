package pl.zak.auth.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtConstants {
    public static final int  JWT_EXPIRATION = 360000000;
    public static final MacAlgorithm JWT_ALGORITHM = Jwts.SIG.HS256;
    public static final SecretKey JWT_SECRET = JWT_ALGORITHM.key().build();
}