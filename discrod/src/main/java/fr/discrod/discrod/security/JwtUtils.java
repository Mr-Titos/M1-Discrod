package fr.discrod.discrod.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {
    private static final String KEY = "19D6IjLAudjoZMxFHnp/Owq2SKapi7JRqGhUo82TrAMF9JBz7ATG4SnDLulvQqI2";
    //private static final String KEY = RandomStringUtils.randomAlphanumeric(64);

    private JwtUtils() { }

    public static String generate(String username) {
        // Création de la clé de signature
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
        Date now = new Date();

        return Jwts.builder()
                .setSubject("username")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 36000000))
                .claim("username", username)
                .signWith(key)
                .compact();
    }

    public static boolean isValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        }

        catch (Exception ex) {
            return false;
        }
    }
}