package volm.journal.filter;


import volm.journal.dao.impl.HomeWorkDaoImpl;
import volm.journal.model.Homework;
import volm.journal.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;


@WebFilter("/hw")
public class HomeworkFilter implements Filter{

    private HomeWorkDaoImpl homeWorkDaoImpl = new HomeWorkDaoImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        User currentUser = (User) httpRequest.getSession().getAttribute("currentUser");

        String hwId = httpRequest.getParameter("hwId");
        Homework homeWork = homeWorkDaoImpl.findById(Long.parseLong(hwId));

        if (Objects.nonNull(currentUser) && currentUser.getId() != homeWork.getStudent_id()) {

            httpRequest.setAttribute("message", "It is not your homework");
            httpRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
