package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Subject;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class SubjectRepository implements ISubjectRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public SubjectRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Subject> find(){
        String sql = "select id, subject_name from subject";

        List<Subject> subjects = jdbc.query(sql,
                DataClassRowMapper.newInstance(Subject.class));

        return subjects;
    }

   @Override
    public Subject findByID(int id){
        String subjectName = null;
        String sql = "SELECT SUBJECT_NAME FROM SUBJECT WHERE ID = ?";

       // jdbc.queryForObjectを使用して、単一の値を取得
       try {
           subjectName = jdbc.queryForObject(sql, new Object[]{id}, String.class);
       } catch (Exception e) {
           // 例外をキャッチして処理（例: subjectが見つからない場合）
           e.printStackTrace();
       }

       Subject subject = new Subject(id, subjectName);

       return subject;
    }
}
