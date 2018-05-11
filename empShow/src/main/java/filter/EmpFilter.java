package filter;

import dao.LoginMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.CookieUtil;
import util.Sqlsession;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute("user");
//            session中没有数据
            if(user==null){
//                判断Cookie
                Cookie[] cookies= request.getCookies();
//                从这个数组中找 不好找，放到map集合中，
                Map<String,Cookie> cookieMap = CookieUtil.getCookieMap(cookies);
                Cookie userCookie = cookieMap.get("username");

               if(userCookie!=null){
                   String username=userCookie.getValue();

                   SqlSession sqlSession = Sqlsession.getSqlSession(true);
                   LoginMapper loginMapper =sqlSession.getMapper(LoginMapper.class);
                   User u= loginMapper.login(username);
//                      找到后再存到session中 emplist中要用
                   httpSession.setAttribute("user",u);

                   filterChain.doFilter(servletRequest,servletResponse);
               }else{
                   ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");
               }

            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
