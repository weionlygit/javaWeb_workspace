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

@WebServlet(urlPatterns = {"/update"})
public class UpdateEmp extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id=Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        Integer salary=Integer.parseInt(req.getParameter("salary"));
        String job=req.getParameter("job");

        SqlSession sqlSession = Sqlsession.getSqlSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        empMapper.updateEmp(new Emp(id,name,salary,job));

        resp.sendRedirect("emplist");
    }
}
