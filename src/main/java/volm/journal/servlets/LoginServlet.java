package volm.journal.servlets;

import volm.journal.service.UsrService;
import volm.journal.service.impl.UsrServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UsrService usrService = new UsrServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (usrService.authorized(login, password)) {

            HttpSession session = req.getSession();
            session.setAttribute("currentUsr", usrService.findUsrByEmail(login));

            resp.sendRedirect("/cabinet");
        } else {
            String errorMessage = "Wrong login or password";
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("previousLogin", login);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

