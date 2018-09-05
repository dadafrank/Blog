package servlet;

import dao.Articledao;
import model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "Admin_Article_change",urlPatterns = {"/Admin_Article_change"})
@MultipartConfig
public class Admin_Article_change extends HttpServlet {
    private String contextPath;
    @Override
    public void init() throws ServletException {
        contextPath = getServletContext().getRealPath("/upload");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Part part = request.getPart("file");
        String art_name = request.getParameter("art_name");
        String art_author = request.getParameter("art_author");
        String art_disc = request.getParameter("art_disc");
        String art_content = request.getParameter("art_content");
        String art_pic_url = request.getParameter("file2");
        int art_id =Integer.parseInt(request.getParameter("art_id"));

        if(art_name.equals("")||art_author.equals("")||art_disc.equals("")||art_content.equals("")){
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('发布失败，不能为空');window.location.href='/admin/article_change.jsp?art_id="+art_id+"'</script>");
            pw.close();
        }
        else {
            if(part.getSize()==0){
                Article m = new Article();
                m.setName(art_name);
                m.setAuthor(art_author);
                m.setDisc(art_disc);
                m.setContent(art_content);
                m.setId(art_id);
                m.setUrl(art_pic_url);
                if(Articledao.update(m)){
                    PrintWriter pw = response.getWriter();
                    pw.println("<script>alert('发布成功');window.location.href='/admin/article_manage.jsp'</script>");
                    pw.close();
                }
            }
            else{
                String filename = Math.random() + getFileName(part);
                writeTo(filename, part);
                art_pic_url ="/upload/"+ filename;

                Article m = new Article();
                m.setName(art_name);
                m.setAuthor(art_author);
                m.setDisc(art_disc);
                m.setContent(art_content);
                m.setId(art_id);
                m.setUrl(art_pic_url);

                if(Articledao.update(m)){
                    PrintWriter pw = response.getWriter();
                    pw.println("<script>alert('发布成功');window.location.href='/admin/article_manage.jsp'</script>");
                    pw.close();
                }
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
