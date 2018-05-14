package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/exit"})
public class Exit extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        删除session   不用删  cookie删了，就直接退了
//          这样虽然删了  但是还能跳进页面
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("user");

//        创建新的cookie 把之前的顶掉，，，模拟删除
        Cookie myCookie= new Cookie("username","");
        myCookie.setMaxAge(5);
        resp.addCookie(myCookie);
//无论删不删 都能跳到登录页   但是不删Cookie的话 地址栏还能进入其他页   和上面session一样的问题
        resp.sendRedirect("login.jsp");
    }
}
