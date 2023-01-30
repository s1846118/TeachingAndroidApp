package com.example.present;

public class StudentAttendance {

    public String firstName;
    public String surName;
    public String dateofBirth;
    public String studentNo;
    public Boolean Absent;
    public Boolean Present;


    public StudentAttendance(String firstName, String surName, String dateofBirth, String studentNo, Boolean Present, Boolean Absent) {
        this.firstName = firstName;
        this.surName = surName;
        this.dateofBirth = dateofBirth;
        this.studentNo = studentNo;
        this.Present = Present;
        this.Absent = Absent;
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
        return Absent;
    }

    public void setAbsent(Boolean Absent) {
        this.Absent = Absent;
    }

    public Boolean getPresent() {
        return Present;
    }

    public void setPresent(Boolean Present) {
        this.Present = Present;
    }
}

