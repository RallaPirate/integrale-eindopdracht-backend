package nl.spplatform.sppapi.dtos;

public class PostRequestDTO {

    private String title;
    private String region;
    private String posttext;
    private Long userId;

    public String getTitle(){
        return title;
    }
    public String getRegion(){
        return region;
    }
    public String getPosttext(){
        return posttext;
    }
    public Long getUserId(){
        return userId;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public void setPosttext(String posttext){
        this.posttext = posttext;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
}
