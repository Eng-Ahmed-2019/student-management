package com.ahmed.student_management.security;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "VGhpc0lzQVN1cGVyU2VjcmV0S2V5Rm9yU3R1ZGVudEFwaTIwMjY=";

    private static final long EXPIRATION =
            1000 * 60 * 60 * 24;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }


    private Claims extractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public String generateToken(UserDetails details){
        return Jwts.builder()
                    .subject(details.getUsername())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(getSigningKey())
                    .compact();
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                        &&extractAllClaims(token).getExpiration().after(new Date());
    }
}