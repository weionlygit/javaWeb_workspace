package action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;
//-----------------------------------可以 获取  还没理解  这个在注册里写  这里就用不到了
@WebServlet(urlPatterns = {"/filePic"})
public class FilePic extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      构建一个工厂
        DiskFileItemFactory fileItemFactory =new DiskFileItemFactory();
//        创建一个请求的解析器
        ServletFileUpload sfu= new ServletFileUpload(fileItemFactory);
        try {
//            使用请求解析器解析对象  返回所有表单
            List<FileItem> itemList= sfu.parseRequest(req);

            for (FileItem fileItem : itemList) {
//                常规表单域
                if (fileItem.isFormField()) {
                    System.out.println(fileItem.getName());
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
                    in.close();
                    out.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}

