package org.zhuch.dnd35.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtTest {

    @Test
    void testJwt() {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS512);
        String jws = Jwts.builder()
                .setSubject("Subject")
                .setIssuer("org.zhuch")
                .signWith(keyPair.getPrivate())
                .compact();
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println(jws);
        assertTrue(Jwts.parser().setSigningKey(keyPair.getPublic())
                .isSigned(jws));
        assertEquals("org.zhuch", Jwts.parser().setSigningKey(keyPair.getPublic())
                .parseClaimsJws(jws).getBody().getIssuer());
    }
}
