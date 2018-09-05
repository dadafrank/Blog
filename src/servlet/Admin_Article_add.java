package servlet;

import dao.Articledao;
import dao.Messagedao;
import model.Article;
import model.Message;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "Admin_Article_add",urlPatterns = {"/Admin_Article_add"})
@MultipartConfig
public class Admin_Article_add extends HttpServlet {
    private String contextPath;
    @Override
    public void init() throws ServletException {
        contextPath = getServletContext().getRealPath("/upload");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");

        Part part = request.getPart("file");
        String art_name = request.getParameter("art_name");
        String art_author = request.getParameter("art_author");
        String art_disc = request.getParameter("art_disc");
        String art_content = request.getParameter("art_content");

        if(part.getSize()==0||art_name.equals("")||art_author.equals("")||art_disc.equals("")||art_content.equals("")){
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('发布失败，不能为空');window.location.href='/admin/article_add.jsp'</script>");
            pw.close();
        }
        else {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间格式
            String time = date.format(new Date());
            String filename = Math.random() + getFileName(part);
            writeTo(filename, part);
            String art_pic_url ="/upload/"+ filename;
            System.out.println(art_pic_url);

            Article m = new Article();
            m.setName(art_name);
            m.setAuthor(art_author);
            m.setDisc(art_disc);
            m.setContent(art_content);
            m.setTime(time);
            m.setUrl(art_pic_url);

            if(Articledao.insert(m)){
                PrintWriter pw = response.getWriter();
                pw.println("<script>alert('发布成功');window.location.href='/admin/article_manage.jsp'</script>");
                pw.close();
            }
        }




}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //获取文件名字
    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
        return fileName;
    }
    //写入文件
    private void writeTo(String fileName, Part part) throws IOException, FileNotFoundException {
        InputStream in = part.getInputStream();
        OutputStream out = new FileOutputStream(contextPath+"\\" + fileName);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }
}
