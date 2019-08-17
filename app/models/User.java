package models;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="user")
public class User extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "INT")
    private int id;

    @ManyToOne
    @JoinColumn(name="user_type_id",referencedColumnName = "id")
    private UserType userTypeId;

    @Column(name = "fname", columnDefinition = "VARCHAR", length = 45)
    @NotNull
    private String fname;

    @Column(name = "lname", columnDefinition = "VARCHAR", length = 45)
    private String lname;

    @Column(name = "password", columnDefinition = "VARCHAR", length = 45)
    private String password;

    @Column(name = "email", columnDefinition = "VARCHAR", length = 45)
    private String email;

    @Column(name = "created_on", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserType userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    public static Finder<Integer, User> find = new Finder<Integer,User>(
            "default",Integer.class,User.class
    );
}
