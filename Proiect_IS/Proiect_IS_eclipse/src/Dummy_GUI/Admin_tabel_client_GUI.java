package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Actori_pachet.Client;
import Conectare_BD.Connect_DB;
import Magazin_pachet.Magazin;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.SwingConstants;


public class Admin_tabel_client_GUI {

	private JFrame frmAdminguimanipulareclienti;
	private JTextField cod_client_textField;
	private JTextField cod_magazin_textField;
	private JTextField nume_client_textField;
	private JTextField prenume_client_textField;
	private JTextField cnp_textField;
	private JTextField stare_civila_textField;
	private JTextField adresa_textField;
	private JTextField email_textField;
	private JTextField coment_textField;
	private JTable table;
	
	
	private DefaultTableModel model;
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
					Admin_tabel_client_GUI window = new Admin_tabel_client_GUI();
					window.frmAdminguimanipulareclienti.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin_tabel_client_GUI() {
		initialize();
		frmAdminguimanipulareclienti.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connect_DB connect_db = new Connect_DB();
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		
		frmAdminguimanipulareclienti = new JFrame();
		frmAdminguimanipulareclienti.setTitle("ADMIN_GUI_MANIPULARE_CLIENTI");
		frmAdminguimanipulareclienti.setBounds(100, 100, 1260, 547);
		frmAdminguimanipulareclienti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminguimanipulareclienti.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1244, 497);
		frmAdminguimanipulareclienti.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cod_client");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 91, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cod_magazin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 35, 91, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nume_client");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 58, 91, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Prenume_client");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 80, 91, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CNP");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 102, 91, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Stare_civila");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 124, 91, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Adresa");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 146, 91, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("E_mail");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 171, 91, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Coment");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(10, 196, 91, 14);
		panel.add(lblNewLabel_8);
		
		cod_client_textField = new JTextField();
		cod_client_textField.setBounds(108, 11, 149, 20);
		panel.add(cod_client_textField);
		cod_client_textField.setColumns(10);
		
		cod_magazin_textField = new JTextField();
		cod_magazin_textField.setBounds(108, 32, 149, 20);
		panel.add(cod_magazin_textField);
		cod_magazin_textField.setColumns(10);
		
		nume_client_textField = new JTextField();
		nume_client_textField.setBounds(108, 55, 149, 20);
		panel.add(nume_client_textField);
		nume_client_textField.setColumns(10);
		
		prenume_client_textField = new JTextField();
		prenume_client_textField.setBounds(108, 77, 149, 20);
		panel.add(prenume_client_textField);
		prenume_client_textField.setColumns(10);
		
		cnp_textField = new JTextField();
		cnp_textField.setBounds(108, 99, 149, 20);
		panel.add(cnp_textField);
		cnp_textField.setColumns(10);
		
		stare_civila_textField = new JTextField();
		stare_civila_textField.setBounds(108, 121, 149, 20);
		panel.add(stare_civila_textField);
		stare_civila_textField.setColumns(10);
		
		adresa_textField = new JTextField();
		adresa_textField.setBounds(108, 143, 149, 20);
		panel.add(adresa_textField);
		adresa_textField.setColumns(10);
		
		email_textField = new JTextField();
		email_textField.setBounds(108, 168, 149, 20);
		panel.add(email_textField);
		email_textField.setColumns(10);
		
		coment_textField = new JTextField();
		coment_textField.setBounds(108, 193, 149, 20);
		panel.add(coment_textField);
		coment_textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 0, 967, 486);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				cod_client_textField.setText(model.getValueAt(i, 0). toString() );
				cod_magazin_textField.setText(model.getValueAt(i, 1). toString() );
				nume_client_textField.setText(model.getValueAt(i, 2). toString() );
				prenume_client_textField.setText(model.getValueAt(i, 3). toString() );
				cnp_textField.setText(model.getValueAt(i, 4). toString() );
				stare_civila_textField.setText(model.getValueAt(i, 5). toString() );
				adresa_textField.setText(model.getValueAt(i, 6). toString() );
				email_textField.setText(model.getValueAt(i, 7). toString() );
				coment_textField.setText(model.getValueAt(i, 8). toString() );
			}
		});
		model = new DefaultTableModel();
		Object[] column = {"cod",  "cod_magazin", "nume_client", "prenume_client", "CNP",
                "stare_civila", "adresa", "e_mail", "coment"};
		Object[] row = new Object[9];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
		for(Client cl : magazin.getClient_list() )
        {
			
            //data[i] = new Object[]{
            //        cl.getCod(), cl.getCod_magazin(), cl.getNume_client(), cl.getPrenume_client(), cl.getCNP(),
            //        cl.getStare_civila(), cl.getAdresa(), cl.getE_mail(), cl.getComent()
            //};
            
            model.addRow(new Object[]{
            cl.getCod(), cl.getCod_magazin(), cl.getNume_client(), cl.getPrenume_client(), cl.getCNP(),
                    cl.getStare_civila(), cl.getAdresa(), cl.getE_mail(), cl.getComent() });
        }
		
		
		
		JButton add_client_btn = new JButton("Adauga_client");
		add_client_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// daca nu sunt completate toate campurile, afisam un mesaj de eroare
				if(cod_client_textField.getText().equals("") || cod_magazin_textField.getText().equals("") ||
						nume_client_textField.getText().equals("") || prenume_client_textField.getText().equals("") ||
						cnp_textField.getText().equals("") || stare_civila_textField.getText().equals("") ||
						adresa_textField.getText().equals("") || email_textField.getText().equals("") ) {
						//|| coment_textField.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "Adaugare nereusita. Completati toate campurile (in afara de comentarii)");
				}
				// altfel adaugam clientul
				else {
					row[0] = cod_client_textField.getText();
					row[1] = cod_magazin_textField.getText();
					row[2] = nume_client_textField.getText();
					row[3] = prenume_client_textField.getText();
					row[4] = cnp_textField.getText();
					row[5] = stare_civila_textField.getText();
					row[6] = adresa_textField.getText();
					row[7] = email_textField.getText();
					row[8] = coment_textField.getText();
					model.addRow(row);
					add_client();
					// dupa ce adaugam clientul, facem campurile de scriere text libere
					cod_client_textField.setText("");
					cod_magazin_textField.setText("");
					nume_client_textField.setText("");
					prenume_client_textField.setText("");
					cnp_textField.setText("");
					stare_civila_textField.setText("");
					adresa_textField.setText("");
					email_textField.setText("");
					coment_textField.setText("");
					JOptionPane.showMessageDialog(null, "Salvare cu succes");
				}
				
			}
		});
		add_client_btn.setBounds(10, 250, 159, 23);
		panel.add(add_client_btn);
		
		JButton update_client_btn = new JButton("Actualizare_client");
		update_client_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// daca am selectat un rand, se actualizeaza clientul
				int i = table.getSelectedRow();
				if(i >= 0) {
					model.setValueAt(cod_client_textField.getText(), i, 0);
					model.setValueAt(cod_magazin_textField.getText(), i, 1);
					model.setValueAt(nume_client_textField.getText(), i, 2);
					model.setValueAt(prenume_client_textField.getText(), i, 3);
					model.setValueAt(cnp_textField.getText(), i, 4);
					model.setValueAt(stare_civila_textField.getText(), i, 5);
					model.setValueAt(adresa_textField.getText(), i, 6);
					model.setValueAt(email_textField.getText(), i, 7);
					model.setValueAt(coment_textField.getText(), i, 8);
					JOptionPane.showMessageDialog(null, "Actualizare cu succes");
					update_client();
				}
				// altfel afisam un mesaj de eroare
				else {
					JOptionPane.showMessageDialog(null, "Actualizare nereusita");
				}
			}
		});
		update_client_btn.setBounds(10, 286, 159, 23);
		panel.add(update_client_btn);
		
		JButton delete_client_btn = new JButton("Stergere_client");
		delete_client_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// daca am selectat un rand, stergem clientul
				int i = table.getSelectedRow();
				if(i >= 0) {
					model.removeRow(i);
					JOptionPane.showMessageDialog(null, "Stergere cu succes");
					delete_client();
				}
				// altfel afisam un mesaj de eroare
				else {
					JOptionPane.showMessageDialog(null, "Stergere nereusita");
				}
			}
		});
		delete_client_btn.setBounds(10, 320, 159, 23);
		panel.add(delete_client_btn);
		
		JButton clear_btn = new JButton("Clear");
		clear_btn.addActionListener(new ActionListener() {
			
			// stergem textul din toate campurile de scriere text
			public void actionPerformed(ActionEvent e) {
				cod_client_textField.setText("");
				cod_magazin_textField.setText("");
				nume_client_textField.setText("");
				prenume_client_textField.setText("");
				cnp_textField.setText("");
				stare_civila_textField.setText("");
				adresa_textField.setText("");
				email_textField.setText("");
				coment_textField.setText("");
			}
		});
		clear_btn.setBounds(10, 354, 159, 23);
		panel.add(clear_btn);
		
		JButton delogare_btn = new JButton("Delogare");
		delogare_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// daca apasam de butonul "Delogare", vom fi intrebati daca vrem sa ne delogam
				// daca raspunsul este da, vom fi delogati, altfel ramanem logati
				int a = JOptionPane.showConfirmDialog(delogare_btn, "Sunteti sigur ca vreti sa va delogati?");
                if (a == JOptionPane.YES_OPTION) {       
                	JOptionPane.showMessageDialog(null, "Delogare admin realizata cu succes");
                	System.exit(0);
                	//Alege_logare_ca_Admin_sau_Client_GUI obj = new Alege_logare_ca_Admin_sau_Client_GUI();                 
                }				
				
			}
		});
		delogare_btn.setBounds(10, 451, 159, 23);
		panel.add(delogare_btn);
	}
	
	
	// functie care adauga un client in BD
	public void add_client(){
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
        String query = " insert into Client (cod, cod_magazin, nume_client, prenume_client, CNP, " +
                "stare_civila, adresa, e_mail, coment)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //String q="insert into Produs values";
        //System.out.println("Suntem inainte de try de la client");
        try
        {
            //System.out.println("Suntem in try de la client");
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Integer.parseInt(cod_client_textField.getText() ) );
            preparedStmt.setInt (2, Integer.parseInt(cod_magazin_textField.getText() ) );
            preparedStmt.setString (3, nume_client_textField.getText() );
            preparedStmt.setString (4, prenume_client_textField.getText() );
            preparedStmt.setString (5, cnp_textField.getText() );
            preparedStmt.setString (6, stare_civila_textField.getText() );
            preparedStmt.setString (7, adresa_textField.getText() );
            preparedStmt.setString (8, email_textField.getText() );
            preparedStmt.setString (9, coment_textField.getText() );

            // execute the preparedstatement
            preparedStmt.execute();
            JOptionPane.showMessageDialog(null, "Inregistrarea clientului in BD a fost efectuata cu succes");
            //System.out.println("A scris clientul in BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	// functie care actualizeaza un client in BD
	public void update_client(){
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
        // create the java mysql update preparedstatement
        String query = "update Client set cod = ?, nume_client = ?, prenume_client = ?," +
                " stare_civila = ?, adresa = ?, e_mail = ?, coment = ? where CNP = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt (1, Integer.parseInt(cod_client_textField.getText() ) );
            preparedStmt.setString (2, nume_client_textField.getText() );
            preparedStmt.setString (3, prenume_client_textField.getText() );
            //preparedStmt.setString (4, cnp_textField.getText() );
            preparedStmt.setString (4, stare_civila_textField.getText() );
            preparedStmt.setString (5, adresa_textField.getText() );
            preparedStmt.setString (6, email_textField.getText() );
            preparedStmt.setString (7, coment_textField.getText() );

            //preparedStmt.setInt (8, Integer.parseInt(cod_client_textField.getText() ) );
            preparedStmt.setString (8, cnp_textField.getText() );
            //preparedStmt.setInt (10, Integer.parseInt(cod_client_textField.getText() ) );
            //preparedStmt.setInt (2, produs.getCod_magazin() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            System.out.println("A actualizat clientul in BD");
            JOptionPane.showMessageDialog(null, "Actualizarea clientului in BD a fost efectuata cu succes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	// functie care sterge un client din BD
	public void delete_client(){
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
        String query = "DELETE FROM Client WHERE cod = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt (1, Integer.parseInt(cod_client_textField.getText() ) );

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Stergerea clientului din BD a fost efectuata cu succes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



