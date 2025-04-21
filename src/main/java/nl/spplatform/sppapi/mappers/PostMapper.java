package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.PostRequestDTO;
import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.UpvoteRepository;
import nl.spplatform.sppapi.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {
    public static PostResponseDTO toResponseDTO(Post post, int upvoteCount){
        var result = new PostResponseDTO();
        result.setPostId(post.getPostId());
        result.setText(post.getText());
        result.setRegion(post.getRegion());
        result.setTitle(post.getTitle());
        result.setUpvoteCount(upvoteCount);
        return result;
    }

    public static Post toPost(PostRequestDTO postRequestDTO, User user){
        var result = new Post();
        result.setTitle(postRequestDTO.getTitle());
        result.setRegion(postRequestDTO.getRegion());
        result.setText(postRequestDTO.getPosttext());
        result.setUser(user);
        return result;
    }
}
