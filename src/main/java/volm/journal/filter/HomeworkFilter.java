package volm.journal.filter;


import volm.journal.dao.HomeWorkDao;
import volm.journal.model.HomeWork;
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

    private HomeWorkDao homeWorkDao = new HomeWorkDao();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        User currentUser = (User) httpRequest.getSession().getAttribute("currentUser");

        String hwId = httpRequest.getParameter("hwId");
        HomeWork homeWork = homeWorkDao.findById(Long.parseLong(hwId));

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
