package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainTests {

	public static void main(String[] args) {
		
		try {
			RandomAccessFile file = new RandomAccessFile("file1.txt", "rw");
			FileChannel fileChannel = file.getChannel();
			RandomAccessFile outFile = new RandomAccessFile("file1Out.txt", "rw");	
			FileChannel fileChannelOut = outFile.getChannel();
			
			
			ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
			int bytesRead = 0;
			do{
				bytesRead = fileChannel.read(buffer);
				System.out.println(bytesRead);
				
				buffer.flip();
				fileChannelOut.write(buffer);
				buffer.flip();
			}
			while(bytesRead != -1);
			System.out.println(bytesRead);
			System.out.println(file.length());
			
			file.close();
			fileChannel.close();
			outFile.close();
			fileChannelOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
