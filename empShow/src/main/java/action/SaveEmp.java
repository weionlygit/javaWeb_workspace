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
import java.util.List;

@WebServlet(urlPatterns = {"/addEmp"})
public class SaveEmp extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

//        获取提交的数据 得到的就是input中输入的值
        String name= req.getParameter("name");
        Integer salary= Integer.parseInt(req.getParameter("salary"));
        String job= req.getParameter("job");
        System.out.println(name);
//      插入数据库
        SqlSession sqlSession= Sqlsession.getSqlSession(true);
        EmpMapper empMapper= sqlSession.getMapper(EmpMapper.class);

        empMapper.saveEmp(new Emp(null,name,salary,job));


//        刷新列表
        resp.sendRedirect("emplist");
//        req.getRequestDispatcher("emplist").forward(req,resp);

    }
}
