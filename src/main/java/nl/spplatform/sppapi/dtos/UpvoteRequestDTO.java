package nl.spplatform.sppapi.dtos;

public class UpvoteRequestDTO {

    private Long userId;
    private Long postId;

    //getters & setters

    public Long getUserId(){
        return userId;
    }
    public Long getPostId(){
        return postId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
