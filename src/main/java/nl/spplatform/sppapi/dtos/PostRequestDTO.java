package nl.spplatform.sppapi.dtos;

import org.antlr.v4.runtime.misc.NotNull;

public class PostRequestDTO {

    private String title;
    private String region;
    private String posttext;

    @NotNull
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
