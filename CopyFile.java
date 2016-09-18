package shareHub;

import java.io.File;

public class CopyFile {

	   public static ShareHubFile getFile(File fileToCopy){
	      if(fileToCopy == null){
	         return null;
	      }		
	      if(fileToCopy.getName().contains(".mp3")==true){
	         return new MP3();
	         
	      } else if(fileToCopy.getName().contains(".jpg")==true){
	         return new JPG();
	         
	      } 
	      
	      return null;
	   }
}
