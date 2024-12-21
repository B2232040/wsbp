package com.example.wsbp.data;

import java.io.Serializable;

public class Subject implements Serializable {
    private final int ID;
    private final String subjectName;

    public Subject(int ID, String subjectName) {
        this.ID = ID;
        this.subjectName = subjectName;
    }

    public int getID() {
        return ID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if(!subjectName.equals(subject.subjectName)) return false;
        return ID == subject.ID;
    }

    @Override
    public int hashCode() {
        int result = subjectName.hashCode();
        result = 31 * result + ID;
        return result;
    }
}
