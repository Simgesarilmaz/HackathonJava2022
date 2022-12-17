package com.example.springbootjwt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TokenManager {
    private static final String secretKey="SpringBootJwt";
    private static final int validity=5*60*1000;
    public String genarateToken(String username)
    {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("springBootJwt")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+validity,))
                .signWith(SignatureAlgorithm.ES256,secretKey)
                .compact();
    }
    public boolean tokenValidate(String token)
    {
        if((getUsernameToken(token) != null) && isExpired(token))
            return true;
        return false;
    }
    public String getUserFromToken(String token){
       Claims claims= Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean isExpired(String token)
    {
        Claims claims= Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

}
