package com.example.present;


public class StudentList {

    StudentAttendance student;

    public StudentList(StudentAttendance student){
        this.student = student;
    }

    public StudentAttendance getStudent(){
        return student;
    }

    public void setStudent(StudentAttendance student){
        this.student = student;
    }

    public StudentList(){

    }

}
