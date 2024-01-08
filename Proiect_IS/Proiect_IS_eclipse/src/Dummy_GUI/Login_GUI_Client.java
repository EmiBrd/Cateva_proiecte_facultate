package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Actori_pachet.Client;
import Conectare_BD.Connect_DB;
import Magazin_pachet.Magazin;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login_GUI_Client {

	private JFrame frmLogareclient;
	private JTextField username_textField;
	private JTextField password_textField;
	
	Magazin magazin;
	
	
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI_Client window = new Login_GUI_Client();
					window.frmLogareclient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_GUI_Client() {
		initialize();
		frmLogareclient.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connect_DB connect_db = new Connect_DB();
        magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		frmLogareclient = new JFrame();
		frmLogareclient.setTitle("LOGARE_CLIENT");
		frmLogareclient.setBounds(100, 100, 450, 300);
		frmLogareclient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogareclient.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGARE_CLIENT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 23, 200, 43);
		frmLogareclient.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume_utilizator");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(32, 83, 129, 22);
		frmLogareclient.getContentPane().add(lblNewLabel_1);
		
		username_textField = new JTextField();
		username_textField.setBounds(171, 85, 209, 22);
		frmLogareclient.getContentPane().add(username_textField);
		username_textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Parola");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(32, 125, 128, 20);
		frmLogareclient.getContentPane().add(lblNewLabel_2);
		
		password_textField = new JTextField();
		password_textField.setBounds(170, 126, 210, 22);
		frmLogareclient.getContentPane().add(password_textField);
		password_textField.setColumns(10);
		
		JButton autentificare_btn = new JButton("Autentificare");
		autentificare_btn.addActionListener(new ActionListener() {
			//private Magazin magazin1;

			public void actionPerformed(ActionEvent e) {
				
				// daca datele introduse la numele utilizatoruli si parola sunt corecte
				// ne-am autentificat cu succes
				int i = 0;
				for (Client cl : magazin.getClient_list() ) {
					if (cl.getE_mail().equals(username_textField.getText() ) && 
							cl.getCNP().equals(password_textField.getText() ) ) 
					{
						i++;
						JOptionPane.showMessageDialog(null, "Autentificare cu succes");
						
						new Client_alege_editare_date_sau_cumparare_GUI();
						// aici trebuie sa pun functia care arata actiunea pe care vreau sa o execut
						// adica aia care ma lasa sa cumpar produsele dorite
					}
				}
				// nu ne-am putut autentifica
				if(i <= 0) {
					JOptionPane.showMessageDialog(null, "Autentificare nereusita. User sau parola inexistente." + 
													"Introduceti datele din nou");
				}
				
				
			}
		});
		autentificare_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		autentificare_btn.setBounds(78, 189, 129, 33);
		frmLogareclient.getContentPane().add(autentificare_btn);
		
		JButton creare_cont_btn = new JButton("Creare_cont");
		creare_cont_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Creare_cont_client_in_BD_si_GUI();
				
			}
		});
		creare_cont_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		creare_cont_btn.setBounds(235, 189, 118, 33);
		frmLogareclient.getContentPane().add(creare_cont_btn);
	}
	

	
	
	
}



