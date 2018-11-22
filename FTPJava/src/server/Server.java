package server;

import server.views.ServerView;

public class Server {
	
	public static void main(String[] args) {
		
		ServerDirectory serverDirectory = new ServerDirectory();
		
		ServerView serverView = new ServerView();
		serverView.setVisible(true);
		
		ServerInstanceManager.create(serverDirectory.rootFolder());			
		
		
	}
}
