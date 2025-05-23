package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.PostRequestDTO;
import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postService.createPost(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDTO);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(@RequestParam(required = false) List<String> region, @RequestParam(required = false, defaultValue = "newest") String sort, @RequestParam(required = false) String query, @RequestParam(required = true) Long userId) {
        List<PostResponseDTO> result = postService.getAllPosts(region, sort, query, userId);
        return ResponseEntity.ok(result);
    }

}
