package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postid;

    private String title;
    private String region;
    private String posttext;
    private int upvoteCount;

    //constructors
    public Post(){}

    public Post(Long postid, String title, String region, String posttext){
        this.postid = postid;
        this.title = title;
        this.region = region;
        this.posttext = posttext;
    }

    //getters & setters
    public Long getPostid(){
        return postid;
    }

    public String getTitle(){
        return title;
    }

    public String getRegion(){
        return region;
    }

    public String getPosttext(){
        return posttext;
    }

    public int getUpvoteCount() { return upvoteCount;}


    public void setTitle(String title){
        this.title = title;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public void setPosttext(String posttext){
        this.posttext = posttext;
    }

    public void setUpvoteCount(int upvoteCount){ this.upvoteCount = upvoteCount;}
}