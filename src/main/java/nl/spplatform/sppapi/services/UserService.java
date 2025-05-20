package nl.spplatform.sppapi.services;

import nl.spplatform.sppapi.Security.JwtService;
import nl.spplatform.sppapi.dtos.SignUpRequestDTO;
import nl.spplatform.sppapi.dtos.SignUpResponseDTO;
import nl.spplatform.sppapi.mappers.SignUpMapper;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.ProfileRepository;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final JwtService jwtService;

    public UserService(ProfileRepository profileRepository, UserRepository userRepository, PasswordEncoderService passwordEncoderService, JwtService jwtService) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.jwtService = jwtService;
    }

    public SignUpResponseDTO createUser(SignUpRequestDTO signUpRequestDTO) {
        String email = signUpRequestDTO.getEmail();
        Optional<User> emailCheck = userRepository.findByEmail(email);

        if (emailCheck.isPresent()) {
            return new SignUpResponseDTO(false, "Emailadres is al in gebruik");
        }

        Profile profile = SignUpMapper.toProfile(signUpRequestDTO);
        User user = SignUpMapper.toUser(signUpRequestDTO);

        String rawPassword = signUpRequestDTO.getPassword();
        String encryptedPassword = passwordEncoderService.encrypt(rawPassword);
        user.setPassword(encryptedPassword);

        user.setProfile(profile);
        profile.setUser(user);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDTO(true, "Gebruiker succesvol aangemaakt");
    }

    public Long getUserIdFromToken(String token){
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Gebruiker niet gevonden met email: " + email));
        Long userId = user.getUserId();
        return userId;
    }
}
