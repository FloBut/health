//package com.example.health.services;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.example.health.entities.Doctor;
//import com.example.health.entities.Patient;
//import com.example.health.entities.User;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class JwtTokenService {
//    private Algorithm hmac512;
//    private JWTVerifier verifier;
//    public static final long JWT_TOKEN_VALIDITY = 2222222L;
//
//    public JwtTokenService(@Value("${jwt.secret}") String secret) {
//        this.hmac512 = Algorithm.HMAC512(secret);
//        this.verifier = JWT.require(hmac512).build();
//    }
//    public String generateToken(UserDetails userDetails) {
//        return JWT.create()
//                .withSubject(userDetails.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .sign(hmac512);
//    }
//    public String validateToken (String token) {
//        try {
//            return verifier.verify(token).getSubject();
//        } catch (JWTVerificationException e) {
//            // Token-ul nu este valid sau expirat
//            return null;
//        }
//    }
////    private String getUserType(User user) {
////        if (user instanceof Doctor) {
////            return "DOCTOR";
////        } else if (user instanceof Patient) {
////            return "PATIENT";
////        } else {
////            return "USER"; // Default or handle other user types
////        }
////    }
//}
