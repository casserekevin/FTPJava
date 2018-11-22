package client.views.connection;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.views.connection.plainsDocuments.ServerIPPlainDocument;
import client.views.connection.plainsDocuments.ServerPortPlainDocument;

@SuppressWarnings("serial")
public class ClientConnectionView extends JDialog {
	
	private JPanel contentPanel;
	
	//Components
	private JTextField txtf_ip, txtf_port;
	private JLabel lbl_ip, lbl_port;
	private JButton btn_ok;
	
	//Layout
	private GridBagConstraints gridBagConstraints = new GridBagConstraints();
	
	//Logica da janela
	private String ipServer;
	private int port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientConnectionView dialog = new ClientConnectionView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientConnectionView() {
		setBounds(100, 100, 450, 150);
		setModal(true);
		setLocationRelativeTo(null);
		contentPanel = new JPanel(); 
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		contentPanel.setLayout(new GridBagLayout());
		
		lbl_ip = new JLabel("IP do servidor:");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		contentPanel.add(lbl_ip, gridBagConstraints);
		
		txtf_ip = new JTextField(15);
		txtf_ip.setDocument(new ServerIPPlainDocument());
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(txtf_ip, gridBagConstraints);
		
		
		lbl_port = new JLabel("Porta:");
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		contentPanel.add(lbl_port, gridBagConstraints);
		
		
		txtf_port = new JTextField(5);
		txtf_port.setDocument(new ServerPortPlainDocument());
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		contentPanel.add(txtf_port, gridBagConstraints);
		
		
		btn_ok = new JButton("OK");
//		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.insets = new Insets(30, 25, 0, 0);
		contentPanel.add(btn_ok, gridBagConstraints);
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientConnectionValidator validator = new ClientConnectionValidator(txtf_ip.getText(), txtf_port.getText());
				ArrayList<String> messages = validator.validate();
				if(!messages.isEmpty()){
					ClientConnectionErrorView errorView = new ClientConnectionErrorView(messages);
					errorView.setVisible(true);
				}
				else{
					setIpServer(txtf_ip.getText());
					setPort(Integer.parseInt(txtf_port.getText()));
					dispose();					
				}
			}
		});
	}

	public String getIpServer() {
		return ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
}
