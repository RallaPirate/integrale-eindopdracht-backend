package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.mappers.PostMapper;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody Post post){
        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.toResponseDTO(savedPost, 0));
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPosts(@RequestParam(required = false) String region){
        List<Post> posts = (region != null) ? postRepository.findByRegion(region) : postRepository.findAll();
        return ResponseEntity.ok(PostMapper.toResponseDTOList(posts));
    }
}
