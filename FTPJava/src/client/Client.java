package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import views.ClientIPView;

public class Client {
	
	private static Socket socket;
	
	public static void main(String[] args) {
		
		ClientIPView clientIPView = new ClientIPView();
		clientIPView.setVisible(true);
		
		ClientDirectory clientDirectory = new ClientDirectory();
		
		try {
			Client.socket = new Socket(clientIPView.getIpServer(), clientIPView.getPort());
			new ClientInstance(Client.socket, new ObjectOutputStream(Client.socket.getOutputStream()), new ObjectInputStream(Client.socket.getInputStream()), clientDirectory.rootFolder());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
