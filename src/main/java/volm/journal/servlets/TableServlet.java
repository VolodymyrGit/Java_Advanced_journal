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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private HomeWorkDao homeWorkDao = new HomeWorkDao();
    private LessonDao lessonDao = new LessonDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        Long group_id = currentUser.getGroup_id();

        List<User> users = userService.findByGroupId(group_id);

        List<User> teachers = userService.findUsersByRole(group_id, Role.TEACHER);

        List<User> students = userService.findUsersByRole(group_id, Role.STUDENT);

        List<Lesson> lessons = lessonDao.findByGroupId(group_id);

        List<Long> lessonsIds = lessons.stream()
                .map(l -> l.getId())
                .collect(Collectors.toList());

        Map<Long, List<HomeWork>> homeworks = lessonsIds.stream()
                .map(id -> homeWorkDao.findByLessonsIds(id))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(hw -> hw.getStudent_id(), Collectors.toList()));


        req.setAttribute("teachers", teachers);
        req.setAttribute("students", students);
        req.setAttribute("lessons", lessons);
        req.setAttribute("homeworks", homeworks);
        req.setAttribute("group_id", group_id);

        req.getRequestDispatcher("table.jsp").forward(req, resp);
    }
}
