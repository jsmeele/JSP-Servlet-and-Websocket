package zdemo.sockets;

import consts.Constants;
/**
 * 
 * @author jsmeele
 *
 */
public class App 
{
    public static void main( String[] args )
    {   	
        System.out.println( "Hello World! " + System.getProperty("os.name"));
        System.out.println(Constants.AUTO_FILELOC);
        System.out.println(Constants.PROP.getProperty("manualFileLoc"));
        
        //Process in separate thread for receiving files. 
        ReceiveFile.main(null);
        
        System.out.println("test");
        
//        for (int i=0; i < 10; i++) {
//        	try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	System.out.println("test " + i);
//        }
    }
}
