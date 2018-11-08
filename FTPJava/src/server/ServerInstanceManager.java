package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInstanceManager {
	
	private static boolean stoped;
	
	private static ServerSocket serverSocket;
	private static Socket socket;
	
	private static int numSocket = 0;
	
	public static void create(String rootDirectory) {
		stoped = false;
		
		try {
			int port = 40000;
			serverSocket = new ServerSocket(port);
			System.out.println("DEBUG: Server started at port " + port);
			
			//aplicação que vai rodar de maneira dedicada
			while(!stoped){
				//conectou
				socket = serverSocket.accept();
				
				new ServerInstance(socket, new ObjectInputStream(socket.getInputStream()), new ObjectOutputStream(socket.getOutputStream()), ++numSocket, rootDirectory);
			}
			
		} catch (IOException e) {
			System.err.println("IOException: Erro ao abrir o servidor");
			e.printStackTrace();
		}
	}
	
	public static void closeServerConnection(){
		stoped = true;
		
		if(socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("IOException: Erro ao fechar todo o servidor");
				e.printStackTrace();
			}
		}
		
	}
}
