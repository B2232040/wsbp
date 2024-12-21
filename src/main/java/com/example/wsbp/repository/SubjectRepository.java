package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

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
}
