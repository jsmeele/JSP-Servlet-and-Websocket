package zdemo.sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.net.ServerSocket;
//import java.io.InputStream;
import java.net.Socket;

import consts.Constants;


/**
 * 
 * @author jsmeele
 *
 */
public class ReceiveFile implements Runnable {
	private static String file = Constants.AUTO_FILELOC + "\\TEST";
	private ServerSocket ss;
	private static final int SERVER_PORT = Integer.parseInt(Constants.DEFAULT_FILE_RECEIVE_PORT);
	
	public ReceiveFile(int port) {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			System.out.println("Server has started on 127.0.0.1:" + Constants.DEFAULT_FILE_RECEIVE_PORT + ".\r\nWaiting for a connection...");
			try {
				Socket clientSock = ss.accept();
				System.out.println("A client connected.");
				saveFile(clientSock);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile(Socket clientSock) throws IOException {
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		int filesize = dis.readInt();
		int read = 0;
		int totalRead = 0;
		int remaining = filesize;
		byte[] buffer = new byte[4096];
		FileOutputStream fos = new FileOutputStream(file + "." + dis.readUTF());

		while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}
		
		fos.close();
		dis.close();
	}
	
    public static void main(String[] args){
    	//test();
    	ReceiveFile fs = new ReceiveFile(SERVER_PORT);
		fs.run();//.start();
    }
    
//    private static void test()
//    {
//    	FileOutputStream fos = null;
//    	//BufferedInputStream bis = null;
//    	//BufferedOutputStream bos = null;
//    	InputStream input = null;
//        ServerSocket serverSocket = null;
//        Socket socket = null; 
//        int bytesRead;
//        int current = 0;
//        
//		try 
//		{
//			serverSocket = new ServerSocket(8080);
//			while (true)
//			{
//				System.out.println("Server has started on 127.0.0.1:8080.\r\nWaiting for a connection...");
//				//try {
//					socket = serverSocket.accept();		
//			        //System.out.println(socket.toString());
//			        System.out.println("A client connected.");
//					
//			        //Receive file
//			        byte[] myByteArray2 = new byte[6022386];  //hardcoded en must be bigger than file to be received
//			        input = socket.getInputStream();
//			        fos = new FileOutputStream(file);
//			        //bos = new BufferedOutputStream(fos);
//			        bytesRead = input.read(myByteArray2); //,0,myByteArray2.length);
//			        current = bytesRead;
//			        
//			        System.out.println("----------------------------");
//			        do {
//			        	bytesRead = input.read(myByteArray2, current, (myByteArray2.length-current));
//			        	if (bytesRead >=0) current += bytesRead;
//			        } while(bytesRead > -1);
//		       
//			        fos.write(myByteArray2,0,current);
//			        fos.flush();
//			        fos.close();
//			        System.out.println("File saved " + current);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
}
