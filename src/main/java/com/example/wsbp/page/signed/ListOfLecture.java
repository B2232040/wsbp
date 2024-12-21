package com.example.wsbp.page.signed;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Subject;
import com.example.wsbp.repository.ISubjectRepository;
import com.example.wsbp.service.IUserService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Lecture")

public class ListOfLecture extends WebPage{
    public ListOfLecture(PageParameters parameters) {
        super(parameters);

        // パラメータから subjectID を取得
        String subjectID = parameters.get("subjectID").toString("未指定");

        // subjectID に基づく処理（例: DBから詳細を取得する）
        String lectureDetails = "科目ID: " + subjectID; // 実際にはDBなどから取得

        // 詳細を表示するラベルを追加
        add(new Label("lectureDetails", lectureDetails));
    }


}
