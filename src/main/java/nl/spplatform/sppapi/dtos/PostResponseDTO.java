package nl.spplatform.sppapi.dtos;

public class PostResponseDTO {

    private Long postid;

    private String title;
    private String region;
    private String posttext;

    public Long getPostid() {
        return postid;
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
    public void setPostid(Long postid) {
        this.postid = postid;
    }


}
