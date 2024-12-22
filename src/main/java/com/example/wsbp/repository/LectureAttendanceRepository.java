package com.example.wsbp.repository;


import com.example.wsbp.data.LectureAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LectureAttendanceRepository implements ILectureAttendanceRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public LectureAttendanceRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public int insert(int lectureID, String studentName) {
        var sql = "insert into lecture_attendance (lectureID, studentName) values (?,?)";
        var n = jdbc.update(sql, lectureID, studentName);
        return n;
    }

    @Override
    public List<LectureAttendance> find() {
        String sql = "select lectureID, studentName from lecture_attendance";

        List<LectureAttendance> lectureAttendances = jdbc.query(sql,
                DataClassRowMapper.newInstance(LectureAttendance.class));
        return lectureAttendances;
    }

    @Override
    public boolean exist(int lectureID, String studentName) {
        var sql = "select true from lecture_attendance where lectureID = ? and studentName = ?";

        var booles = jdbc.query(sql, SingleColumnRowMapper.newInstance(Boolean.class), lectureID, studentName);

        return !booles.isEmpty();
    }
}
