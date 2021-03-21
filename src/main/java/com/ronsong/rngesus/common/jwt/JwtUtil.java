package com.ronsong.rngesus.common.jwt;

import cn.hutool.extra.tokenizer.TokenizerException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class JwtUtil {
    public static final long EXPIRATION_TIME = 7 * 24 * 36_000_000L;
    public static final String SECRET = "HauntingMelody";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_NAME = "userName";

    private JwtUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateToken(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(USER_NAME, userId);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static HttpServletRequest validateTokenAndAddUserIdToHeader(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER_STRING);
        if (token != null) {
            try {
                Map<String, Object> body = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                return new NewHttpServletRequestWrapper(httpServletRequest, body);
            } catch (Exception e) {
                throw new TokenizerException(e.getMessage());
            }
        } else {
            throw new TokenizerException("Missing token");
        }
    }

    public static class NewHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private Map<String, String> claims;

        public NewHttpServletRequestWrapper(HttpServletRequest request, Map<String, ?> claims) {
            super(request);

            this.claims = new HashMap<>();
            claims.forEach((k, v) -> this.claims.put(k, String.valueOf(v)));
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (claims != null && claims.containsKey(name)) {
                return Collections.enumeration(Arrays.asList(claims.get(name)));
            }
            return super.getHeaders(name);
        }

        static class TokenValidationException extends RuntimeException {
            public TokenValidationException(String msg) {
                super(msg);
            }
        }
    }
}
