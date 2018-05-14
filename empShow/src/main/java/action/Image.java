package action;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(urlPatterns ={"/image"})
public class Image extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       创建空白图片
        BufferedImage image =new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
//        获取图片画笔
        Graphics g = image.getGraphics();
//        画一个背景图片
        Random r =new Random();
        g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        g.fillRect(0,0,60,20);
//      画笔改变颜色 去画数字
        g.setColor(Color.BLACK);
//        图片生成随机数
        String code ="";
        for (int i = 0; i < 4; i++) {
            code+=r.nextInt(10);
        }
        g.drawString(code,15,15);

//        添加删除线，防止获取图片数字 起点坐标 终点坐标
        for (int i = 0; i < 3; i++) {
            g.drawLine(r.nextInt(60),r.nextInt(20),r.nextInt(60),r.nextInt(20));
        }

//        将图片响应给浏览器
        resp.setContentType("image/jpeg");
        OutputStream out = resp.getOutputStream();
        JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
    }
}
