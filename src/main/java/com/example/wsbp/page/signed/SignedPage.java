package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.data.AuthUser;
import com.example.wsbp.page.SignPage;
import com.example.wsbp.page.UserDeletePage;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public SignedPage() {
        var name = MySession.get().getUserName();
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);

        Link<Void> signoutLink = new Link<Void>("signout") {

            @Override
            public void onClick() {
                // セッションの破棄
                MySession.get().invalidate();
                // SignPageへ移動
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);

        var toListOfSubjectsLink = new BookmarkablePageLink<>("listOfSubjects", ListOfSubjects.class);
        add(toListOfSubjectsLink);



        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        // なお、DBや外部のWEB-APIなどのデータを取得する場合、通常はLoadableDetachableModelを利用する
        // 参考：https://ci.apache.org/projects/wicket/guide/9.x/single.html#_detachable_models
        var authUsersModel = Model.ofList(userService.findAuthUsers());

        // List型のモデルを表示する ListView
        var usersLV = new ListView<>("users", authUsersModel) {
            @Override
            protected void populateItem(ListItem<AuthUser> listItem) {
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var authUser = itemModel.getObject(); // 元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var userNameModel = Model.of(authUser.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var userPassModel = Model.of(authUser.getUserPass());
                var userPassLabel = new Label("userPass", userPassModel);
                listItem.add(userPassLabel);
            }
        };
        add(usersLV);

    }
}

