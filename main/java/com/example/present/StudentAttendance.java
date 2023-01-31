package com.example.present;

public class StudentAttendance {

    public String firstName;
    public String surName;
    public String dateofBirth;
    public String studentNo;
    public Boolean absent;
    public Boolean present;


    public StudentAttendance(String firstName, String surName, String dateofBirth, String studentNo, Boolean present, Boolean absent) {
        this.firstName = firstName;
        this.surName = surName;
        this.dateofBirth = dateofBirth;
        this.studentNo = studentNo;
        this.present = present;
        this.absent = absent;
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

    public Boolean getAbsent() {
        return absent;
    }

    public void setAbsent(Boolean absent) {
        this.absent = absent;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}

