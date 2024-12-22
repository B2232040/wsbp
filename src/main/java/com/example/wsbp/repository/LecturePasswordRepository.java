package com.example.wsbp.repository;

import com.example.wsbp.data.LecturePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LecturePasswordRepository implements ILecturePasswordRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public LecturePasswordRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(int lectureID, String lecturePassword) {
        var sql = "insert into lecture_password (lectureID, lecturepassword) values (?, ?)";
        var n = jdbc.update(sql, lectureID, lecturePassword);
        return n;
    }

    @Override
    public boolean exists(int lectureID) {
        var sql = "select true from lecture_password where lectureID = ?";

        var booles =jdbc.query(sql, SingleColumnRowMapper.newInstance(Boolean.class), lectureID);

        return !booles.isEmpty();
    }

    @Override
    public List<LecturePassword> find() {
        String sql = "select lectureID, lecturePassword from lecture_password";

        List<LecturePassword> lecturePasswords = jdbc.query(sql,
                DataClassRowMapper.newInstance(LecturePassword.class));
        return lecturePasswords;
    }

    @Override
    public List<LecturePassword> findLectureByLectureID(int id) {
        String sql = "select lectureID, lecturePassword from lecture_password where lectureID = ?";

        List<LecturePassword> lecturePasswords = jdbc.query(sql,
                new Object[]{id}, (rs, rowNum) ->{
            LecturePassword lecturePassword = new LecturePassword(0,null);
            lecturePassword.setLectureID(rs.getInt("LECTURE_ID"));
            lecturePassword.setLecturePassword(rs.getString("LECTURE_PASSWORD"));
            return lecturePassword;
                });
        return lecturePasswords;
    }
}
