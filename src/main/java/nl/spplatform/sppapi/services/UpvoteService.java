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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpvoteService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UpvoteRepository upvoteRepository;

    public UpvoteService(UpvoteRepository upvoteRepository, UserRepository userRepository, PostRepository postRepository) {
        this.upvoteRepository = upvoteRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public UpvoteResponseDTO createUpvote(UpvoteRequestDTO upvoteRequestDTO){

        Long userId = upvoteRequestDTO.getUserId();
        Long postId = upvoteRequestDTO.getPostId();

        if(upvoteRepository.existsByUser_userIdAndPost_postId(userId, postId)){
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Gebruiker heeft al een upvote op deze post");
        }

        User user = userRepository.findByUserId(userId);
        Post post = postRepository.findByPostId(postId);
        Upvote upvote = UpvoteMapper.toUpvote(upvoteRequestDTO, user, post);
        Upvote savedUpvote = upvoteRepository.save(upvote);

        return UpvoteMapper.responseDTO(savedUpvote);
    }

}
