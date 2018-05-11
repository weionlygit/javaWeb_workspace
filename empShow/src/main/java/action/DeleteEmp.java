package action;

import dao.EmpMapper;
import org.apache.ibatis.session.SqlSession;
import util.Sqlsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/delEmp"})
public class DeleteEmp extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id=Integer.parseInt(req.getParameter("id"));

        SqlSession sqlSession = Sqlsession.getSqlSession(true);
        EmpMapper empMapper= sqlSession.getMapper(EmpMapper.class);
        empMapper.deleteEmp(id);
//      转发不行，，地址栏没变 不会刷新
//        req.getRequestDispatcher("emplist.jsp").forward(req,resp);
        resp.sendRedirect("emplist");
    }
}
