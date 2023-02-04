package com.example.ecomm.Models;

public class Customers {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String birthDate;
    private String Job;
    private String gender;
    private  int adminornot;

    public Customers(String name, String userName, String password, String birthDate, String job, String gender,int adminornot) {

        this.name = name;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        Job = job;
        this.gender = gender;
        this.adminornot=adminornot;
    }
    public Customers(String name, String userName, String password, String birthDate, String job, String gender) {

        this.name = name;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        Job = job;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
