package nl.spplatform.sppapi.dtos;

import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;

import java.time.LocalDateTime;

public class UpvoteDTO {

    private Long upvoteId;
    private LocalDateTime upvotedAt;
    private User user;
    private Post post;

//    getters&setters

    public Long getUpvoteId(){ return upvoteId; }
    public LocalDateTime getUpvotedAt(){ return upvotedAt; }
    public User getUser(){ return user; }
    public Post getPost(){ return post; }

    public void setUser(User user) {
        this.user = user;
    }
    public void setPost(Post post){
        this.post = post;
    }

}
