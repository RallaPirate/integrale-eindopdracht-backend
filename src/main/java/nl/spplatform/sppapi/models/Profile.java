package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    //getters&setters

    public long getProfileId(){ return profileId;}
    public String getName(){ return name;}
    public User getUser(){ return user;}

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUser(User user){
        this.user = user;
    }
}
