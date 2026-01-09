package com.progetto.model.issues;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;


@Embeddable
public class UserInfo {
    private String userid;
    @Transient
    private String name;
    @Transient  
    private String surname;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    
}
