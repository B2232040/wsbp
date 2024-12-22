package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture;
import com.example.wsbp.data.LecturePassword;

import java.util.List;

public interface ILecturePasswordRepository {
    /**
     * ユーザー名とパスワードをLectureテーブルに記録する
     *
     * @param lectureID 授業ID
     * @param lecturePassword パスワード
     * @return データベースの更新行数
     */
    public int insert(int lectureID, String lecturePassword);

    /**
     * lectureIDがあるかどうかを見る
     *
     * @param lectureID 授業ID
     * @return レコードの有無, 存在すれば<code>true</code>, それ以外は <code>false</code>
     */
    public boolean exists(int lectureID);

    /**
     * Lectureテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link LecturePassword} の {@link List} で返す
     */
    public List<LecturePassword> find();


    /**
     * IDからLectureの行を引き出す
     *
     * @return Lectureクラス
     */
    public List<LecturePassword> findLectureByLectureID(int id);

}
