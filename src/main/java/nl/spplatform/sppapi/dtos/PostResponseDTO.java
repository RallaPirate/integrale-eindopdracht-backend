package nl.spplatform.sppapi.dtos;

public class PostResponseDTO {

    private Long postId;
    private String title;
    private String region;
    private String text;
    private int upvoteCount;

    public Long getPostid() {
        return postId;
    }
    public String getTitle() {
        return title;
    }
    public String getRegion() {
        return region;
    }
    public String getPosttext() {
        return text;
    }
    public int getUpvoteCount(){
        return upvoteCount;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    public void setUpvoteCount(int upvoteCount){
        this.upvoteCount = upvoteCount;
    }

}
