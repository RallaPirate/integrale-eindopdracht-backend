package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.config.JwtUtil;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtUtil jwtUtil;

        @CrossOrigin(origins = "*")
        @PostMapping("/login")
        public String login(@RequestBody User user) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
                );
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
                return token;
            }
            catch(Error e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ongeldige email/wachtwoord combinatie");
            }
        }
    }

