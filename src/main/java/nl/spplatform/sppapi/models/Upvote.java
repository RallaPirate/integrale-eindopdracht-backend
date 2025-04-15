package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "upvotes", uniqueConstraints = @UniqueConstraint(columnNames = {"postid", "userid" }))

public class Upvote {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long upvoteId;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

@ManyToOne
@JoinColumn(name = "post_id")
private Post post;

}
