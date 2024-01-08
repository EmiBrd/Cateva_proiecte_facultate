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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class Client_actualizare_date_GUI {

	private JFrame frmActualizareClient;
	private JTextField nume_client_vechi_textField;
	private JTextField prenume_client_vechi_textField;
	private JTextField cnp_client_vechi_textField;
	private JTextField stare_civila_client_vechi_textField;
	private JTextField adresa_client_vechi_textField;
	private JTextField email_client_vechi_textField;
	private JTextField comentariu_client_nou_textField;
	private JTextField email_client_nou_textField;
	private JTextField adresa_client_nou_textField;
	private JTextField stare_civila_client_nou_textField;
	private JTextField prenume_client_nou_textField;
	private JTextField nume_client_nou_textField;
	
	
	Magazin magazin;
	private Connect_DB connect_db;
	private Connection con;
	int e_bine = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_actualizare_date_GUI window = new Client_actualizare_date_GUI();
					window.frmActualizareClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_actualizare_date_GUI() {
		initialize();
		frmActualizareClient.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmActualizareClient = new JFrame();
		frmActualizareClient.setTitle("Actualizare client");
		frmActualizareClient.setBounds(100, 100, 767, 460);
		frmActualizareClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActualizareClient.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date_actuale");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(85, 18, 112, 21);
		frmActualizareClient.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume_client");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 53, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenume_client");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 79, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CNP");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 104, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Stare_civila");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 129, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Adresa");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 154, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("E_mail");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 179, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_6);
		
		nume_client_vechi_textField = new JTextField();
		nume_client_vechi_textField.setBounds(130, 47, 185, 20);
		frmActualizareClient.getContentPane().add(nume_client_vechi_textField);
		nume_client_vechi_textField.setColumns(10);
		
		prenume_client_vechi_textField = new JTextField();
		prenume_client_vechi_textField.setBounds(130, 73, 185, 20);
		frmActualizareClient.getContentPane().add(prenume_client_vechi_textField);
		prenume_client_vechi_textField.setColumns(10);
		
		cnp_client_vechi_textField = new JTextField();
		cnp_client_vechi_textField.setBounds(130, 98, 185, 20);
		frmActualizareClient.getContentPane().add(cnp_client_vechi_textField);
		cnp_client_vechi_textField.setColumns(10);
		
		stare_civila_client_vechi_textField = new JTextField();
		stare_civila_client_vechi_textField.setBounds(130, 123, 185, 20);
		frmActualizareClient.getContentPane().add(stare_civila_client_vechi_textField);
		stare_civila_client_vechi_textField.setColumns(10);
		
		adresa_client_vechi_textField = new JTextField();
		adresa_client_vechi_textField.setBounds(130, 148, 185, 20);
		frmActualizareClient.getContentPane().add(adresa_client_vechi_textField);
		adresa_client_vechi_textField.setColumns(10);
		
		email_client_vechi_textField = new JTextField();
		email_client_vechi_textField.setBounds(130, 173, 185, 20);
		frmActualizareClient.getContentPane().add(email_client_vechi_textField);
		email_client_vechi_textField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Date_actualizate");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(442, 18, 141, 21);
		frmActualizareClient.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Nume_nou");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(372, 50, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Prenume_nou");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(372, 76, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_12 = new JLabel("Stare_civila_noua");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(372, 101, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_11 = new JLabel("Adresa_noua");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(372, 126, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_13 = new JLabel("E_mail_nou");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setBounds(372, 151, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Comentariu_nou");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setBounds(372, 176, 110, 14);
		frmActualizareClient.getContentPane().add(lblNewLabel_14);
		
		comentariu_client_nou_textField = new JTextField();
		comentariu_client_nou_textField.setBounds(489, 173, 211, 20);
		frmActualizareClient.getContentPane().add(comentariu_client_nou_textField);
		comentariu_client_nou_textField.setColumns(10);
		
		email_client_nou_textField = new JTextField();
		email_client_nou_textField.setBounds(489, 148, 211, 20);
		frmActualizareClient.getContentPane().add(email_client_nou_textField);
		email_client_nou_textField.setColumns(10);
		
		adresa_client_nou_textField = new JTextField();
		adresa_client_nou_textField.setBounds(489, 123, 211, 20);
		frmActualizareClient.getContentPane().add(adresa_client_nou_textField);
		adresa_client_nou_textField.setColumns(10);
		
		stare_civila_client_nou_textField = new JTextField();
		stare_civila_client_nou_textField.setBounds(489, 98, 211, 20);
		frmActualizareClient.getContentPane().add(stare_civila_client_nou_textField);
		stare_civila_client_nou_textField.setColumns(10);
		
		prenume_client_nou_textField = new JTextField();
		prenume_client_nou_textField.setBounds(489, 73, 211, 20);
		frmActualizareClient.getContentPane().add(prenume_client_nou_textField);
		prenume_client_nou_textField.setColumns(10);
		
		nume_client_nou_textField = new JTextField();
		nume_client_nou_textField.setBounds(489, 47, 211, 20);
		frmActualizareClient.getContentPane().add(nume_client_nou_textField);
		nume_client_nou_textField.setColumns(10);
		
		JButton verif_date_actuale_btn = new JButton("Verificare_date_actuale");
		verif_date_actuale_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e_bine == 0) {
					e_bine = 1;
					verificare_date_client();
				}
				else {
					e_bine = 0;
					JOptionPane.showMessageDialog(null, "Trebuia sa verificati corectitudinea datelor, iar dupa aceea le puteati actualiza.");
				}
				
			}
		});
		verif_date_actuale_btn.setBounds(130, 239, 185, 60);
		frmActualizareClient.getContentPane().add(verif_date_actuale_btn);
		
		JButton actualizare_date_btn = new JButton("Actualizare_date");
		actualizare_date_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e_bine == 1) {
					e_bine = 0;
					update_client_fara_id_uri();
				}
				else {
					e_bine = 0;
					JOptionPane.showMessageDialog(null, "Trebuie sa verificati corectitudinea datelor. Dupa aceea le puteti actualiza.");
				}
				
			}
		});
		actualizare_date_btn.setBounds(489, 239, 185, 60);
		frmActualizareClient.getContentPane().add(actualizare_date_btn);
		
		JButton delogare_btn = new JButton("Delogare");
		delogare_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// daca apasam de butonul "Delogare", vom fi intrebati daca vrem sa ne delogam
				// daca raspunsul este da, vom fi delogati, altfel ramanem logati
				int a = JOptionPane.showConfirmDialog(delogare_btn, "Sunteti sigur ca vreti sa iesiti?");
                if (a == JOptionPane.YES_OPTION) {       
                	JOptionPane.showMessageDialog(null, "Delogare client realizata cu succes");
                	System.exit(0);
                	//Alege_logare_ca_Admin_sau_Client_GUI obj = new Alege_logare_ca_Admin_sau_Client_GUI();                 
                }	
				
			}
		});
		delogare_btn.setBounds(494, 328, 180, 60);
		frmActualizareClient.getContentPane().add(delogare_btn);
	}
	
	
	// functie care verifica daca datele sunt introduse corect
	void verificare_date_client() {
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		int i = 0;
		for (Client cl : magazin.getClient_list() ) {
			if (cl.getNume_client().equals(nume_client_vechi_textField.getText() ) && 
					cl.getPrenume_client().equals(prenume_client_vechi_textField.getText() ) &&
					cl.getCNP().equals(cnp_client_vechi_textField.getText() ) &&
					cl.getStare_civila().equals(stare_civila_client_vechi_textField.getText() ) &&
					cl.getAdresa().equals(adresa_client_vechi_textField.getText() ) &&
					cl.getE_mail().equals(email_client_vechi_textField.getText() ) )
			{
				i++;
				JOptionPane.showMessageDialog(null, "Autentificare cu succes. Va rugam sa introduceti noile date.");
				
			}
		}
		if(i <= 0) {
			JOptionPane.showMessageDialog(null, "Autentificare nereusita. Datele sunt introduse incorect." + 
											" Introduceti datele din nou.");
		}
		
	}
	
	
	// functie pentru actualizare clienti in BD fara id_client si id_magazin
	public void update_client_fara_id_uri(){
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
        // create the java mysql update preparedstatement
        String query = "update Client set nume_client = ?, prenume_client = ?," +
                " stare_civila = ?, adresa = ?, e_mail = ?, coment = ? where CNP = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
           
            preparedStmt.setString (1, nume_client_nou_textField.getText() );
            preparedStmt.setString (2, prenume_client_nou_textField.getText() );
            preparedStmt.setString (3, stare_civila_client_nou_textField.getText() );
            preparedStmt.setString (4, adresa_client_nou_textField.getText() );
            preparedStmt.setString (5, email_client_nou_textField.getText() );
            preparedStmt.setString (6, comentariu_client_nou_textField.getText() );

            preparedStmt.setString (7, cnp_client_vechi_textField.getText() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("A actualizat clientul in BD");
            JOptionPane.showMessageDialog(null, "Actualizarea clientului in BD a fost efectuata cu succes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	
	
}
