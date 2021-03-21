package volm.journal.servlets;

import volm.journal.dao.HomeWorkDao1;
import volm.journal.model.HomeWork;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hw")
public class HomeWorkServlet extends HttpServlet {

    private HomeWorkDao1 homeWorkDao1 = new HomeWorkDao1();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String hwId = req.getParameter("hwId");
        req.setAttribute("hwId", hwId);

        String description = req.getParameter("desc");
        req.setAttribute("description", description);

        req.getRequestDispatcher("hwForm.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("description");
        long hwId = Long.parseLong(req.getParameter("hwId"));

        HomeWork homeWork = homeWorkDao1.findById(hwId);
        homeWork.setHw_description(description);
        homeWorkDao1.save(homeWork);

        resp.sendRedirect("/table?group_id=1");
    }
}
