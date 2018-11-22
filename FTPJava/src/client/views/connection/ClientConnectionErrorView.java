package client.views.connection;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClientConnectionErrorView extends JDialog {

	//Layout
	private GridBagConstraints gridBagConstraints = new GridBagConstraints();
	
	//Lógica da Janela
	private ArrayList<String> messages;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ArrayList<String> messages = new ArrayList<>();
			messages.add("Teste de exemplo 1");
			messages.add("Teste de exemplo 2");
			ClientConnectionErrorView dialog = new ClientConnectionErrorView(messages);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientConnectionErrorView(ArrayList<String> messages) {
		this.messages = messages;
	
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(new GridBagLayout());
		
		addLabels();
		addButtom();
		
		pack();
		setLocationRelativeTo(null);
		
	}
	
	private void addLabels(){
		for(int i = 0; i < messages.size(); i++){
			JLabel label = new JLabel(messages.get(i));
			label.setForeground(new Color(255, 0, 0));
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = i;
			gridBagConstraints.insets = new Insets(10, 50, 10, 50);
			getContentPane().add(label, gridBagConstraints);
		}
	}
	
	private void addButtom(){
		JButton buttom = new JButton("OK");
		buttom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = messages.size();
		gridBagConstraints.insets = new Insets(10, 50, 10, 50);
		getContentPane().add(buttom, gridBagConstraints);
	}

}
