package dev.rest;

import dev.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Object loginUser(@RequestBody Map<String, String> requestMap) {
        try {
            String email = requestMap.get("email");
            String password = requestMap.get("password");

            if (email == null || password == null) {
                return new ResponseEntity<>("Email and password are required.", HttpStatus.BAD_REQUEST);
            }

            return authService.loginUser(email, password);
//            boolean loginSuccessful = authService.loginUser(email, password);
//            if (loginSuccessful) {
//                return new ResponseEntity<>("Login successful.", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Login failed. Invalid credentials.", HttpStatus.UNAUTHORIZED);
//            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to login.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
