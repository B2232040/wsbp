package com.example.wsbp.data;

import java.io.Serializable;

public class Lecture implements Serializable {
    private int lectureID;
    private int subjectID;
    private int lectureSerialNum;
    private String detail;

    public Lecture(int lectureID, int subjectID, int lectureSerialNum, String detail) {
        this.lectureID = lectureID;
        this.subjectID = subjectID;
        this.lectureSerialNum = lectureSerialNum;
        this.detail = detail;
    }

    public int getLectureID() {return lectureID;}
    public int getSubjectID() {return subjectID;}
    public int getLectureSerialNum() {return lectureSerialNum;}
    public String getDetail() {return detail;}

    public void setLectureID(int lectureID) {this.lectureID = lectureID;}
    public void setSubjectID(int subjectID) {this.subjectID = subjectID;}
    public void setDetail(String detail) {this.detail = detail;}
    public void setLectureSerialNum(int lectureSerialNum) {this.lectureSerialNum = lectureSerialNum;}
}
