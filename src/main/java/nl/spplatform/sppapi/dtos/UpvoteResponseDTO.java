package nl.spplatform.sppapi.dtos;

import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;

import java.time.LocalDateTime;

public class UpvoteResponseDTO {

    private Long upvoteId;
    private LocalDateTime upvotedAt;
    private Long userId;
    private String username;
    private Long postId;
    private String postTitle;

//    getters&setters

    public Long getUpvoteId(){ return upvoteId; }
    public LocalDateTime getUpvotedAt(){ return upvotedAt; }
    public Long getUserId(){ return userId; }
    public Long getPostId(){ return postId; }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setPostId(Long postId){
        this.postId = postId;
    }
    public void setUpvoteId(Long upvoteId) {
        this.upvoteId= upvoteId;
    }
    public void setUpvotedAt(LocalDateTime upvotedAt){
        this.upvotedAt = upvotedAt;
    }
}
