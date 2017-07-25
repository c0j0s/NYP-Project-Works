package common;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.Part;

public class FileManager {

	public static String getFile(String folder,String filename){		
		return "\\FFL\\uploads\\" + folder + "\\" + filename;
	}
	/**
	 * 
	 * @param path for file to be saved
	 * @param parts 
	 * @return String
	 * @throws IOException
	 */
	public static String upload(String path,Collection<Part> parts) throws IOException{
	 String appPath = System.getProperty("catalina.base");
        String savePath = appPath.substring(0, appPath.indexOf('.')) + "FFL\\WebContent\\uploads\\" + path;
        String filePath = null;
        
        System.out.println("Log file upload oreignal path at: " + appPath);
        
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : parts) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            filePath = savePath + File.separator + fileName;
            part.write(filePath);
            System.out.println("log file upload success at:" + filePath);
        }
        
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) { 

        	return f.getName();
        }else {
        	return "";
        }
	}
	
	//split all content parts and locate file content
	private static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
