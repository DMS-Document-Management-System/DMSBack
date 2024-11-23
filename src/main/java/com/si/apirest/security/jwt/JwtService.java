package com.si.apirest.security.jwt;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.apirest.entity.Person;
import com.si.apirest.service.PermissionService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "RXMgbGEgY2xhdmUgZGVsIHByb3llY3RvIHRpZW5kYSAyMDIz";
    @Autowired
    private final PermissionService permissionService;

    public String getToken(Person user) {
        Map<String, Object> claims  = new HashMap<>();
        claims.put("id", user.getId());
        List<String> permisos = permissionService.userPermissionList(user.getUsuario());
        claims.put("Permisos", permisos);
        if (user.getTenant() != null) {
            claims.put("tenant_id", Long.toString(user.getTenant().getId()));
        }
        return getToken(claims,user);
    }

    private String getToken(Map<String, Object> extractClaims, Person user)
        throws InvalidKeyException{

        return Jwts
        .builder()
        .claims(extractClaims)
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
        .signWith(getKey())
        .compact()
        ;
    }


    private SecretKey getKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, Optional<Person> userDet) {
        final String username = getUsernameFromToken(token);
        return userDet.map(person -> username.equals(person.getUsername()) && !isTokenExpired(token))
        .orElse(false);
    }

    private Claims getAllClaims(String token) {
        return Jwts
        .parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public String getTenantIdFromToken(String token) {
        final Claims claims = getAllClaims(token);
        String tenantId = claims.get("tenant_id", String.class);
        return tenantId;
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}
