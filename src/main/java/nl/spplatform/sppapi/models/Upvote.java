package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "upvotes", uniqueConstraints = @UniqueConstraint(columnNames = {"postid", "userid" }))

public class Upvote {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long upvoteId;

private LocalDateTime upvotedAt = LocalDateTime.now();

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

@ManyToOne
@JoinColumn(name = "post_id")
private Post post;

//constructors

    public Upvote(){}

    public Upvote(Long upvoteId, LocalDateTime upvotedAt, User user, Post post){
        this.upvoteId = upvoteId;
        this.upvotedAt = upvotedAt;
        this.user = user;
        this.post = post;
    }

    //getters&setters
    public Long getUpvoteId(){ return upvoteId; }
    public LocalDateTime getUpvotedAt(){ return upvotedAt; }
    public User getUser() {return user;}
    public Post getPost(){ return post;}



    //TODO: check if I need setters if I do not intend to have anything changed after initial setup. What is best practice?
}
