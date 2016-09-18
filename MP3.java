package shareHub;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MP3 implements ShareHubFile{

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
