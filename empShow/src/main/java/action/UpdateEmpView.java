package action;

import dao.EmpMapper;
import entity.Emp;
import org.apache.ibatis.session.SqlSession;
import util.Sqlsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/UpdateEmpView"})
public class UpdateEmpView extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id =Integer.parseInt(req.getParameter("id"));

        SqlSession sqlSession = Sqlsession.getSqlSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp= empMapper.selectEmpById(id);
//          测试
//        System.out.println(emp);
        req.setAttribute("empClick",emp);
        req.getRequestDispatcher("updateEmp.jsp").forward(req,resp);
    }
}
