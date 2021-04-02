package volm.journal.servlets;


import volm.journal.dao.impl.HomeWorkDaoImpl;
import volm.journal.dao.impl.LessonDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
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


@WebServlet("/add-lesson")
public class AddLessonServlet extends HttpServlet {

    private LessonDaoImpl lessonDaoImpl = new LessonDaoImpl();
    private UserService userService = new UserServiceImpl();
    private HomeWorkDaoImpl homeWorkDaoImpl = new HomeWorkDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        Group group = currentUser.getGroup();
//        req.setAttribute("groupId", groupId);

        Lesson savedLesson = lessonDaoImpl.save(new Lesson(group, new Date()));

        List<User> students = userService.findUsersByRole(group.getId(), Role.STUDENT);

        students.stream()
                .map(s -> new Homework(savedLesson, s))
                .forEach(hw -> homeWorkDaoImpl.save(hw));

        resp.sendRedirect("/table");
    }
}
