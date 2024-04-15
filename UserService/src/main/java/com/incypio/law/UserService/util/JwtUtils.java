package com.incypio.law.UserService.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.incypio.law.UserService.dto.UserDto;
import com.incypio.law.UserService.entity.UserEntity;
import com.incypio.law.UserService.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String key = "19970328Aa!";

    public static String createJtw(UserEntity user) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        calendar.add(Calendar.SECOND, 3600*24*7) ;
        return JWT.create() .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .withClaim("iat", now)
                .withClaim("exp", calendar.getTime())
                .sign(Algorithm.HMAC256(key));
    }

    public static UserEntity parseJwt(String token){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            Map<String, Claim> claims = decodedJWT.getClaims();
            UserEntity user = new UserEntity();
            user.setEmail(claims.get("email").asString());
            user.setId(claims.get("id").asLong());
            user.setUsername(claims.get("username").asString());
            return user;
        }catch(TokenExpiredException e ){
            throw new RuntimeException("Token expired");
        }
    }

}
