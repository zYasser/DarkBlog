package com.DarkBlog.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtTokenVerifier {
    private final String token;
    private final String key;
    private JWTVerifier verifier;
    private Algorithm algorithm;

    public JwtTokenVerifier(String token, String key) {
        this.token = token;
        this.key = key;
        buildVerify();
    }

    private void buildVerify() {
         algorithm = Algorithm.HMAC256(key.getBytes());
        verifier= JWT.require(algorithm).build();

    }

    public DecodedJWT getDecodedJWT() {
        return verifier.verify(token);
    }
}
