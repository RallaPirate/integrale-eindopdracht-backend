package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.UpvoteRequestDTO;
import nl.spplatform.sppapi.dtos.UpvoteResponseDTO;
import nl.spplatform.sppapi.services.UpvoteService;
import nl.spplatform.sppapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
public class UpvoteController {

    private final UpvoteService upvoteService;
    private final UserService userService;

    public UpvoteController(UpvoteService upvoteService, UserService userService) {
        this.upvoteService = upvoteService;
        this.userService = userService;
    }

    @PostMapping("/{id}/upvote")
    public ResponseEntity<UpvoteResponseDTO> createUpvote(@PathVariable Long id, @RequestBody UpvoteRequestDTO upvoteRequestDTO) {
        upvoteRequestDTO.setPostId(id);
        UpvoteResponseDTO upvoteResponse = upvoteService.createUpvote(upvoteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(upvoteResponse);
    }

    @DeleteMapping("/{id}/upvote")
    public ResponseEntity<Void> deleteUpvote(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ","");
        Long userId = userService.getUserIdFromToken(token);
        UpvoteRequestDTO upvoteRequestDTO = new UpvoteRequestDTO();
        upvoteRequestDTO.setPostId(id);
        upvoteRequestDTO.setUserId(userId);
        upvoteService.deleteUpvote(upvoteRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
