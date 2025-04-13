package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "upvotes", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id" }))

public class Upvote {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long upvote_id;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

@ManyToOne
@JoinColumn(name = "post_id")
private Post post;

}
