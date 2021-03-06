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

        Usr currentUsr = (Usr) req.getSession().getAttribute("currentUsr");
        Long group_id = currentUsr.getGroup_id();

        List<Usr> teachers = userService.findUsersByRole(group_id, Role.TEACHER);

        List<Usr> students = userService.findUsersByRole(group_id, Role.STUDENT);

        List<Lesson> lessons = lessonDao1.findByGroupId(group_id);

        Map<Long, List<HomeWork>> homeworks = lessons.stream()
                .map(l -> l.getId())
                .collect(Collectors.toList()).stream()
                .map(id -> homeWorkDao1.findByLessonsIds(id))
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
