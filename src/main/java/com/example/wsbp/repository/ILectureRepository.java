package com.example.wsbp.repository;
import com.example.wsbp.data.Lecture;
import java.util.List;

public interface ILectureRepository {

    /**
     * ユーザー名とパスワードをLectureテーブルに記録する
     *
     * @param lectureID 授業ID
     * @param subjectID 科目ID
     * @param lectureSerialNum 授業回数
     * @param detail 授業情報
     * @return データベースの更新行数
     */
    public int insert(int lectureID, int subjectID, int lectureSerialNum, String detail);

    /**
     * ユーザ名とパスワードが一致するレコードがAuthUserテーブルにあるか検索する
     *
     * @param userName ユーザー名
     * @param userPass パスワード
     * @return レコードの有無, 存在すれば<code>true</code>, それ以外は <code>false</code>
     */
    public boolean exists(String userName, String userPass);

    /**
     * Lectureテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Lecture} の {@link List} で返す
     */
    public List<Lecture> find();


    /**
     * IDからLectureの行を引き出す
     *
     * @return Lectureクラス
     */
    public List<Lecture> findLectureBySubjectID(int id);


}