package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Lecture;
import com.example.wsbp.data.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LectureRepository implements ILectureRepository{
    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public LectureRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public int insert(int lectureID, int subjectID, int lectureSerialNum, String detail) {
        return 0;
    }

    @Override
    public boolean exists(String userName, String userPass) {
        return false;
    }

    @Override
    public List<Lecture> find() {
        return List.of();
    }

    @Override
    public List<Lecture> findLectureBySubjectID(int id) {
        String sql = "SELECT ID, SUBJECT_ID, LECTURE_SERIAL_NUM, DETAIL FROM LECTURE WHERE SUBJECT_ID = ?";

        // 講義リストを取得するクエリ
        List<Lecture> lectures = jdbc.query(sql, new Object[]{id}, (rs, rowNum) -> {
            Lecture lecture = new Lecture(0,0,0,null);
            lecture.setLectureID(rs.getInt("ID"));
            lecture.setSubjectID(rs.getInt("SUBJECT_ID"));
            lecture.setLectureSerialNum(rs.getInt("LECTURE_SERIAL_NUM"));
            lecture.setDetail(rs.getString("DETAIL"));
            return lecture;
        });

        return lectures;
    }
}

