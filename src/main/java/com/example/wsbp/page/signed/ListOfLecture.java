package com.example.wsbp.page.signed;

import com.example.wsbp.data.Lecture;
import com.example.wsbp.repository.ILectureRepository;
import com.example.wsbp.repository.ISubjectRepository;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Lecture")

public class ListOfLecture extends WebPage {

    @SpringBean
    private ISubjectRepository subjectRepos;

    @SpringBean
    private ILectureRepository lectureRepos;

    public ListOfLecture(PageParameters parameters) {
        super(parameters);

        // Subject IDをパラメータから取得
        int subjectID = parameters.get("subjectID").toInt();
        System.out.println("subjectID: " + subjectID);
        // Subject名を取得
        String subjectName = subjectRepos.findByID(subjectID).getSubjectName();
        String subjectLabel = subjectName + " の講義一覧";
        // Subject名を表示するラベルを追加
        add(new Label("subjectName", subjectLabel));

        // とりあえず1回目の授業の情報を出す
        // lecture情報を取得
        List<Lecture> lecture1 = lectureRepos.findLectureBySubjectID(subjectID);
        List<Integer> lectureIDs = new ArrayList<>();
        List<String> details = new ArrayList<>();
        List<Integer> serialNumbers = new ArrayList<>();
        List<String> lectureNums = new ArrayList<>();
        for (Lecture lecture : lecture1) {
            String detail = lecture.getDetail();
            int lectureID = lecture.getLectureID();
            int serialNumber = lecture.getLectureSerialNum();
            String lectureNum = serialNumber + "回目の授業";
            details.add(detail);
            lectureIDs.add(lectureID);
            serialNumbers.add(serialNumber);
            lectureNums.add(lectureNum);
            System.out.println("detail: " + detail);
            System.out.println("serialNumber: " + serialNumber);
            System.out.println("lectureID: " + lectureID);
        }

        List<Lecture> lectures = lectureRepos.findLectureBySubjectID(subjectID);

        ListView<Lecture> LectureList = new ListView<Lecture>("lectureList", lectures) {
            @Override
            protected void populateItem(ListItem<Lecture> item) {
                Lecture lecture = item.getModelObject();
                item.add(new Label("attendanceStatus", "あなたの出席状況：-"));
                item.add(new MultiLineLabel("Detail", (lecture.getDetail())));
                item.add(new Label("serialNumber", (lecture.getLectureSerialNum() + " 回目の授業")));
            }
        };
        add(LectureList);
    }
}