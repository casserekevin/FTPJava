package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientOptionMenu extends JDialog {

	private JPanel contentPane;
	
	private JLabel lbl_chooseOption;
	private JButton btn_downloadFile;
	private JButton btn_cancel;
	
	
	private int option;
	public static final int BAIXAR_ARQUIVO = 4; 
	public static final int CANCELAR = 0;
	
	private String filename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientOptionMenu frame = new ClientOptionMenu();
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
	public ClientOptionMenu() {
		setTitle("Op\u00E7\u00F5es");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		
		lbl_chooseOption = new JLabel("Escolha uma op\u00E7\u00E3o:");
		lbl_chooseOption.setBounds(146, 11, 142, 27);
		lbl_chooseOption.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lbl_chooseOption);
		
		btn_downloadFile = new JButton("4 - Baixar Arquivo");
		btn_downloadFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_downloadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownloadFileView downloadFileView = new DownloadFileView();
				downloadFileView.setVisible(true);
				
				setFilename(downloadFileView.getFilename());
				setOption(BAIXAR_ARQUIVO);
				dispose();
			}
		});
		btn_downloadFile.setBounds(10, 49, 414, 23);
		contentPane.add(btn_downloadFile);
		
		btn_cancel = new JButton("Cancelar");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOption(CANCELAR);
				dispose();
			}
		});
		btn_cancel.setBounds(179, 227, 89, 23);
		contentPane.add(btn_cancel);
		
	}
	
	public int getOption() {
		return option;
	}
	
	private void setOption(int option) {
		this.option = option;
	}

	public String getFilename() {
		return filename;
	}

	private void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
