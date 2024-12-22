package com.example.wsbp.data;

import java.io.Serializable;

public class LectureAttendance implements Serializable {
    private int lectureId;
    private String studentName;

    public LectureAttendance(int lectureId, String studentName) {
        this.lectureId = lectureId;
        this.studentName = studentName;
    }

    public int getLectureId() {return lectureId;}
    public String getStudentName() {return studentName;}

    public void setLectureId(int lectureId) {this.lectureId = lectureId;}
    public void setStudentName(String studentName) {this.studentName = studentName;}
}
