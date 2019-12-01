package org.zhuch.dnd35.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.zhuch.dnd35.exception.JwtAuthenticationException;

import java.util.Date;

import static org.zhuch.dnd35.security.JwtTokenProvider.PUBLIC_KEY;

@Component
public class JwtTokenValidator {

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(PUBLIC_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    private static String getUsername(String token) {
        return Jwts.parser().setSigningKey(PUBLIC_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
