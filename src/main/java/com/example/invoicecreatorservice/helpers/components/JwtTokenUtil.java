package com.example.invoicecreatorservice.helpers.components;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.invoicecreatorservice.objects.models.JwtUserDetails;
import com.example.invoicecreatorservice.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
    private final transient JwtTokenService jwtTokenService = new JwtTokenService();

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = (long) 1 * (60 * 60);

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public int getCompanyFromToken(String token){
        final Claims claims = getAllClaimsFromToken(token);
        return (int) claims.get("companyId");
    }

    public int getUserFromToken(String token){
        final Claims claims = getAllClaimsFromToken(token);
        return (int) claims.get("userId");
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token, String ip) {
        final Date expiration = getExpirationDateFromToken(token);
        boolean expired = expiration.before(new Date());

        if(expired){
            jwtTokenService.setExpiredToken(token, ip);
        }

        return expired;
    }

    //generate token for user
    public String generateToken(JwtUserDetails userDetails, String ip) {
        Map<String, Object> claims = new HashMap<>();
        String token = doGenerateToken(claims, userDetails.getUsername(), userDetails.getCompanyId(), userDetails.getId());

        jwtTokenService.addNewToken(token, ip);

        return token;
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject, int companyId, int userId) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .claim("companyId", companyId)
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails, String ip) {
        boolean exists = jwtTokenService.validateToken(token, ip);
        final String username = getUsernameFromToken(token);
        return (exists && username.equals(userDetails.getUsername()) && !isTokenExpired(token, ip));
    }
}