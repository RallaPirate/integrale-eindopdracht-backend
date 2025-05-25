package nl.spplatform.sppapi.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.spplatform.sppapi.Security.JwtService;
import nl.spplatform.sppapi.dtos.SignUpRequestDTO;
import nl.spplatform.sppapi.dtos.SignUpResponseDTO;
import nl.spplatform.sppapi.mappers.SignUpMapper;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.ProfileRepository;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private ProfileRepository   profileRepository;

    @Mock
    private UserRepository      userRepository;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private JwtService          jwtService;

    @InjectMocks
    private UserService userService;

    private SignUpRequestDTO signUpRequestDTO;
    private User mockUser;
    private Profile mockProfile;

    @BeforeEach
    void setUp() {
        signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setEmail("test@example.com");
        signUpRequestDTO.setPassword("rawPass");
        signUpRequestDTO.setName("tester");

        mockProfile = mock(Profile.class);
        mockUser = mock(User.class);
    }

    @Test
    void canCreateUser() {
        //arrange
        when(userRepository.findByEmail(signUpRequestDTO.getEmail()))
                .thenReturn(Optional.empty());
        when(passwordEncoderService.encrypt("rawPass"))
                .thenReturn("encryptedPass");
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        //act
        SignUpResponseDTO response = userService.createUser(signUpRequestDTO);
        //assert
        assertTrue(response.getSuccess());
        assertEquals("Gebruiker succesvol aangemaakt", response.getMessage());
        verify(passwordEncoderService, times(1)).encrypt("rawPass");
        verify(userRepository,        times(1)).save(any(User.class));
    }

    @Test
    void canCreateUserReturnsErrorWhenEmailInUse() {
        //arrange
        when(userRepository.findByEmail(signUpRequestDTO.getEmail()))
                .thenReturn(Optional.of(mockUser));
        //act
        SignUpResponseDTO response = userService.createUser(signUpRequestDTO);

        //assert
        assertFalse(response.getSuccess());
        assertEquals("Emailadres is al in gebruik", response.getMessage());
        verify(userRepository, never()).save(any());
        verify(passwordEncoderService, never()).encrypt(anyString());
    }


    @Test
    void canGetUserIdFromToken() {
        //arrange
        String token = "dummy.jwt.token";
        when(jwtService.extractUsername(token))
                .thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(mockUser));
        when(mockUser.getUserId()).thenReturn(42L);
        //act
        Long userId = userService.getUserIdFromToken(token);
        //assert
        assertEquals(42L, userId);
        verify(jwtService, times(1)).extractUsername(token);
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void canGetUserIdFromTokenWhenUserNotFound(){
        // Arrange
        String token = "dummy.jwt.token";
        when(jwtService.extractUsername(token))
                .thenReturn("nouser@example.com");
        when(userRepository.findByEmail("nouser@example.com"))
                .thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userService.getUserIdFromToken(token)
        );
        assertTrue(exception.getMessage().contains("Gebruiker niet gevonden met email: nouser@example.com"));
    }
}
