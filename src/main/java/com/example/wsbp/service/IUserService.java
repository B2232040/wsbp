package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Subject;

import java.util.List;

public interface IUserService {

    public void registerUser(String userName, String userPass);
    public void removeUser(String userName);

    /**
     * ユーザ名とパスワードをデータベースに照合する
     *
     * @param userName ユーザー名
     * @param userPass パスワード
     * @return 照合成功であれば<code>true</code>, 照合失敗は<code>false</code>
     */
    public boolean existsUser(String userName, String userPass);

    /**
     * ユーザ名とパスワードの一覧を、AuthUser型のリストで検索する
     *
     * @return AuthUser型のListインスタンス
     */
    public List<AuthUser> findAuthUsers();

    /**
     * IDと科目名の一覧を、Subject型のリストで検索する
     *
     * @return Subject型のListインスタンス
     */
    public List<Subject> findSubjects();



}