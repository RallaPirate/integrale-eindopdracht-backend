package nl.spplatform.sppapi.services;

import nl.spplatform.sppapi.dtos.PostRequestDTO;
import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.mappers.PostMapper;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.PostRepository;
import nl.spplatform.sppapi.repositories.UpvoteRepository;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UpvoteRepository upvoteRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UpvoteRepository upvoteRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.upvoteRepository = upvoteRepository;
        this.userRepository = userRepository;
    }
//
//    public List<PostResponseDTO> getAllPosts() {
//        List<Post> posts = postRepository.findAll();
//        return posts.stream()
//                .map(post -> {
//                    int upvoteCount = upvoteRepository.countByPost_postId(post.getPostId());
//                    return PostMapper.toResponseDTO(post, upvoteCount);
//                })
//                .collect(Collectors.toList());
//    }
    public List<PostResponseDTO> getAllPosts(String region) {
        List<Post> posts;
        if(region != null) {
        posts = postRepository.findByRegion(region);
        }
        else {
         posts = postRepository.findAll();
        }
        return posts.stream()
                .map(post -> {
                    int upvoteCount = upvoteRepository.countByPost_postId(post.getPostId());
                    return PostMapper.toResponseDTO(post, upvoteCount);
                })
                .collect(Collectors.toList());
    }

    public PostResponseDTO createPost(PostRequestDTO postRequestDTO){

        Long userId = postRequestDTO.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gebruiker bestaat niet"));
        Post post = PostMapper.toPost(postRequestDTO, user);
        Post savedPost = postRepository.save(post);
        return PostMapper.toResponseDTO(savedPost, 0);
    }
}
