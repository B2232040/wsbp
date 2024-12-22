package com.example.wsbp.repository;

import com.example.wsbp.data.LectureAttendance;
import java.util.List;

public interface ILectureAttendanceRepository {
    /**
     * 授業IDと出席者名をLectureAttendanceテーブルに記録する
     *
     * @params lectureID 授業ID
     * @params studentName 出席登録者の氏名
     *
     * @return データベースの更新行数
      */
    public int insert(int lectureID, String studentName);

    /**
     * lectureAttendanceテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@Link LectureAttendance}の{@Link List}で返す
     */
    public List<LectureAttendance> find();

    /**
     * 授業IDがあって、名前があるのか確認する
     *
     * @return boolean 授業IDの表の中にstudentNameはあるのかどうか
     */
    public boolean exist (int lectureID, String studentName);

}
