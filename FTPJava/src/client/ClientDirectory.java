package client;

import java.io.File;

public class ClientDirectory {
	
	public String rootFolder(){
		File baseDirectory = new File("client");
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
