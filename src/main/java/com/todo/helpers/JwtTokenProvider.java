/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import static com.todo.config.Secret.JWT_SECRET;
import static com.todo.config.Secret.TOKEN_EXPIRATION;
import com.todo.exceptions.NotFoundTokenException;
import com.todo.models.User;
import java.util.Calendar;
import java.util.Date;
import spark.Request;

/**
 *
 * @author jzuniga
 */
public class JwtTokenProvider {
    private static String HEADER_AUTH = "Authorization";
    private static String TOKEN_TYPE = "Bearer ";
    
    public static String generateToken(User u) {
        Date now = Calendar.getInstance().getTime();
        Date expireDate = new Date(now.getTime() + TOKEN_EXPIRATION);
        Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
        
        return JWT.create()
                .withClaim("idUser", u.getId())
                .withClaim("email", u.getEmail())
                .withExpiresAt(expireDate)
                .sign(alg);
    }
    
    public static boolean validateToken(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
            JWT.require(alg)
                .build()
                .verify(token);
            return true;
        } catch(JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
        
    }
    
    public static int getUserId(String token) {
       Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
       
        DecodedJWT jwtToken = JWT.require(alg)
                .build()
                .verify(token);
        Claim claim = jwtToken.getClaim("idUser");
        return claim.asInt();
    }
    
    public static boolean isValidFormatJWT(String jwtToken) {
        return jwtToken != null ? jwtToken.startsWith(TOKEN_TYPE) : false;
    }
    
    public static String extractTokenFromRequest(Request req) throws NotFoundTokenException {
        String jwtToken = req.headers(HEADER_AUTH);
        if(!isValidFormatJWT(jwtToken)) 
           throw new NotFoundTokenException("Invlaid jwt token format");
        
        return jwtToken.substring(TOKEN_TYPE.length());
    }
}
