package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String region;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //LifeCycle Hook
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    //constructors
    public Post(){}

    public Post(Long postId, String title, String region, String text){
        this.postId = postId;
        this.title = title;
        this.region = region;
        this.text = text;
    }

    //getters & setters
    public Long getPostId(){
        return postId;
    }

    public String getTitle(){
        return title;
    }

    public String getRegion(){
        return region;
    }

    public String getText(){
        return text;
    }

    public User getUser(){
        return user;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setUser(User user){
        this.user = user;
    }


}