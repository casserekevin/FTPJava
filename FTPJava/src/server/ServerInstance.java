package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import messages.MessageContent;
import messages.Reply;
import messages.Request;

public class ServerInstance implements Runnable{

	private Socket socket;

	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private int numSocket;
	
	private String rootDirectory;
	
	private boolean stop;

	public ServerInstance(Socket socket, ObjectInputStream in, ObjectOutputStream out, int numSocket, String rootDirectory){
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.numSocket = numSocket;
		this.rootDirectory = rootDirectory + File.separatorChar;

		new Thread(this).start();
	}

	@Override
	public void run() {
		
		System.out.println("DEBUG: Server Instance #" + numSocket + " connected...");
		

		Request request = null; 
		do {
			request = receiveRequest();
			switch (request.getRequestCode()) {
				case Request.DOWNLOAD_FILE_REQUEST:
					System.out.println("DEBUG: <<<<< Server Instance #" + numSocket + " received request... DOWNLOAD_FILE_REQUEST");
					uploadFile(request.getRequestContent().getContentString());
					break;
			
				case Request.CLOSE_INSTANCE_CONNECTION_REQUEST:
					closeInstanceConnection();
					break;
			}
			
		} while (stop != true);
	}
	
	private boolean fileExists(String filename){
		File file = new File(filename);
		System.out.println(file.getAbsolutePath());
		if(file.exists()){
			return true;
		}
		
		return false;
	}
	
	private void uploadingFile(String filename){
		try {
			RandomAccessFile file = new RandomAccessFile(rootDirectory + filename, "rw");
			FileChannel fileChannel = file.getChannel();
			if(file.length() < 1024 * 1024 * 1024){
				ByteBuffer buffer = ByteBuffer.allocate((int)file.length()); //8Kib				
			
				int bytesReaded = 0;
				Request request = null;
				do{
					request = receiveRequest();
					if(request.getRequestCode() == Request.FILECONTENT_REQUEST){
						System.out.println("DEBUG: <<<<< Server Instance #" + numSocket + " received request... FILECONTENT_REQUEST");
						bytesReaded = fileChannel.read(buffer);
						buffer.flip();
						if(bytesReaded != -1){
							sendReply(Reply.CONTENT_AVAILABLE_REPLY, null, buffer.array());
							System.out.println("DEBUG: >>>>> Server Instance #" + numSocket + " sended request... CONTENT_AVAILABLE_REPLY");
						}
						else{
							sendReply(Reply.EOF_REPLY, null, null);
							System.out.println("DEBUG: >>>>> Server Instance #" + numSocket + " sended request... EOF_REPLY");
						}
					}
				}while(bytesReaded != -1);
				file.close();
				fileChannel.close();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void uploadFile(String filename){
		if(fileExists(rootDirectory + filename)){
			sendReply(Reply.FILE_EXISTS_REPLY, null, null);
			System.out.println("DEBUG: >>>>> Server Instance #" + numSocket + " sended reply... FILE_EXISTS_REPLY");
			System.out.println("DEBUG: Starting upload...");
			
			uploadingFile(filename);
		}
		else{
			sendReply(Reply.FILE_NOT_FOUND_REPLY, null, null);
		}
	}
	
	private void sendReply(int replyCode, String contentString, byte[] contentBytes){
		Reply reply = new Reply();
		
		MessageContent messageContent = new MessageContent();
		if(contentString != null){
			messageContent.setContentString(contentString);			
		}
		if(contentBytes != null){
			messageContent.setContentBytes(contentBytes);			
		}
		
		reply.setReplyCode(replyCode);
		reply.setReplyContent(messageContent);
		
		try {
			out.writeObject(reply);
//			System.out.println("DEBUG: Server Instance #" + numSocket + " sended reply...");
		} catch (IOException e) {
			System.err.println("IOException: Erro ao enviar requisição");
			e.printStackTrace();
		}
	}
	
	private Request receiveRequest(){
		Request request = null;
		try {
			request = (Request) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: Erro ao receber requisição");
			e.printStackTrace();
		}
//		System.out.println("DEBUG: Server Instance #" + numSocket + " received request...");
		return request;
	}
	
	private void closeInstanceConnection(){
		stop = true;
		
		try {
			in.close();
			out.close();
			socket.close();
			System.out.println("DEBUG: Server Instance #" + numSocket + " closed connection...");
		} catch (IOException e) {
			System.err.println("IOException: Erro ao fechar instância do servidor");
			e.printStackTrace();
		}
	}
	

}
