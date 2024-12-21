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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("List")

public class ListOfSubjects extends WebPage{
    @SpringBean
    private IUserService userService;

    @SpringBean
    private ISubjectRepository subjectRepos;

    public ListOfSubjects() {

        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        // なお、DBや外部のWEB-APIなどのデータを取得する場合、通常はLoadableDetachableModelを利用する
        // 参考：https://ci.apache.org/projects/wicket/guide/9.x/single.html#_detachable_models
        var subjectsModel = Model.ofList(userService.findSubjects());

        // List型のモデルを表示する ListView
        var subjectsLV = new ListView<>("subjects", subjectsModel) {
            @Override
            protected void populateItem(ListItem<Subject> listItem) {
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var subject = itemModel.getObject(); // 元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var subjectNameModel = Model.of(subject.getSubjectName());
                var subjectNameLabel = new Label("subjectName", subjectNameModel);
                listItem.add(subjectNameLabel);
            }
        };
        add(subjectsLV);
    }


}
