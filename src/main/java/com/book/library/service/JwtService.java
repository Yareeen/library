package com.book.library.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtService {

    @Value("${jwt.key}")
    private String SECRET;

    public String generateToken(String userName) {   //Tokenı keyle şifreliyorum.
       // Map<String, Object> claims = new HashMap<>();  //jwtnin içerdiği bilgileri saklamak için
        //claims.put("yaren", "can");

        return Jwts.builder()
                //.setClaims(userName) // JWT için özel talepleri ayarlamak için kullanılır.
                .setSubject(userName) //isim
                .setIssuedAt(new Date(System.currentTimeMillis())) //token ne zaman üretildi
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2)) //geçersiz olma süresi // 2 dakika
                .signWith(getSignKey(), SignatureAlgorithm.HS256) //keyi alır.
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) { //tokenın geçerliliğine bakar.
        String username = extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());
    }

    private Date extractExpiration(String token) { //expired değeri döndürür
        return extractClaims(token).getExpiration();
    }

    public String extractUser(String token) { //user değeri döndürür.
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody(); //payload
    }

    private Key getSignKey() { //bu secretla şifrelenir.
        byte[] keyBytes = Decoders.BASE64.decode(SECRET); //yukarıda oluşturduğumuz secret
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
