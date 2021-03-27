package volm.journal.servlets;

import volm.journal.enums.Role;
import volm.journal.model.Usr;
import volm.journal.service.UsrService;
import volm.journal.service.impl.UsrServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UsrService usrService = new UsrServiceImpl();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Long groupId = Long.parseLong(req.getParameter("groupId"));
        Role role = Role.valueOf(req.getParameter("role"));
        String password = req.getParameter("password");

        Usr user = new Usr(name, email, phone, groupId, role, password);

        Usr savedUsr = usrService.save(user);

        HttpSession session = req.getSession();
        session.setAttribute("currentUsr", savedUsr);

        resp.sendRedirect("/cabinet");
    }
}
