package nl.spplatform.sppapi.controllers;

//import nl.spplatform.sppapi.config.JwtUtil;
//import nl.spplatform.sppapi.dtos.AuthResponseDTO;
//import nl.spplatform.sppapi.models.Profile;
//import nl.spplatform.sppapi.models.User;
//import nl.spplatform.sppapi.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import nl.spplatform.sppapi.Security.JwtService;
import nl.spplatform.sppapi.dtos.AuthResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.spplatform.sppapi.dtos.AuthRequestDTO;
import org.springframework.web.server.ResponseStatusException;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.UserRepository;
import nl.spplatform.sppapi.models.Profile;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager man, JwtService service, UserRepository userRepository) {
        this.authManager = man;
        this.jwtService = service;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequestDTO
    ) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword());

        try {
            Authentication auth = authManager.authenticate(up);

            var ud = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);

            AuthResponseDTO response = new AuthResponseDTO();

            User loggedInUser = userRepository.findByEmail(authRequestDTO.getEmail())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gebruiker niet gevonden"));
            Profile profile = loggedInUser.getProfile();
                if(profile == null){
                    response.setProfileId(-5L);
                }
                else{
                response.setProfileId(profile.getProfileId());
                }
                response.setToken(token);
                response.setUserId(loggedInUser.getUserId());
                response.setRole(loggedInUser.getRole());
                return ResponseEntity.ok(response);
            }
            catch(Error e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ongeldige email/wachtwoord combinatie");
        }
    }
}

//@RestController
//    @RequestMapping("/api/auth")
//    public class AuthController {
//
//        @Autowired
//        private AuthenticationManager authenticationManager;
//
//        @Autowired
//        private UserRepository userRepository;
//
//        @Autowired
//        private JwtUtil jwtUtil;
//
////        @CrossOrigin(origins = "*")
//        @PostMapping("/login")
//        public ResponseEntity<AuthResponseDTO> login(@RequestBody User user) {
//            try {
//                Authentication authentication = authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
//                );
//                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
//                AuthResponseDTO response = new AuthResponseDTO();
//                User loggedInUser = userRepository.findByEmail(user.getEmail())
//                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gebruiker niet gevonden"));
//                Profile profile = loggedInUser.getProfile();
//                if(profile == null){
//                    response.setProfileId(-5L);
//                }
//                else{
//                response.setProfileId(profile.getProfileId());}
//                response.setToken(token);
//                response.setUserId(loggedInUser.getUserId());
//                response.setRole(loggedInUser.getRole());
//                return ResponseEntity.ok(response);
//            }
//            catch(Error e) {
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ongeldige email/wachtwoord combinatie");
//            }
//        }
//    @GetMapping("/api/whoami")
//    public ResponseEntity<String> whoami(@RequestHeader("Authorization") String auth) {
//        return ResponseEntity.ok("Got token: " + auth);
//    }
//    }
//
