package client.views.connection;

import java.util.ArrayList;

public class ClientConnectionValidator {

	private String ipServer;
	private String port;
	
	public ClientConnectionValidator(String ipServer, String port) {
		this.ipServer = ipServer;
		this.port = port;
	}
	
	public ArrayList<String> validate(){
		ArrayList<String> messages = new ArrayList<>();
		
		if(!validateIpServer()){
			messages.add("IP do servidor inválido");
		}
		if(!validatePort()){
			messages.add("Porta inválida");
		}
		
		return messages;
	}
	
	private boolean validateIpServer(){
		return ipServer.matches("^(((25[0-5])|(2[0-4]\\d)|(1\\d{2})|(\\d{1,2}))\\.){3}(((25[0-5])|(2[0-4]\\d)|(1\\d{2})|(\\d{1,2})))$");
	}
	
	private boolean validatePort(){
		if(port.isEmpty()){
			return false;
		}
		int port = Integer.parseInt(this.port);
		return port >= 0 && port <= 65535;
	}
}
