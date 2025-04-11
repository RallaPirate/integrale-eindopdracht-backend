package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "upvotes", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id" }))

public class Upvote {

}
