package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "upvotes", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))

public class Upvote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upvoteId;

    @Column(updatable = false)
    private LocalDateTime upvotedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Upvote() {
    }

//constructors

    public Upvote(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    //LifeCycle hook
    @PrePersist
    protected void onCreate() {
        this.upvotedAt = LocalDateTime.now();
    }

    //getters&setters
    public Long getUpvoteId() {
        return upvoteId;
    }

    public LocalDateTime getUpvotedAt() {
        return upvotedAt;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

}
