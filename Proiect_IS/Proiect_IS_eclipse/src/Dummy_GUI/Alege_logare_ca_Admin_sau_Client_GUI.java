package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Alege_logare_ca_Admin_sau_Client_GUI {

	private JFrame frmLogareCaAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alege_logare_ca_Admin_sau_Client_GUI window = new Alege_logare_ca_Admin_sau_Client_GUI();
					window.frmLogareCaAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Alege_logare_ca_Admin_sau_Client_GUI() {
		initialize();
		frmLogareCaAdmin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogareCaAdmin = new JFrame();
		frmLogareCaAdmin.setTitle("Logare ca Admin sau Client");
		frmLogareCaAdmin.setBounds(100, 100, 450, 300);
		frmLogareCaAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogareCaAdmin.getContentPane().setLayout(null);
		
		JButton logare_admin_btn = new JButton("Logare_Admin");
		logare_admin_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Login_GUI_Admin_bun();
				
			}
		});
		logare_admin_btn.setBounds(43, 82, 140, 77);
		frmLogareCaAdmin.getContentPane().add(logare_admin_btn);
		
		JButton logare_client_btn = new JButton("Logare_client");
		logare_client_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Login_GUI_Client();
				
			}
		});
		logare_client_btn.setBounds(252, 82, 140, 77);
		frmLogareCaAdmin.getContentPane().add(logare_client_btn);
	}

}
