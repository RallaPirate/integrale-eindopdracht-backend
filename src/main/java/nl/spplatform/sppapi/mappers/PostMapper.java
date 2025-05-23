package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.PostRequestDTO;
import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;

public class PostMapper {
    public static PostResponseDTO toResponseDTO(Post post, int upvoteCount, Boolean upvotedByUser) {
        var result = new PostResponseDTO();
        result.setPostId(post.getPostId());
        result.setText(post.getText());
        result.setRegion(post.getRegion());
        result.setTitle(post.getTitle());
        result.setUpvoteCount(upvoteCount);
        result.setUpvotedByUser(upvotedByUser);
        return result;
    }

    public static PostResponseDTO toResponseDTO(Post post) {
        return toResponseDTO(post, 0, false);
    }

    public static Post toPost(PostRequestDTO postRequestDTO, User user) {
        var result = new Post();
        result.setTitle(postRequestDTO.getTitle());
        result.setRegion(postRequestDTO.getRegion());
        result.setText(postRequestDTO.getPosttext());
        result.setUser(user);
        return result;
    }
}
