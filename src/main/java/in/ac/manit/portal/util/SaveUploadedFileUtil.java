package in.ac.manit.portal.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SaveUploadedFileUtil {
	
	public void saveUploadedFile(CommonsMultipartFile file, String UPLOADED_FOLDER, String fileName){
		
		try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            
            Files.write(path, bytes);

        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
