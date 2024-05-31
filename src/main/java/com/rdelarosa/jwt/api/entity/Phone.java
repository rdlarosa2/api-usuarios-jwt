package com.rdelarosa.jwt.api.entity;

//import jakarta.persistence.Entity;

import javax.persistence.*;

@Entity
@Table(name="PHONE_TBL")
public class Phone {

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    int userId;

    @Column(name="number")
    private String number;

    @Column(name="citycode")
    private String citycode;

    @Column(name="countrycode")
    private String countrycode;

    public Phone() {
    }

    public Phone(int theUserId, String number, String citycode, String countrycode) {
        this.userId = theUserId;
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", citycode='" + citycode + '\'' +
                ", countrycode='" + countrycode + '\'' +
                '}';
    }

}
