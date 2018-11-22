package server.views;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import server.ServerInstanceManager;

@SuppressWarnings("serial")
public class ServerView extends JFrame {

	// Layout
	private GridBagConstraints gridBagConstraints = new GridBagConstraints();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerView frame = new ServerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120};
		gridBagLayout.columnWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
		
		addButtom();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void addButtom(){
		JButton buttom = new JButton("Desligar");
		buttom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerInstanceManager.closeServerConnection();
				System.exit(0);
			}
		});
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.insets = new Insets(25, 25, 25, 25);
		getContentPane().add(buttom, gridBagConstraints);
	}

}
