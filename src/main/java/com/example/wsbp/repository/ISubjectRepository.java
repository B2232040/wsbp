package com.example.wsbp.repository;


import com.example.wsbp.data.Subject;
import java.util.List;

public interface ISubjectRepository {

    /**
     * Subjectテーブルのすべての情報を検索する
     *
     * @return テーブルのすべての情報
     */
    public List<Subject> find();

    /**
     *  SubjectテーブルのIDと一致するsubject_nameを検索
     *
     * @return IDと一致するsubject_name
     */
    public Subject findByID(int id);
}
