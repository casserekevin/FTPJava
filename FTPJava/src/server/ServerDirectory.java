package server;

import java.io.File;

public class ServerDirectory {

	public String rootFolder(){
		File baseDirectory = new File("server");
		if(!baseDirectory.exists()){
			baseDirectory.mkdirs();
			System.out.println(baseDirectory.getAbsolutePath());
		}
		else if(!baseDirectory.isDirectory()){
			baseDirectory.delete();
			baseDirectory.mkdirs();
			System.out.println(baseDirectory.getAbsolutePath());
		}
		
		return baseDirectory.getName();
	}
}
