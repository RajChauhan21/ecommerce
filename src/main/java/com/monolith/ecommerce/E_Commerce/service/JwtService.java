package com.monolith.ecommerce.E_Commerce.service;

import com.monolith.ecommerce.E_Commerce.DTO.LoginRequest;
import com.monolith.ecommerce.E_Commerce.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    String Secret_Key = "E6FFA5B73286CDCB2A7EF9CC7F55FNRINVU495564JHFY67674";

    public String generateToken(User user){
        Map<String, String > claims = new HashMap<>();
        String token = Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .issuer("CRX")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(secretKey())
                .compact();
        return token;
    }

    public SecretKey secretKey(){
        byte[] decodedKey = Decoders.BASE64.decode(Secret_Key);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUserNameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Claims getClaims(String token){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    public Date extractExpirationDateFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        return (!extractExpirationDateFromToken(token).before(new Date()) && userDetails.getUsername().equals(extractUserNameFromToken(token)));
    }

}
