package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/checkAuthcode"})
public class CheckAuthcode  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num= req.getParameter("authCode");

        HttpSession httpSession=req.getSession();
        String sessionNum= (String) httpSession.getAttribute("authCode");

        PrintWriter out =resp.getWriter();
        if(sessionNum.trim().equals(num)){
            out.print(true);
        }else{
            out.print(false);
        }
    }
}
