package nl.spplatform.sppapi.dtos;

import java.time.LocalDateTime;

public class UpvoteResponseDTO {

    private Long upvoteId;
    private LocalDateTime upvotedAt;
    private Long userId;
    private String username;
    private Long postId;
    private String postTitle;


    public Long getUpvoteId() {
        return upvoteId;
    }

    public void setUpvoteId(Long upvoteId) {
        this.upvoteId = upvoteId;
    }

    public LocalDateTime getUpvotedAt() {
        return upvotedAt;
    }

    public void setUpvotedAt(LocalDateTime upvotedAt) {
        this.upvotedAt = upvotedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
