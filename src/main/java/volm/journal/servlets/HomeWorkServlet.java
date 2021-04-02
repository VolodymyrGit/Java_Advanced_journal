package volm.journal.servlets;

import volm.journal.dao.impl.HomeWorkDaoImpl;
import volm.journal.model.Homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hw")
public class HomeWorkServlet extends HttpServlet {

    private HomeWorkDaoImpl homeWorkDaoImpl = new HomeWorkDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String hwId = req.getParameter("hwId");
        req.setAttribute("hwId", hwId);

        Homework hwById = homeWorkDaoImpl.findById(Long.parseLong(hwId));
        req.setAttribute("description", hwById.getHw_description());
        req.getRequestDispatcher("hwForm.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("description");
        Long hwId = Long.parseLong(req.getParameter("hwId"));

        Homework homeWork = homeWorkDaoImpl.findById(hwId);
        homeWork.setHw_description(description);
        homeWorkDaoImpl.updateHomeworkDescription(homeWork);

        resp.sendRedirect("/table");
    }
}
