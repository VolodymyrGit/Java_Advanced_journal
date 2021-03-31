package volm.journal.servlets;


import volm.journal.dao.HomeWorkDao;
import volm.journal.dao.LessonDao;
import volm.journal.enums.Role;
import volm.journal.model.HomeWork;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.service.impl.UserServiceImpl;

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

    private LessonDao lessonDao = new LessonDao();
    private UserService userService = new UserServiceImpl();
    private HomeWorkDao homeWorkDao = new HomeWorkDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long group_id = Long.parseLong(req.getParameter("group_id"));
        req.setAttribute("group_id", group_id);

        Lesson lesson = new Lesson(group_id, new Date());

        Lesson savedLesson = lessonDao.save(lesson);

        List<User> students = userService.findUsersByRole(lesson.getGroup_id(), Role.STUDENT);

        students.stream()
                .map(s -> new HomeWork(savedLesson.getId(), s.getId()))
                .forEach(hw -> homeWorkDao.save(hw));

        resp.sendRedirect("/table");
    }
}
