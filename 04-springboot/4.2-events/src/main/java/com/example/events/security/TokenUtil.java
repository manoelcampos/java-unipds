package com.example.events.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

/**
 * @author Manoel Campos
 */
public class TokenUtil {
    public static Authentication decore(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null){
            String token = header.replace("Bearer ", "");
            if(token.equals("security123")){
                return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
            }
        }

        return null;
    }
}
