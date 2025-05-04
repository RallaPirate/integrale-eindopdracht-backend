package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.UpvoteRequestDTO;
import nl.spplatform.sppapi.dtos.UpvoteResponseDTO;
import nl.spplatform.sppapi.services.UpvoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class UpvoteController {

    private final UpvoteService upvoteService;

    public UpvoteController(UpvoteService upvoteService) {
        this.upvoteService = upvoteService;
    }

    @PostMapping("/{id}/upvote")
    public ResponseEntity<UpvoteResponseDTO> createUpvote(@PathVariable Long id, @RequestBody UpvoteRequestDTO upvoteRequestDTO) {
        upvoteRequestDTO.setPostId(id);
        UpvoteResponseDTO upvoteResponse = upvoteService.createUpvote(upvoteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(upvoteResponse);
    }

    @DeleteMapping("/{id}/upvote")
    public ResponseEntity<Void> deleteUpvote(@PathVariable Long id, @RequestBody UpvoteRequestDTO upvoteRequestDTO) {
        upvoteRequestDTO.setPostId(id);
        upvoteService.deleteUpvote(upvoteRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
