package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.UpvoteRequestDTO;
import nl.spplatform.sppapi.dtos.UpvoteResponseDTO;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.Upvote;
import nl.spplatform.sppapi.models.User;

public class UpvoteMapper {
    public static UpvoteResponseDTO responseDTO(Upvote upvote){
        var result = new UpvoteResponseDTO();
        result.setUpvoteId(upvote.getUpvoteId());
        result.setUpvotedAt(upvote.getUpvotedAt());
        result.setUserId(upvote.getUser().getUserId());
        result.setPostId(upvote.getPost().getPostId());
        return result;
    }

    public static Upvote toUpvote(UpvoteRequestDTO upvoteRequest, User user, Post post){
        return new Upvote(user, post);
    }
}
