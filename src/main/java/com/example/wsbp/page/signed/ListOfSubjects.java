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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("List")

public class ListOfSubjects extends WebPage{
    @SpringBean
    private IUserService userService;

    @SpringBean
    private ISubjectRepository subjectRepos;

    public ListOfSubjects() {
        //Serviceからdbの科目一覧をもらい、modelにする
        var subjectsModel = Model.ofList(userService.findSubjects());

        // List型のモデルを表示する ListView
        var subjectsLV = new ListView<>("subjects", subjectsModel) {
            @Override
            protected void populateItem(ListItem<Subject> listItem) {
                var itemModel = listItem.getModel();
                var subject = itemModel.getObject();

                var subjectLink = new Link<Void>("subjectLink"){
                    @Override
                    public void onClick() {
                        PageParameters params = new PageParameters();
                        params.add("subjectID", subject.getID());
                        setResponsePage(ListOfLecture.class, params);
                    }
                };

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                var subjectNameModel = Model.of(subject.getSubjectName());
                var subjectNameLabel = new Label("subjectName", subjectNameModel);
                subjectLink.add(subjectNameLabel);

                listItem.add(subjectLink);
            }
        };
        add(subjectsLV);
    }


}
