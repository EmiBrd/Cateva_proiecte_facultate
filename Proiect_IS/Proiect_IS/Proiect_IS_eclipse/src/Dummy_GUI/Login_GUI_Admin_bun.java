package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Actori_pachet.Administrator;
import Actori_pachet.Client;
import Conectare_BD.Connect_DB;
import Magazin_pachet.Magazin;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login_GUI_Admin_bun {

	private JFrame frmLogare;
	private JTextField username_textField;
	private JTextField password_textField;
	private Magazin magazin;
    Administrator admin;
    Client client;
    //private Magazin magazin1;
    //private Connect_DB connect_db1;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login_GUI_Admin_bun window = new Login_GUI_Admin_bun();
					window.frmLogare.setVisible(true);
					
					Connect_DB connect_db1 = new Connect_DB();
					Magazin magazin1 = new Magazin(connect_db1.get_conexiune("root", "0000") );
					System.out.println(magazin1.getAdministrator().getE_mail() );
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_GUI_Admin_bun() {
		initialize();
		frmLogare.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Connect_DB connect_db = new Connect_DB();
        magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		
		frmLogare = new JFrame();
		frmLogare.setTitle("LOGARE_ADMINISTRATOR");
		frmLogare.setBounds(100, 100, 450, 300);
		frmLogare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogare.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGARE_ADMIN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(92, 30, 226, 28);
		frmLogare.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume_utilizator");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(31, 89, 119, 28);
		frmLogare.getContentPane().add(lblNewLabel_1);
		
		username_textField = new JTextField();
		username_textField.setBounds(160, 92, 216, 26);
		frmLogare.getContentPane().add(username_textField);
		username_textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Parola");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(31, 132, 119, 26);
		frmLogare.getContentPane().add(lblNewLabel_2);
		
		password_textField = new JTextField();
		password_textField.setBounds(160, 133, 216, 26);
		frmLogare.getContentPane().add(password_textField);
		password_textField.setColumns(10);
		
		JButton autentificare_btn = new JButton("Autentificare");
		autentificare_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (magazin.getAdministrator().getE_mail().equals(username_textField.getText() ) && 
						magazin.getAdministrator().getParola().equals(password_textField.getText() ) ) 
				{
					JOptionPane.showMessageDialog(null, "Autentificare cu succes");
					// aici trebuie sa pun functia care arata actiunea pe care vreau sa o execut
					// adica aia care ma lasa sa aleg intre manipulare clienti sau produse
					new Alegere_produs_sau_client_GUI();
				}
				else {
					JOptionPane.showMessageDialog(null, "Autentificare nereusita. Introduceti datele din nou");
				}
			}
		});
		autentificare_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		autentificare_btn.setBounds(138, 188, 145, 41);
		frmLogare.getContentPane().add(autentificare_btn);
	}
	
	
	
	
}





