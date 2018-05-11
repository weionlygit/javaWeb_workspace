package action;

import dao.LoginMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.Sqlsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={"/loginpp"} )
public class Login extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

        String name = req.getParameter("name");
        String password = req.getParameter("pwd");
        System.out.println(name+password);

        SqlSession sqlSession = Sqlsession.getSqlSession(true);
        LoginMapper loginMapper= sqlSession.getMapper(LoginMapper.class);
        User user= loginMapper.login(name);

        System.out.println(user);
        if(user!=null && password.equals(user.getPassword())){

            //放在这里面
            HttpSession httpSession = req.getSession();
            httpSession.setMaxInactiveInterval(5);
            httpSession.setAttribute("user",user);

            resp.sendRedirect("emplist");
        }else{
            resp.sendRedirect("login.jsp");
        }
    }
}
