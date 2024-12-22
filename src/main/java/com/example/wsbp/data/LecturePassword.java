package com.example.wsbp.data;

import java.io.Serializable;

public class LecturePassword implements Serializable {
    private int LectureID;
    private String LecturePassword;

    public LecturePassword(int lectureID, String lecturePassword) {
        LectureID = lectureID;
        LecturePassword = lecturePassword;
    }

    public int getLectureID() {return LectureID;}
    public String getLecturePassword() {return LecturePassword;}
}
