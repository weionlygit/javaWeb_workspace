package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class EmpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        String uri= request.getRequestURI();
        if(uri.endsWith("regist")||uri.endsWith(".jsp")||uri.endsWith(".css")
                ||uri.endsWith(".gif")||uri.endsWith("loginpp")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            User user = (User) request.getSession().getAttribute("user");
            if(user==null){
                ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
