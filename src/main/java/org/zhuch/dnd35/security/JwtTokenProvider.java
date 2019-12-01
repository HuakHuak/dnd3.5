package org.zhuch.dnd35.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhuch.dnd35.entity.user.AppUserDetails;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    public static final PublicKey PUBLIC_KEY;
    private static final KeyPair keyPair;

    @Value("${jwt.token.access.expired}")
    private long msAccess;

    @Value("${jwt.token.refresh.expired}")
    private long msRefresh;

    @Value("${jwt.token.issuer}")
    private String issuer;

    static {
        keyPair = Keys.keyPairFor(SignatureAlgorithm.RS512);
        PUBLIC_KEY = keyPair.getPublic();
    }

    private String createToken(AppUserDetails user, long expired, String sessionId) {
        Claims claims = Jwts.claims()
                .setIssuer(issuer)
                .setSubject(user.getUsername());
        claims.put("id", user.getId());
        claims.put("roles", user.getAuthorities());
        claims.put("sessionId", sessionId);
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + expired);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public String createAccess(AppUserDetails user, String sessionId) {
        return createToken(user, msAccess, sessionId);
    }

    public String createRefresh(AppUserDetails user, String sessionId) {
        return createToken(user, msRefresh, sessionId);
    }
}
