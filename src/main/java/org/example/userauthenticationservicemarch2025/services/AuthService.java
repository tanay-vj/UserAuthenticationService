package org.example.userauthenticationservicemarch2025.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservicemarch2025.exceptions.IncorrectPasswordException;
import org.example.userauthenticationservicemarch2025.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservicemarch2025.exceptions.UserNotFoundException;
import org.example.userauthenticationservicemarch2025.models.User;
import org.example.userauthenticationservicemarch2025.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretKey secretKey;


    @Override
    public User signup(String email, String password) {
        Optional<User> userOptional = userRepo.findUserByEmailId(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setEmailId(email);
//        user.setPassword(password);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }

    @Override
    public Pair<User, String> login(String email, String password) {
        Optional<User> userOptional = userRepo.findUserByEmailId(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        String storedPassword = userOptional.get().getPassword();
        if(!bCryptPasswordEncoder.matches(password, storedPassword)) {
//        if(!password.equals(storedPassword)) {
            throw new IncorrectPasswordException("Please pass correct password, else reset it.");
        }
//        return userOptional.get();

        //Generating Token
//        String message = "{\n" +
//                "   \"email\": \"tanay@gmail.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"buddy\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"2ndApril2025\"\n" + "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);
        Map<String,Object> payload = new HashMap<>();
        payload.put("userId", userOptional.get().getId());
        Long currentTime = System.currentTimeMillis();
        payload.put("iat", currentTime); //issuedAt
        payload.put("exp", currentTime + 100000);
        payload.put("iss", "scaler");
        //To generate Signature
//        MacAlgorithm algorithm = Jwts.SIG.HS256;
//        SecretKey secretKey = algorithm.key().build();

        String token = Jwts.builder().claims(payload).signWith(secretKey).compact();
        return new Pair<User,String>(userOptional.get(), token);
    }

    public Boolean validateToken(String token, Long userId) {
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
            Claims claims = jwtParser.parseClaimsJws(token).getPayload();
            String newToken = Jwts.builder().claims(claims).signWith(secretKey).compact();

            if(!token.equals(newToken)) {
                System.out.println("Invalid token");
                throw new RuntimeException("Invalid token");
            }

            Long expiry = (Long) claims.get("exp");
            Long currentTime = System.currentTimeMillis();
            if(currentTime > expiry) {
                System.out.println("Token is expired.");
                throw new RuntimeException("Token is expired.");
            }
        return true;
        } catch(Exception e) {
            throw e;
        }
    }
}
