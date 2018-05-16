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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/checkUserName"})
public class CheckUsername extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name= req.getParameter("username");
        SqlSession sqlSession = Sqlsession.getSqlSession(true);
        LoginMapper loginMapper= sqlSession.getMapper(LoginMapper.class);
        User user= loginMapper.login(name);
        PrintWriter out =resp.getWriter();
        if(user==null){
            out.print(true);
        }else{
            out.print(false);
        }
    }
}
