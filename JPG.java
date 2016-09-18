package shareHub;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class JPG implements ShareHubFile{
	@Override
	   public boolean copy(File file,File file2) {
		
		try {
			Files.copy(file.toPath(), file2.toPath());
			return true;
		} catch (IOException e) {			
			return false;
		}
	
	}
}
