package ru.gb.market.auth.configs;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class  JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Integer jwtLifetime;

    public String generateToken(UserDetails userDetails, Long userId, List<String> privileges) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("userID", String.valueOf(userId));
        claims.put("roles", roleList);
        claims.put("privileges", privileges);


        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

//    public String getUsernameFromToken(String token) {
//        return getClaimsFromToken(token, Claims::getSubject);
//    }
//
//    public List<String> getRolesFromToken(String token) {
//        return getClaimsFromToken(token, (Function<Claims, List<String>>) claims -> claims.get("roles", List.class));
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
//        Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }


}
