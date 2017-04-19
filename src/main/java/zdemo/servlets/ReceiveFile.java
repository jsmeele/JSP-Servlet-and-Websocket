package zdemo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.List;


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/receiveFile")
public class ReceiveFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        try {
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    writer.println("fieldName = " + item.getFieldName());
                    writer.println("fileName = " + item.getName());
                    writer.println("contentType = " + item.getContentType());
                    writer.println("size [bytes] = " + item.getSize());
                    File uploadedFile = File.createTempFile("temp", ".txt");
                    item.write(uploadedFile);
                    writer.println("stored as  " + uploadedFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);}
	}

}
