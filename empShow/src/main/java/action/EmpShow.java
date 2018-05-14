package action;

import dao.EmpMapper;
import entity.Emp;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.Sqlsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/emplist"})
public class EmpShow extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        SqlSession sqlSession= Sqlsession.getSqlSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        resp.setCharacterEncoding("utf8");
//        浏览器解析h5等时的编码格式
        resp.setContentType("text/html;charset=utf8");

        List<Emp> empList = empMapper.listEmp();
//      将集合绑定到req上
        req.setAttribute("emplist",empList);
//       刷新页面 转发
        req.getRequestDispatcher("emplist.jsp").forward(req,resp);

//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(this.getClass()
//        .getClassLoader().getResourceAsStream("mybatis.xml"));
//        SqlSession sqlSession =sqlSessionFactory.openSession(true);
//        EmpMapper empMapper= sqlSession.getMapper(EmpMapper.class);
//        List<Emp> list = empMapper.listEmp();
//        for (Emp emp : list) {
//            System.out.println(emp);
//        }
    }
}
