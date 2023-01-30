package com.example.present;

public class Parent {

    public String pFirstName;
    public String pSurName;
    public String pEmail;
    public String pChildID;

    public Parent(String pFirstName, String pSurName, String pEmail, String pChildID) {
        this.pFirstName = pFirstName;
        this.pSurName = pSurName;
        this.pEmail = pEmail;
        this.pChildID = pChildID;
    }

    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpSurName() {
        return pSurName;
    }

    public void setpSurName(String pSurName) {
        this.pSurName = pSurName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpChildID() {
        return pChildID;
    }

    public void setpChildID(String pChildID) {
        this.pChildID = pChildID;
    }
}
