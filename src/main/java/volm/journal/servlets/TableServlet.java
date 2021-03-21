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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    private UsrService userService = new UsrServiceImpl();
    private HomeWorkDao1 homeWorkDao1 = new HomeWorkDao1();
    private LessonDao1 lessonDao1 = new LessonDao1();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long group_id = Long.parseLong(req.getParameter("group_id"));

        List<Usr> users = userService.findByGroupId(group_id);

        List<Usr> teachers = filterByRole(users, Role.TEACHER);

        List<Usr> students = filterByRole(users, Role.STUDENT);

        List<Lesson> lessons = lessonDao1.findByGroupId(group_id);

        List<Long> lessonsIds = lessons.stream()
                .map(l -> l.getId())
                .collect(Collectors.toList());

        final Map<Long, List<HomeWork>> homeworks = lessonsIds.stream()
                .map(id -> homeWorkDao1.findByLessonsIds(id))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(hw -> hw.getStudent_id(), Collectors.toList()));


        req.setAttribute("teachers", teachers);
        req.setAttribute("students", students);
        req.setAttribute("lessons", lessons);
        req.setAttribute("homeworks", homeworks);

        req.getRequestDispatcher("table.jsp").forward(req, resp);
    }

    private List<Usr> filterByRole(List<Usr> users, Role role) {
        return users.stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
    }
}
