package nl.spplatform.sppapi.dtos;

public class PostResponseDTO {

    private Long postId;
    private String title;
    private String region;
    private String text;
    private int upvoteCount;
    private Boolean upvotedByUser;

    public Long getPostid() {
        return postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getUpvoteCount() {
        return upvoteCount;
    }
    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Boolean getUpvotedByUser(){
        return upvotedByUser;
    }
    public void setUpvotedByUser(Boolean upvotedByUser){
        this.upvotedByUser = upvotedByUser;
    }

}
