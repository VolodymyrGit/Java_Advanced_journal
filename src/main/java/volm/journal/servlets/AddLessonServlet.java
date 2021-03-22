package volm.journal.servlets;


import volm.journal.dao.HomeWorkDao1;
import volm.journal.dao.LessonDao1;
import volm.journal.enums.Role;
import volm.journal.model.HomeWork;
import volm.journal.model.Lesson;
import volm.journal.model.Usr;
import volm.journal.service.UsrService;
import volm.journal.service.impl.UsrServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet("/add_lesson")
public class AddLessonServlet extends HttpServlet {

    private LessonDao1 lessonDao1 = new LessonDao1();
    private UsrService usrService = new UsrServiceImpl();
    private HomeWorkDao1 homeWorkDao1 = new HomeWorkDao1();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long group_id = Long.parseLong(req.getParameter("group_id"));
        req.setAttribute("group_id", group_id);

        Lesson lesson = new Lesson(group_id, new Date());

        Lesson savedLesson = lessonDao1.save(lesson);

        List<Usr> students = usrService.findUsersByRole(lesson.getGroup_id(), Role.STUDENT);

        students.stream()
                .map(s -> new HomeWork(savedLesson.getId(), s.getId()))
                .forEach(hw -> homeWorkDao1.save(hw));

        resp.sendRedirect("/table?group_id=1");
    }
}
