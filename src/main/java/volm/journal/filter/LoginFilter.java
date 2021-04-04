package volm.journal.filter;

import volm.journal.model.Usr;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter({"/login", "/registration"})
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        Usr currentUsr = (Usr) httpRequest.getSession().getAttribute("currentUsr");

        if(currentUsr != null) {

            req.setAttribute("message", "You are already registered");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
            filterChain.doFilter(req, resp);
    }


    @Override
    public void destroy() {

    }
}
