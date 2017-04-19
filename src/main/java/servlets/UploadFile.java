package servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.File;
//import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import consts.Constants;


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/uploadFile")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
				 maxFileSize=1024*1024*50,      	// 50 MB
				 maxRequestSize=1024*1024*100)   	// 100 MB

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = Constants.AUTO_FILELOC;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // creates the upload directory if it does not exists
		String absolutePath = createUploadDirectory(UPLOAD_DIR);
        System.out.println("Upload File Directory="+absolutePath);
        
        String fileName = null;
        //Get all the parts from the request and write the file to the upload directory
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            //System.out.println(UPLOAD_DIR + File.separator + fileName);
            part.write(UPLOAD_DIR + File.separator + fileName);
        }
        
        //Return a message
        request.setAttribute("message", fileName + " File uploaded successfully!");
        getServletContext().getRequestDispatcher("/WEB-INF/response.jsp").forward(
                request, response);
    }
	
	
	/**
	 * Utility method to create the upload directory if not available
	 */
	private String createUploadDirectory (String directory) {
        File fileSaveDir = new File(directory);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        return fileSaveDir.getAbsolutePath();
	}

    /**
     * Utility method to get filename from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
            	String filename = token.substring(token.indexOf("=") + 2, token.length()-1);
            	Path path = Paths.get(filename);

                return path.getFileName().toString();
            }
        }
        return "";
    }
	
//	private void uploadTest1() {
//        String content = request.getParameter("filename");
//        assertNotEmpty("content", content);
//        //File file = File.createTempFile("temp", ".txt");  //ORIGINAL
//        File file = File.createTempFile("temp", ".txt", new File(UPLOAD_DIR));
//        System.out.println(file.toPath());
//        Files.write(file.toPath(), content.getBytes());
//        //String url = request.getParameter("url");
//        //assertNotEmpty("url", url);
//
//        response.setContentType("text/plain");
////        PrintWriter writer = response.getWriter();
////        CloseableHttpClient httpClient = HttpClients.createDefault();
////        try {
////            FileBody bin = new FileBody(file);
////            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).build();
////            HttpPost httpPost = new HttpPost(url);
////            httpPost.setEntity(reqEntity);
////
////            CloseableHttpResponse postResponse = httpClient.execute(httpPost);
////            try {
////                writer.println(postResponse.getStatusLine());
////                HttpEntity postResponseEntity = postResponse.getEntity();
////                if (postResponseEntity != null) {
////                    writer.println(EntityUtils.toString(postResponseEntity));
////                }
////            } finally {
////                postResponse.close();
////            }
////        } finally {
////            httpClient.close();
////        }
//	}
//
//    private void assertNotEmpty(String param, String value) throws ServletException {
//        if (value == null || value.trim().length() == 0) {
//            throw new ServletException("Mandatory parameter '" + param + "' is empty!");
//        }
//    }

}
