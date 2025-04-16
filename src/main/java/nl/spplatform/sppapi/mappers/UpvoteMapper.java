package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.UpvoteResponseDTO;
import nl.spplatform.sppapi.models.Upvote;

public class UpvoteMapper {
    public static UpvoteResponseDTO responseDTO(Upvote upvote){
        var result = new UpvoteResponseDTO();
        result.setPostId(Upvote.GetPostId());
    }
}
