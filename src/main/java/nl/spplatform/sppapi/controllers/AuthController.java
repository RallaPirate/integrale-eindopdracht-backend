package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.Security.JwtService;
import nl.spplatform.sppapi.dtos.AuthRequestDTO;
import nl.spplatform.sppapi.dtos.AuthResponseDTO;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword());

        try {
            Authentication auth = authManager.authenticate(up);

            var ud = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);

            AuthResponseDTO response = new AuthResponseDTO();

            User loggedInUser = userRepository.findByEmail(authRequestDTO.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gebruiker niet gevonden"));
            Profile profile = loggedInUser.getProfile();
            if (profile == null) {
                response.setProfileId(-5L);
            } else {
                response.setProfileId(profile.getProfileId());
            }
            response.setToken(token);
            response.setUserId(loggedInUser.getUserId());
            response.setRole(loggedInUser.getRole());
            return ResponseEntity.ok(response);
        } catch (Error e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ongeldige email/wachtwoord combinatie");
        }
    }
}