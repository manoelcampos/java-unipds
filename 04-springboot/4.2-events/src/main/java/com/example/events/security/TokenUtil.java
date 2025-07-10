package com.example.events.security;

import com.example.events.dto.MyToken;
import com.example.events.model.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Date;

/**
 * @author Manoel Campos
 */
public class TokenUtil {
    private static final String ISSUER = "Manoel Campos";
    private static final long EXPIRATION_MILLIS = 60 * 60 * 1000;
    private static final String SECRET_KEY = "123456adsflk201384012374290xklhaiy31412lahsdfa";

    public static MyToken encode(final UserAccount userAccount) {
            final var key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            final String jwtToken = Jwts.builder()
                    .subject(userAccount.getUsername())
                    .expiration(new Date(System.currentTimeMillis()+EXPIRATION_MILLIS))
                    .issuer(ISSUER)
                    .signWith(key)
                    .compact();
            return new MyToken(jwtToken);
    }

    public static Authentication decode(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        System.out.println("Header: " + header);
        if(header != null){
            final String token = header.replace("Bearer ", "");
            final var key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            final var parser = Jwts.parser().verifyWith(key).build();
            final var claims = (Claims)parser.parse(token).getPayload();

            if(claims.getIssuer().equals(ISSUER) &&
            !claims.getSubject().isBlank() &&
            claims.getExpiration().after(new Date())) {
                return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.emptyList());
            }
        }

        return null;
    }
}
