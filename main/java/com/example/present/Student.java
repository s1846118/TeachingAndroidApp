package com.example.present;

public class Student {

    public String firstName;
    public String surName;
    public String dateofBirth;
    public String studentNo;

    public Student(){

    }

    public Student(String firstName, String surName, String dateofBirth, String studentNo) {
        this.firstName = firstName;
        this.surName = surName;
        this.dateofBirth = dateofBirth;
        this.studentNo = studentNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
