package volm.journal.servlets;

import volm.journal.enums.Role;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.service.impl.UserServiceImpl;
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

    private final UserService userService = new UserServiceImpl();


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

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        User changedUser = new User(id, name, email, phone, groupId, role, newHashedPassword, salt);

        String hashedPassword = SecurityUtil.getSecurePassword(password, currentUser.getSalt());

        if (!currentUser.getPassword().equals(hashedPassword)) {
            String errorMessage = "You entered wrong current password";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("changeInfo.jsp").forward(req, resp);
        }

        userService.updateUser(changedUser);

        HttpSession session = req.getSession();
        session.removeAttribute("currentUser");
        session.setAttribute("currentUser", changedUser);

        resp.sendRedirect("/cabinet");
    }
}
