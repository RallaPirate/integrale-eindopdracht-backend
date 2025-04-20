package nl.spplatform.sppapi.services;

import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.mappers.PostMapper;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.repositories.PostRepository;
import nl.spplatform.sppapi.repositories.UpvoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UpvoteRepository upvoteRepository;

    public PostService(PostRepository postRepository, UpvoteRepository upvoteRepository){
        this.postRepository = postRepository;
        this.upvoteRepository = upvoteRepository;
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
}
