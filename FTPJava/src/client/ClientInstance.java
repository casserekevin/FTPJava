package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import client.views.ClientOptionMenu;
import messages.MessageContent;
import messages.Reply;
import messages.Request;

public class ClientInstance {
	
	private Socket socket;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private boolean stop;
	private int option;
	
	private String rootDirectory;
	
	private ClientOptionMenu clientOptionMenu;
	
	public ClientInstance(Socket socket, ObjectOutputStream out, ObjectInputStream in, String rootDirectory) {
		this.socket = socket;
		this.out = out;
		this.in = in;
		
		this.rootDirectory = rootDirectory + File.separatorChar;
		
		System.out.println("DEBUG: Client connected...");
		
		this.clientOptionMenu = new ClientOptionMenu();
		do{
			this.clientOptionMenu.setVisible(true);
			
			this.option = clientOptionMenu.getOption();			
			
			switch (option) {
				case ClientOptionMenu.BAIXAR_ARQUIVO:
					downloadFile(clientOptionMenu.getFilename());
					break;
	
				case ClientOptionMenu.CANCELAR:
					sendRequest(Request.CLOSE_INSTANCE_CONNECTION_REQUEST, null, null);
					closeConnection();
					break;
			}
		}
		while(stop != true);
		
		
		
	}
	
	private void downloadingFile(String filename){
		try {
			RandomAccessFile file = new RandomAccessFile(rootDirectory + filename, "rw");
			FileChannel fileChannel = file.getChannel();
			
			Reply reply = null;
			do{
				sendRequest(Request.FILECONTENT_REQUEST, null, null);
				System.out.println("DEBUG: >>>>> Client sended request... FILECONTENT_REQUEST");
				
				reply = receiveReply();
				if(reply.getReplyCode() == Reply.CONTENT_AVAILABLE_REPLY){
					System.out.println("DEBUG: <<<<< Client received reply... CONTENT_AVAILABLE_REPLY");
					ByteBuffer buffer = ByteBuffer.wrap(reply.getReplyContent().getContentBytes());
					fileChannel.write(buffer);
				}
			}
			while(reply.getReplyCode() != Reply.EOF_REPLY);
			System.out.println("DEBUG: <<<<< Client received reply... EOF_REPLY");
			file.close();
			fileChannel.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void downloadFile(String filename){
		sendRequest(Request.DOWNLOAD_FILE_REQUEST, filename, null);
		System.out.println("DEBUG: >>>>> Client sended request... DOWNLOAD_FILE_REQUEST");
		Reply reply = receiveReply();
		System.out.println("DEBUG: <<<<< Client received reply... FILE_EXISTS_REPLY");
		
		if(reply.getReplyCode() == Reply.FILE_EXISTS_REPLY){
			System.out.println("DEBUG: Starting download...");
			
			downloadingFile(filename);
		}
		else if(reply.getReplyCode() == Reply.FILE_NOT_FOUND_REPLY){
			System.out.println("ERRO - Arquivo não encontrado no servidor");
		}
			
	}
	
	private void sendRequest(int requestCode, String contentString, byte[] contentBytes){
		Request request = new Request();
		
		MessageContent messageContent = new MessageContent();
		if(contentString != null){
			messageContent.setContentString(contentString);			
		}
		if(contentBytes != null){
			messageContent.setContentBytes(contentBytes);			
		}
		
		request.setRequestCode(requestCode);
		request.setRequestContent(messageContent);
		
		try {
			out.writeObject(request);
//			System.out.println("DEBUG: Client sended request...");
		} catch (IOException e) {
			System.err.println("IOException: Erro ao enviar requisição");
			e.printStackTrace();
		}
	}
	
	private Reply receiveReply(){
		Reply reply = null;
		try {
			reply = (Reply)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: Erro ao receber resposta");
			e.printStackTrace();
		}
		
//		System.out.println("DEBUG: Client received reply...");
		return reply;
	}
	
	private void closeConnection(){
		stop = true;
		
		try {
			in.close();
			out.close();
			socket.close();
			System.out.println("DEBUG: Client closed connection...");
		} catch (IOException e) {
			System.err.println("IOException: Erro ao fechar o cliente");
			e.printStackTrace();
		}
	}
}
