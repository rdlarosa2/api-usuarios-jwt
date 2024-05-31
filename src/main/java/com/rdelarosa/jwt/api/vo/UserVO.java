package com.rdelarosa.jwt.api.vo;

import java.util.Date;

public class UserVO {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private String created;

    private String modified;

    private Date lastLogin;

    private String token;

    private Boolean active;

    private PhoneVO[] phones;

    public UserVO() {
    }

    public UserVO(Integer id, String name, String email, String password, String theCreationDate, String theModificationDate, Date lastLogin, String token, Boolean active ,PhoneVO[] phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = theCreationDate;
        this.modified = theModificationDate;
        this.lastLogin = lastLogin;
        this.token = token;
        this.active = active;
        this.phones = phones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PhoneVO[] getPhones() {
        return phones;
    }

    public void setPhones(PhoneVO[] phones) {
        this.phones = phones;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
