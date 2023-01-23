package com.kojelauta.projekti.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Userinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String userId;
    @Column
    private String email;
    @Column
    private int boxId;
    @Column
    private int apiId;
    @Column
    private String apiName;
    @Column
    private String apiValue;
    @Column
    private Boolean visible;
    public Userinfo(){

    }
    public Userinfo(String userId,String email, int boxId){
        this.userId=userId;
        this.email=email;
        this.boxId=boxId;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getBoxId() {
        return boxId;
    }
    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }
    public int getApiId() {
        return apiId;
    }
    public void setApiId(int apiId) {
        this.apiId = apiId;
    }
    public String getApiName() {
        return apiName;
    }
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
    public String getApiValue() {
        return apiValue;
    }
    public void setApiValue(String apiValue) {
        this.apiValue = apiValue;
    }
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
   
    
}
