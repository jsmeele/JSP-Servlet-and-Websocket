package consts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 * @author a500648
 *
 */
public class Constants {
    public static final String PROPERTY_FILE = "config.properties";
    public static final String DEFAULT_WIN_AUTO_FILELOC = "C:\\Users\\user\\Documents\\workspace\\CustomContentClassifier\\DocLocation\\New";
    public static final String DEFAULT_WIN_MANUAL_FILELOC = "C:\\Users\\user\\Documents\\workspace\\CustomContentClassifier\\DocLocation\\Manual";
    public static final String DEFAULT_FILE_RECEIVE_PORT = "8000";
    
    public static final Properties PROP = getProperties();
    public static final String AUTO_FILELOC = PROP.getProperty("autoFileLoc"); //getAutofileLoc("autoFileLoc");
    public static final String MANUAL_FILELOC = getAutofileLoc("manualFileLoc");
    
    private static Properties getProperties()
    {
    	Properties prop = new Properties();
    	InputStream input = null;
    	
    	try 
    	{
    		input = new FileInputStream(Constants.PROPERTY_FILE);

    		// load a properties file
    		prop.load(input);
    		input.close();
    		
    		return prop;
    	} catch (IOException ex) {
    		//ex.printStackTrace();
    		createDefaultConfigFile();
    		
    		return getProperties();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    private static void createDefaultConfigFile()
    {
    	Properties prop = new Properties();
    	OutputStream output = null;

    	try {

    		output = new FileOutputStream(Constants.PROPERTY_FILE);

    		// set the default properties value, depending on the OS
        	String os = System.getProperty("os.name").toLowerCase();
        	if (os.contains("win"))
        	{
        	    //Operating system is based on Windows
        		prop.setProperty("autoFileLoc", DEFAULT_WIN_AUTO_FILELOC);
        		prop.setProperty("manualFileLoc", DEFAULT_WIN_MANUAL_FILELOC);
        		prop.setProperty("port", DEFAULT_FILE_RECEIVE_PORT);
        	}
        	else if (os.contains("osx"))
        	{
        	    //Operating system is Apple OSX based
        		//TODO
        	}      
        	else if (os.contains("nix") || 
        			 os.contains("aix") || 
        			 os.contains("nux"))
        	{
        	    //Operating system is based on Linux/Unix/*AIX
        		//TODO
        	}

    		// save properties to project root folder
    		prop.store(output, null);

    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (output != null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}

    	}
    }
    
    private static String getAutofileLoc(String propName)
    {
   		// get the property value and print it out
    	return PROP.getProperty(propName);
    }
}
