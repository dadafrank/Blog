package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "Texttwo",urlPatterns = {"/Texttwo"})
@MultipartConfig(location = "/upload")
public class Texttwo extends HttpServlet {
    private String contextPath;

    @Override
    public void init() throws ServletException {
        contextPath = getServletContext().getRealPath("/upload");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String filename = getFileName(part);
        System.out.println(filename);
        writeTo(filename, part);
        System.out.println(contextPath+filename);
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
