package servlet;

import dao.Art_messdao;
import model.Art_mess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



@WebServlet(name = "WriteArt_mess",urlPatterns = {"/WriteArt_mess"})
public class WriteArt_mess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");
        int art_id = Integer.parseInt(request.getParameter("art_id"));
        String mess_content = request.getParameter("mess_content");
        mess_content = changechar(mess_content);
        String mess_author = request.getParameter("mess_name");
        mess_author = changechar(mess_author);
        String mess_email = request.getParameter("mess_email");
        mess_email = changechar(mess_email);
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = date.format(new Date());
        if( mess_author.equals("")||mess_content.equals("")||mess_email.equals("")){
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('发布失败，不能为空');window.location.href='article.jsp?art_id="+art_id+"'</script>");
            pw.close();
        }
        else {
            Art_mess a = new Art_mess();
            a.setArt_id(art_id);
            a.setContent(mess_content);
            a.setAuthor(mess_author);
            a.setEmail(mess_email);
            a.setTime(time);


            Art_messdao.insert(a);
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('发布成功');window.location.href='article.jsp?art_id="+art_id+"'</script>");
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
