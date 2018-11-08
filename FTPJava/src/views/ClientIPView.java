package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientIPView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtf_ip;
	private JTextField txtf_port;
	
	private String ipServer;
	private int port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientIPView dialog = new ClientIPView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientIPView() {
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		
		JPanel panel_inferior = new JPanel();
		panel_inferior.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_inferior.setBounds(10, 87, 414, 33);
		contentPanel.add(panel_inferior);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIpServer(txtf_ip.getText());
				setPort(Integer.parseInt(txtf_port.getText()));
				dispose();
			}
		});
		panel_inferior.add(btnOk);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setBounds(10, 11, 414, 70);
		contentPanel.add(panel_superior);
		
		JLabel lbl_ip = new JLabel("IP do servidor:");
		
		txtf_ip = new JTextField();
		txtf_ip.setColumns(15);
		
		JLabel lbl_port = new JLabel("Porta:");
		
		txtf_port = new JTextField();
		txtf_port.setColumns(5);
		GroupLayout gl_panel_superior = new GroupLayout(panel_superior);
		gl_panel_superior.setHorizontalGroup(
			gl_panel_superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_ip)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtf_ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lbl_port)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtf_port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(105, Short.MAX_VALUE))
		);
		gl_panel_superior.setVerticalGroup(
			gl_panel_superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_superior.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_ip)
						.addComponent(txtf_ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_port)
						.addComponent(txtf_port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		panel_superior.setLayout(gl_panel_superior);
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
