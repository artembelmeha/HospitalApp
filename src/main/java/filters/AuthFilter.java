package filters;

import model.dto.UserDto;
import model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String contextPath = req.getContextPath().toLowerCase();
        String path = req.getRequestURI().toLowerCase();
        String mod = path.replaceFirst(".*api/","");
        UserDto currentUser = (UserDto) session.getAttribute("user");

        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0); // Proxies.

        if (currentUser != null && mod.contains("/")) {
            if (!path.contains(currentUser.getRole().name().toLowerCase())) {
                req.getRequestDispatcher(contextPath+"/access_denied.jsp").forward(req, res);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
