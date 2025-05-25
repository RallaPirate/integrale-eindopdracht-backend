package nl.spplatform.sppapi.services;

import nl.spplatform.sppapi.dtos.UpvoteRequestDTO;
import nl.spplatform.sppapi.dtos.UpvoteResponseDTO;
import nl.spplatform.sppapi.mappers.UpvoteMapper;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.Upvote;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.PostRepository;
import nl.spplatform.sppapi.repositories.UpvoteRepository;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpvoteServiceTest {

    @Mock
    private UpvoteRepository upvoteRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UpvoteService upvoteService;

    private User mockUser;
    private Post mockPost;
    private Upvote mockUpvote;

    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);

        mockPost = mock(Post.class);

        mockUpvote = new Upvote(mockUser, mockPost);
    }

    @Test
    void canCreateUpvote() {
        // Arrange
        when(mockUser.getUserId()).thenReturn(1L);
        when(mockPost.getPostId()).thenReturn(10L);

        UpvoteRequestDTO upvoteRequestDTO = new UpvoteRequestDTO();
        upvoteRequestDTO.setUserId(1L);
        upvoteRequestDTO.setPostId(10L);

        when(upvoteRepository.existsByUser_userIdAndPost_postId(1L, 10L)).thenReturn(false);
        when(userRepository.findByUserId(1L)).thenReturn(mockUser);
        when(postRepository.findByPostId(10L)).thenReturn(mockPost);
        when(upvoteRepository.save(any(Upvote.class))).thenReturn(mockUpvote);

        // Act
        UpvoteResponseDTO result = upvoteService.createUpvote(upvoteRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(10L, result.getPostId());
    }

    @Test
    void canCreateUpvote_ThrowsConflictIfExists() {
        // Arrange
        UpvoteRequestDTO upvoteRequestDTO = new UpvoteRequestDTO();
        upvoteRequestDTO.setUserId(1L);
        upvoteRequestDTO.setPostId(10L);
        when(upvoteRepository.existsByUser_userIdAndPost_postId(1L, 10L)).thenReturn(true);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            upvoteService.createUpvote(upvoteRequestDTO);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Gebruiker heeft al een upvote op deze post", exception.getReason());
    }

    @Test
    void canDeleteUpvote() {
        // Arrange
        UpvoteRequestDTO upvoteRequestDTO = new UpvoteRequestDTO();
        upvoteRequestDTO.setUserId(1L);
        upvoteRequestDTO.setPostId(10L);
        when(upvoteRepository.existsByUser_userIdAndPost_postId(1L, 10L)).thenReturn(true);

        // Act
        assertDoesNotThrow(() -> upvoteService.deleteUpvote(upvoteRequestDTO));

        // Assert
        verify(upvoteRepository, times(1)).deleteByUser_userIdAndPost_postId(1L, 10L);
    }

    @Test
    void canDeleteUpvote_ThrowsNotFoundIfNotExists() {
        // Arrange
        UpvoteRequestDTO upvoteRequestDTO = new UpvoteRequestDTO();
        upvoteRequestDTO.setUserId(1L);
        upvoteRequestDTO.setPostId(10L);
        when(upvoteRepository.existsByUser_userIdAndPost_postId(1L, 10L)).thenReturn(false);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            upvoteService.deleteUpvote(upvoteRequestDTO);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Er is geen upvote voor deze gebruiker/post gevonden", exception.getReason());
    }
}