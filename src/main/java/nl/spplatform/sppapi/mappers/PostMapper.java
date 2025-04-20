package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.repositories.UpvoteRepository;

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

    public static List<PostResponseDTO> toResponseDTOList(List<Post> posts){
        return posts.stream().map(PostMapper::toResponseDTO).collect(Collectors.toList());
    }
}
