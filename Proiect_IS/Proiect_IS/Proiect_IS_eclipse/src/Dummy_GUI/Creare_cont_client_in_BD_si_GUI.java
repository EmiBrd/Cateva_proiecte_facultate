package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Actori_pachet.Client;
import Conectare_BD.Connect_DB;
import Magazin_pachet.Magazin;


public class Creare_cont_client_in_BD_si_GUI {

	private JFrame frmCrearecontclient;
	private JTextField nume_client_textField;
	private JTextField prenume_client_textField;
	private JTextField cnp_textField;
	private JTextField stare_civila_textField;
	private JTextField adresa_textField;
	private JTextField e_mail_textField;
	private JTextField coment_textField;
	
	private Magazin magazin;
	private Connect_DB connect_db;
	private Connection con;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Creare_cont_client_in_BD_si_GUI window = new Creare_cont_client_in_BD_si_GUI();
					window.frmCrearecontclient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Creare_cont_client_in_BD_si_GUI() {
		initialize();
		frmCrearecontclient.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearecontclient = new JFrame();
		frmCrearecontclient.setTitle("Creare_cont_client");
		frmCrearecontclient.setBounds(100, 100, 499, 300);
		frmCrearecontclient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrearecontclient.getContentPane().setLayout(null);
		
		JButton creare_cont_btn = new JButton("Creare_cont si add in BD");
		creare_cont_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cand apasam pe butonul "Creare_cont si add in BD", apelam functia
				add_client();
				
			}
		});
		creare_cont_btn.setBounds(294, 71, 179, 55);
		frmCrearecontclient.getContentPane().add(creare_cont_btn);
		
		JLabel lblNewLabel_2 = new JLabel("Nume_client");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 46, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_2);
		
		nume_client_textField = new JTextField();
		nume_client_textField.setBounds(125, 43, 159, 20);
		frmCrearecontclient.getContentPane().add(nume_client_textField);
		nume_client_textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Prenume_client");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 71, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_3);
		
		prenume_client_textField = new JTextField();
		prenume_client_textField.setBounds(125, 68, 159, 20);
		frmCrearecontclient.getContentPane().add(prenume_client_textField);
		prenume_client_textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("CNP");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 96, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_4);
		
		cnp_textField = new JTextField();
		cnp_textField.setBounds(125, 93, 159, 20);
		frmCrearecontclient.getContentPane().add(cnp_textField);
		cnp_textField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Stare_civila");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 121, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_5);
		
		stare_civila_textField = new JTextField();
		stare_civila_textField.setBounds(125, 118, 159, 20);
		frmCrearecontclient.getContentPane().add(stare_civila_textField);
		stare_civila_textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Adresa");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 146, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("E_mail");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 171, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Coment");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(10, 196, 105, 14);
		frmCrearecontclient.getContentPane().add(lblNewLabel_8);
		
		adresa_textField = new JTextField();
		adresa_textField.setBounds(125, 143, 159, 20);
		frmCrearecontclient.getContentPane().add(adresa_textField);
		adresa_textField.setColumns(10);
		
		e_mail_textField = new JTextField();
		e_mail_textField.setBounds(125, 168, 159, 20);
		frmCrearecontclient.getContentPane().add(e_mail_textField);
		e_mail_textField.setColumns(10);
		
		coment_textField = new JTextField();
		coment_textField.setBounds(125, 193, 159, 20);
		frmCrearecontclient.getContentPane().add(coment_textField);
		coment_textField.setColumns(10);
		
		JButton iesire_btn = new JButton("Iesire");
		iesire_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = JOptionPane.showConfirmDialog(iesire_btn, "Sunteti sigur ca vreti sa iesiti?");
                if (a == JOptionPane.YES_OPTION) {       
                	JOptionPane.showMessageDialog(null, "Iesire realizata cu succes");
                	System.exit(0);
                	//Alege_logare_ca_Admin_sau_Client_GUI obj = new Alege_logare_ca_Admin_sau_Client_GUI();                 
                }	
				
			}
		});
		iesire_btn.setBounds(323, 146, 133, 44);
		frmCrearecontclient.getContentPane().add(iesire_btn);
	}
	
	
	// functie prin care clientii isi creeaza cont in BD
	public void add_client(){
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		int i = 0, j = 0, id_client = 0;
		final int id_magazin = 10;  
        String query = " insert into Client (cod, cod_magazin, nume_client, prenume_client, CNP, " +
                "stare_civila, adresa, e_mail, coment)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {      	
        	int ii = 1, jj = 1, gata = 0;
        	for(Client cli : magazin.getClient_list() ) {
        		if(gata == 1 ) {
        			break;
        		}
        		jj = 1;
        		for(Client clj : magazin.getClient_list() ) {
        			int diferenta = jj - ii;
        			if(ii >= jj) {
        				break;
        			}
        			else if( diferenta == 1 ) {
        				//System.out.println("jj = " + jj + ", ii = "+ii);
        				if( (clj.getCod() - cli.getCod() ) > 1 ) {
        					//System.out.println("cod client i = "+cli.getCod() );
        					id_client = cli.getCod() + 1;
        					gata = 1;
        					break;
        				}
        			}
        			jj++;
        		}
        		ii++;
        	}
        		
        	      	
        	for (Client cl : magazin.getClient_list() ) {       		
        		if ( cl.getE_mail().equals(e_mail_textField.getText() ) ||
        				cl.getCNP().equals(cnp_textField.getText() ) ) 
        		{
        			if (cl.getE_mail().equals(e_mail_textField.getText() ) ) {
        				i = 1;
        			}
        			if (cl.getCNP().equals(cnp_textField.getText() ) ) {
        				j = 1;
        			}
        		}
			}
        	
        	if ( (i != 1) && (j != 1) ) {
        		PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt (1, id_client );
                preparedStmt.setInt (2, id_magazin );
                preparedStmt.setString (3, nume_client_textField.getText() );
                preparedStmt.setString (4, prenume_client_textField.getText() );
                preparedStmt.setString (5, cnp_textField.getText() );
                preparedStmt.setString (6, stare_civila_textField.getText() );
                preparedStmt.setString (7, adresa_textField.getText() );
                preparedStmt.setString (8, e_mail_textField.getText() );
                preparedStmt.setString (9, coment_textField.getText() );

                // execute the preparedstatement
                preparedStmt.execute();
                JOptionPane.showMessageDialog(null, "Inregistrarea clientului a fost efectuata cu succes");

        	}
                        
        	else {
        		if ( (i == 1) && (j == 1) ) {
        			JOptionPane.showMessageDialog(null, "Clientul cu adresa de e_mail si acest CNP este deja inregistrat");
        		}
        		else if ( (i == 1) && (j != 1) ) {
        			JOptionPane.showMessageDialog(null, "Clientul cu aceasta adresa de e_mail este deja inregistrat");
        		}
        		if ( (j == 1) && (i != 1) ) {
        			JOptionPane.showMessageDialog(null, "Clientul cu acest CNP este deja inregistrat");
        		}
        	}
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



