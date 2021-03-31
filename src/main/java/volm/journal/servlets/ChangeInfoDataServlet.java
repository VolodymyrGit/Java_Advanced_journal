package volm.journal.servlets;

import volm.journal.enums.Role;
import volm.journal.model.Usr;
import volm.journal.service.UsrService;
import volm.journal.service.impl.UsrServiceImpl;
import volm.journal.util.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/changeinfo")
public class ChangeInfoDataServlet extends HttpServlet {

    private final UsrService usrService = new UsrServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("changeInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Long groupId = Long.parseLong(req.getParameter("groupId"));
        Role role = Role.valueOf(req.getParameter("role"));
        String password = req.getParameter("password");
        String npassword = req.getParameter("npassword");

        String salt = SecurityUtil.generateRandomSalt();
        String newHashedPassword = SecurityUtil.getSecurePassword(npassword, salt);

        Usr currentUsr = (Usr) req.getSession().getAttribute("currentUsr");

        Usr changedUser = new Usr(id, name, email, phone, groupId, role, newHashedPassword, salt);

        String hashedPassword = SecurityUtil.getSecurePassword(password, currentUsr.getSalt());

        if (!currentUsr.getPassword().equals(hashedPassword)) {
            String errorMessage = "You entered wrong current password";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("changeInfo.jsp").forward(req, resp);
        }

        usrService.updateUser(changedUser);

        HttpSession session = req.getSession();
        session.removeAttribute("currentUsr");
        session.setAttribute("currentUsr", changedUser);

        resp.sendRedirect("/cabinet");
    }
}
