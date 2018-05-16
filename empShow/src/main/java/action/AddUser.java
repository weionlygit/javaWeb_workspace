package action;

import dao.AddUserMapper;
import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import util.Sqlsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = {"/addUser"})
public class AddUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String> parm =new HashMap<>();
        DiskFileItemFactory fileItemFactory =new DiskFileItemFactory();
//        创建一个请求的解析器
        ServletFileUpload sfu= new ServletFileUpload(fileItemFactory);
        try {
            List<FileItem> itemList= sfu.parseRequest(req);
            for (FileItem fileItem : itemList) {
                if (fileItem.isFormField()) {
                    String name=fileItem.getFieldName();
                    String value=fileItem.getString("utf8");
                    parm.put(name,value);
                } else {
//                    文件域
                    String path =req.getServletContext().getRealPath("upload/");
                    File f=new File(path);
                    if(!f.exists()){
                        f.mkdirs();
                    }
                    String filePath = path+ UUID.randomUUID().toString() +fileItem.getName().substring(fileItem.getName().indexOf("."));
                    File file = new File(filePath);
//                    指向本地
                    OutputStream out = new FileOutputStream(file);
//                    从服务器获取的输入流
                    InputStream in = fileItem.getInputStream();
                    byte[] b = new byte[8];
                    while(in.read(b)!=-1){
                        out.write(b);
                    }
                    parm.put("img_path",filePath.substring(filePath.indexOf("upload")));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
/**
 * 集合遍历  用于测试
 */
//        Set<Map.Entry<String,String>> set =parm.entrySet();
//        for(Map.Entry<String,String> stringStringEntry:set){
//            System.out.println(stringStringEntry.getKey());
//            System.out.println(stringStringEntry.getValue());
//        }

        /**
         * 注册用户插入数据库
         */
//        通过上面的方法获取也行
        String imgpath= parm.get("img_path");
//         这样就获取不到了
//        String userName = req.getParameter("userNameW");
//        String name = req.getParameter("name");
//        String pwd = req.getParameter("pwd");
//        String sex = req.getParameter("sex");

        String userName = parm.get("userNameW");
        String name = parm.get("name");
        String pwd = parm.get("pwd");
        String sex = parm.get("sex");
        SqlSession sqlSession = Sqlsession.getSqlSession(true);
        AddUserMapper addUserMapper= sqlSession.getMapper(AddUserMapper.class);
        addUserMapper.saveUser(new User(userName,pwd,name,sex,imgpath));

        resp.sendRedirect("login.jsp");
    }
}
