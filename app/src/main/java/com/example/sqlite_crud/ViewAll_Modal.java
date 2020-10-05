package com.example.sqlite_crud;

public class ViewAll_Modal {
     private  String name;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private  byte[]image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private  String id;


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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String email;
    private String mobile;
    private String gender;
}
