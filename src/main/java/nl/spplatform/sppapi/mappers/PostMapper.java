package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.models.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {
    public static PostResponseDTO toResponseDTO(Post post){
        var result = new PostResponseDTO();
        result.setPost_id(post.getPost_id());
        result.setPosttext(post.getPosttext());
        result.setRegion(post.getRegion());
        result.setTitle(post.getTitle());
        return result;
    }

    public static List<PostResponseDTO> toResponseDTOList(List<Post> posts){
        return posts.stream().map(PostMapper::toResponseDTO).collect(Collectors.toList());
    }
}
