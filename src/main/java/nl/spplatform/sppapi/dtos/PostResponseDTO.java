package nl.spplatform.sppapi.dtos;

public class PostResponseDTO {

    private Long post_id;

    private String title;
    private String region;
    private String posttext;

    public Long getPost_id() {
        return post_id;
    }
    public String getTitle() {
        return title;
    }
    public String getRegion() {
        return region;
    }
    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPost_id(Long postid) {
        this.post_id = post_id;
    }


}
