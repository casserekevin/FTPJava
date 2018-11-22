package client.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ClientFileNameView extends JDialog {
	private JLabel lbl_fileName;
	private JTextField txtf_fileName;
	private JButton btn_ok;
	
	private String filename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientFileNameView dialog = new ClientFileNameView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientFileNameView() {
		setTitle("Nome do arquivo");
		setBounds(100, 100, 300, 140);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);
		
		lbl_fileName = new JLabel("Nome do Arquivo:");
		lbl_fileName.setBounds(10, 21, 99, 20);
		getContentPane().add(lbl_fileName);
		
		txtf_fileName = new JTextField();
		txtf_fileName.setBounds(119, 21, 155, 20);
		txtf_fileName.setColumns(173);
		getContentPane().add(txtf_fileName);
		
		btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtf_fileName.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Campo obrigatório", "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
				}
				else{
					setFilename(txtf_fileName.getText());
					dispose();
				}
			}
		});
		btn_ok.setBounds(199, 67, 75, 23);
		getContentPane().add(btn_ok);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
