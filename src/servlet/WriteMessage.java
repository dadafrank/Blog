package servlet;

import dao.Messagedao;
import model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "WriteMessage",urlPatterns = {"/WriteMessage"})
public class WriteMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");

        String author = request.getParameter("name");
        author = changechar(author);
        String email = request.getParameter("email");
        email = changechar(email);
        String content = request.getParameter("content");
        content = changechar(content);

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间格式
        String time = date.format(new Date());
        if(author.equals("")||email.equals("")||content.equals("")){
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('发布失败，不能为空');window.location.href='blog_mess.jsp'</script>");
            pw.close();
        }
        else {
            Message m = new Message();
            m.setAuthor(author);
            m.setEmail(email);
            m.setContent(content);
            m.setTime(time);


            Messagedao.insert(m);

            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('发布成功');window.location.href='blog_mess.jsp'</script>");
            pw.close();
        }







    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static String changechar(String content){
        content = content.replaceAll("<","&lt;");
        content = content.replaceAll(">","&gt;");
        content = content.replaceAll(" ","&nbsp");
        return  content;
    }
}
