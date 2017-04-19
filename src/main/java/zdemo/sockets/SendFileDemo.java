package zdemo.sockets;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import consts.Constants;


/**
 * 
 * @author a500648
 *
 */
public class SendFileDemo {
	
	private Socket s;
	private static final int SERVER_PORT = Integer.parseInt(Constants.DEFAULT_FILE_RECEIVE_PORT);
	//private int length = 15928;
	//private String fileType = "DOCX";
	
	public SendFileDemo(String host, int port, String file) {
		try {
			s = new Socket(host, port);
			sendFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void sendFile(String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		//File doc = new File(file);
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		int read = 0;
		
		//System.out.println(doc.length() + "  " + fis.getChannel().size());
		dos.writeInt((int) fis.getChannel().size());
		dos.writeUTF(file.substring(file.length() - new StringBuilder(file).reverse().indexOf(".")));
		while ((read = fis.read(buffer)) > 0) {
			//dos.write(buffer);
			dos.write(buffer, 0, read);
		}
		
		fis.close();
		dos.close();	
	}
	
	public static void main(String[] args) {
		/*FileClient fc = */new SendFileDemo("localhost", SERVER_PORT, "C:\\Users\\a500648\\Documents\\Introduction.docx");
	}

}