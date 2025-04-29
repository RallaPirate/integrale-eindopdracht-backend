package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.PostRequestDTO;
import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.mappers.PostMapper;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.repositories.PostRepository;
import nl.spplatform.sppapi.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO){
        PostResponseDTO postResponseDTO = postService.createPost(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDTO);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(@RequestParam(required = false) List<String> region){
        List<PostResponseDTO> result = postService.getAllPosts(region);
        return ResponseEntity.ok(result);
    }


//    @CrossOrigin(origins = "*")
//    @GetMapping
//    public ResponseEntity<List<PostResponseDTO>> getPosts(@RequestParam(required = false) String region){
//        List<Post> posts = (region != null) ? postRepository.findByRegion(region) : postRepository.findAll();
//        return ResponseEntity.ok(PostMapper.toResponseDTOList(posts));
//    }

}
