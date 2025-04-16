package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.UpvoteResponseDTO;
import nl.spplatform.sppapi.models.Upvote;

public class UpvoteMapper {
    public static UpvoteResponseDTO responseDTO(Upvote upvote){
        var result = new UpvoteResponseDTO();
        result.setUpvoteId(upvote.getUpvoteId());
        result.setUpvotedAt(upvote.getUpvotedAt());
        result.setUserId(upvote.getUser().getUserId());
        result.setPostId(upvote.getPost().getPostId());
        return result;
    }
}
