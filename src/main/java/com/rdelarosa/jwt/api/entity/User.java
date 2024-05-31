package com.rdelarosa.jwt.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_TBL")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="created")
    private Date created;

    @Column(name="modified")
    private Date modified;

    @Column(name="last_login")
    private Date lastLogin;

    @Column(name="token")
    private String token;

    @Column(name="active")
    private Boolean active;

    public User() {
    }

    public User(Integer id,String theName,String userName, String password, String email, Date created, Date modified,Date theLastLogin, String token, Boolean active) {
        this.id = id ;
        this.name = theName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.created = created;
        this.modified = modified;
        this.lastLogin = theLastLogin;
        this.token = token;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
