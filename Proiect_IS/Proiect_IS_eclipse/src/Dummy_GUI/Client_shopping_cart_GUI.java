package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Conectare_BD.Connect_DB;
import Magazin_pachet.Magazin;
import Magazin_pachet.Produs;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class Client_shopping_cart_GUI {

	private JFrame frmShoppingcartclient;
	private JTable table;
	private JTable table_1;
	private JTextField pret_total_textField;
	
	private DefaultTableModel model;
	private DefaultTableModel model2;
    private Magazin magazin;
	private Connect_DB connect_db;
	private Connection con;
	private List<Produs> produs_list_din_cosulet = new LinkedList<>();

	private JTextField numar_bucati_textField;
	private JTextField pret_textField;
	private JTextField culoare_textField;
	private JTextField material_produs_textField;
	private JTextField nume_produs_textField;
	private JTextField cod_magazin_textField;
	private JTextField cod_textField;
	
	Object[] row = new Object[7];
	private Object[] row2 = new Object[7];
	float pret_total = 0f;
	
	//Document document;
	//Paragraph parag;
	//PdfPTable table_pdf;
	
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//generare_PDF();
					
					Client_shopping_cart_GUI window = new Client_shopping_cart_GUI();
					window.frmShoppingcartclient.setVisible(true);					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_shopping_cart_GUI() {
		initialize();
		frmShoppingcartclient.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connect_DB connect_db = new Connect_DB();
        magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		
		frmShoppingcartclient = new JFrame();
		frmShoppingcartclient.setTitle("Cumparare_produse");
		frmShoppingcartclient.setBounds(100, 100, 1402, 615);
		frmShoppingcartclient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShoppingcartclient.getContentPane().setLayout(null);
		
		JScrollPane produse_magazin_scrollPane = new JScrollPane();
		produse_magazin_scrollPane.setBounds(806, 39, 554, 526);
		frmShoppingcartclient.getContentPane().add(produse_magazin_scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
                cod_textField.setText(model.getValueAt(i, 0).toString() );
                cod_magazin_textField.setText(model.getValueAt(i, 1).toString() );
                nume_produs_textField.setText(model.getValueAt(i, 2).toString() );
                material_produs_textField.setText(model.getValueAt(i, 3).toString() );
                culoare_textField.setText(model.getValueAt(i, 4).toString() );
                pret_textField.setText(model.getValueAt(i, 5).toString() );
                numar_bucati_textField.setText(model.getValueAt(i, 6).toString() );
				
			}
		});
		
		model = new DefaultTableModel();
        Object[] column = {"Cod", "Cod_magazin", "Nume_produs", "Material_produs", "Culoare",
                "Pret", "Nr bucati"};
        //Object[] row = new Object[7];
        model.setColumnIdentifiers(column);
        table.setModel(model);
		
		produse_magazin_scrollPane.setViewportView(table);
		
		for(Produs pp : magazin.getProdus_list() )
        {
            model.addRow(new Object[]{
                    pp.getCod(), pp.getCod_magazin(), pp.getNume_produs(), pp.getMaterial_produs(),
                    pp.getCuloare_produs(), pp.getPret_produs(), pp.getNumar_bucati() });
        }
		
				
		JScrollPane produse_din_cosulet_scrollPane = new JScrollPane();
		produse_din_cosulet_scrollPane.setBounds(261, 39, 541, 526);
		frmShoppingcartclient.getContentPane().add(produse_din_cosulet_scrollPane);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table_1.getSelectedRow();
                cod_textField.setText(model2.getValueAt(i, 0).toString() );
                cod_magazin_textField.setText(model2.getValueAt(i, 1).toString() );
                nume_produs_textField.setText(model2.getValueAt(i, 2).toString() );
                material_produs_textField.setText(model2.getValueAt(i, 3).toString() );
                culoare_textField.setText(model2.getValueAt(i, 4).toString() );
                pret_textField.setText(model2.getValueAt(i, 5).toString() );
                numar_bucati_textField.setText(model2.getValueAt(i, 6).toString() );
				
			}
		});
		
		model2 = new DefaultTableModel();
        Object[] column2 = {"Cod", "Cod_magazin", "Nume_produs", "Material_produs", "Culoare",
                "Pret", "Nr bucati"};
        //Object[] row2 = new Object[7];
        model2.setColumnIdentifiers(column2);
        table_1.setModel(model2);
		
				
		produse_din_cosulet_scrollPane.setViewportView(table_1);
		
		
		JButton adaugare_in_cos_btn = new JButton("Adaugare_in_cos");
		adaugare_in_cos_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = table.getSelectedRow();
                if(i >= 0) {
                	if(cod_textField.getText().equals("") || cod_magazin_textField.getText().equals("") ||
                            nume_produs_textField.getText().equals("") || material_produs_textField.getText().equals("") ||
                            culoare_textField.getText().equals("") || pret_textField.getText().equals("") ||
                            numar_bucati_textField.getText().equals("") )
                    {
                        JOptionPane.showMessageDialog(null, "Adaugare in cosulet nereusita. Completati toate campurile");
                    }
    				
    				else {
                        //JOptionPane.showMessageDialog(null, "Actualizare tabel cu succes");    					
                        add_produs_in_cos();

                        cod_textField.setText("");
                        cod_magazin_textField.setText("");
                        nume_produs_textField.setText("");
                        material_produs_textField.setText("");
                        culoare_textField.setText("");
                        pret_textField.setText("");
                        numar_bucati_textField.setText("");                       
                    }
                	
                }
                else {
                    JOptionPane.showMessageDialog(null, "Actualizare nereusita");
                }
				
				
			}
		});
		adaugare_in_cos_btn.setBounds(43, 245, 175, 39);
		frmShoppingcartclient.getContentPane().add(adaugare_in_cos_btn);
		
		JButton cumparare_btn = new JButton("Cumparare");
		cumparare_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
												
				generare_pdf();
	
			}
		});
		cumparare_btn.setBounds(43, 410, 175, 39);
		frmShoppingcartclient.getContentPane().add(cumparare_btn);
		
		JButton scoatere_din_cos_btn = new JButton("Scoatere_din_cos");
		scoatere_din_cos_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = table_1.getSelectedRow();
                if(i >= 0) {
                	if(cod_textField.getText().equals("") || cod_magazin_textField.getText().equals("") ||
                            nume_produs_textField.getText().equals("") || material_produs_textField.getText().equals("") ||
                            culoare_textField.getText().equals("") || pret_textField.getText().equals("") ||
                            numar_bucati_textField.getText().equals("") )
                    {
                        JOptionPane.showMessageDialog(null, "Scoatere din cosulet nereusita. Completati toate campurile");
                    }
    				
    				else {
                        //JOptionPane.showMessageDialog(null, "Actualizare tabel cu succes");    					
                        scoatere_produs_din_cos();

                        cod_textField.setText("");
                        cod_magazin_textField.setText("");
                        nume_produs_textField.setText("");
                        material_produs_textField.setText("");
                        culoare_textField.setText("");
                        pret_textField.setText("");
                        numar_bucati_textField.setText("");                       
                    }
                	
                }
                else {
                    JOptionPane.showMessageDialog(null, "Actualizare scoatere din cosulet nereusita");
                }
								
			}
		});
		scoatere_din_cos_btn.setBounds(43, 301, 175, 39);
		frmShoppingcartclient.getContentPane().add(scoatere_din_cos_btn);
		
		
			
		JLabel produse_cosulet_Label = new JLabel("Produse_din_cosulet");
		produse_cosulet_Label.setHorizontalAlignment(SwingConstants.CENTER);
		produse_cosulet_Label.setBounds(461, 11, 138, 21);
		frmShoppingcartclient.getContentPane().add(produse_cosulet_Label);
		
		JLabel produse_magazin_Label = new JLabel("Produse_din_magazin");
		produse_magazin_Label.setHorizontalAlignment(SwingConstants.CENTER);
		produse_magazin_Label.setBounds(1007, 10, 149, 23);
		frmShoppingcartclient.getContentPane().add(produse_magazin_Label);
		
		JLabel pret_total_Label = new JLabel("Pret_total");
		pret_total_Label.setHorizontalAlignment(SwingConstants.CENTER);
		pret_total_Label.setBounds(23, 362, 60, 27);
		frmShoppingcartclient.getContentPane().add(pret_total_Label);
		
		pret_total_textField = new JTextField();
		pret_total_textField.setBounds(86, 362, 132, 27);
		frmShoppingcartclient.getContentPane().add(pret_total_textField);
		pret_total_textField.setColumns(10);
		
		JButton delogare_btn = new JButton("Delogare");
		delogare_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = JOptionPane.showConfirmDialog(delogare_btn, "Sunteti sigur ca vreti sa va delogati?");
                if (a == JOptionPane.YES_OPTION) {       
                	JOptionPane.showMessageDialog(null, "Delogare admin realizata cu succes");
                	System.exit(0);
                	//Alege_logare_ca_Admin_sau_Client_GUI obj = new Alege_logare_ca_Admin_sau_Client_GUI();                 
                }	
				
			}
		});
		delogare_btn.setBounds(43, 487, 175, 44);
		frmShoppingcartclient.getContentPane().add(delogare_btn);
		
		numar_bucati_textField = new JTextField();
		numar_bucati_textField.setColumns(10);
		numar_bucati_textField.setBounds(111, 202, 143, 20);
		frmShoppingcartclient.getContentPane().add(numar_bucati_textField);
		
		JLabel lblNewLabel_6 = new JLabel("Numar_bucati");
		lblNewLabel_6.setBounds(10, 205, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("Pret");
		lblNewLabel_5.setBounds(10, 180, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel_5);
		
		pret_textField = new JTextField();
		pret_textField.setColumns(10);
		pret_textField.setBounds(111, 177, 143, 20);
		frmShoppingcartclient.getContentPane().add(pret_textField);
		
		culoare_textField = new JTextField();
		culoare_textField.setColumns(10);
		culoare_textField.setBounds(111, 152, 143, 20);
		frmShoppingcartclient.getContentPane().add(culoare_textField);
		
		JLabel lblNewLabel_4 = new JLabel("Culoare");
		lblNewLabel_4.setBounds(10, 155, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Material_produs");
		lblNewLabel_3.setBounds(10, 130, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel_3);
		
		material_produs_textField = new JTextField();
		material_produs_textField.setColumns(10);
		material_produs_textField.setBounds(111, 127, 143, 20);
		frmShoppingcartclient.getContentPane().add(material_produs_textField);
		
		nume_produs_textField = new JTextField();
		nume_produs_textField.setColumns(10);
		nume_produs_textField.setBounds(111, 102, 143, 20);
		frmShoppingcartclient.getContentPane().add(nume_produs_textField);
		
		JLabel lblNewLabel_2 = new JLabel("Nume_produs");
		lblNewLabel_2.setBounds(10, 105, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Cod_magazin");
		lblNewLabel_1.setBounds(10, 80, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel_1);
		
		cod_magazin_textField = new JTextField();
		cod_magazin_textField.setColumns(10);
		cod_magazin_textField.setBounds(111, 77, 143, 20);
		frmShoppingcartclient.getContentPane().add(cod_magazin_textField);
		
		cod_textField = new JTextField();
		cod_textField.setColumns(10);
		cod_textField.setBounds(111, 52, 143, 20);
		frmShoppingcartclient.getContentPane().add(cod_textField);
		
		JLabel lblNewLabel = new JLabel("Cod_produs");
		lblNewLabel.setBounds(10, 55, 96, 14);
		frmShoppingcartclient.getContentPane().add(lblNewLabel);
	}
	
	
	// functie care adauga un produs in cosuletul de cumparaturi si il scoate din BD
	public void add_produs_in_cos(){
    	connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );

		
		for (Produs pp : magazin.getProdus_list() ) {
			if( pp.getCod() == Integer.parseInt(cod_textField.getText() )  )  
            {
				
				if (Integer.parseInt(numar_bucati_textField.getText() ) <= 0 ) {
					JOptionPane.showMessageDialog(null, "Numarul de bucati nu poate fi mai mic sau egal cu 0");
				}
				
				else if (pp.getNumar_bucati() == Integer.parseInt(numar_bucati_textField.getText() ) ) 
				{
					JOptionPane.showMessageDialog(null, "Suntem in cazul de egalitate, dici stergem produsul");
					int i = table.getSelectedRow();
	                if(i >= 0) {						
	                	int cod = Integer.parseInt(cod_textField.getText() );
                    	int cod_magazin = Integer.parseInt(cod_magazin_textField.getText() );
                    	String nume_produs = nume_produs_textField.getText();
                    	String material_produs = material_produs_textField.getText();
                    	String culoare = culoare_textField.getText();
                    	float pret = Float.parseFloat(pret_textField.getText() ) ;
                    	int nr_bucati = Integer.parseInt(numar_bucati_textField.getText() ) ;
                    	
                    	produs_list_din_cosulet.add(new Produs(cod, cod_magazin, nume_produs, material_produs,
                    									culoare, pret, nr_bucati) );
	                	
	                	row2[0] = cod_textField.getText();
                    	row2[1] = cod_magazin_textField.getText();
                    	row2[2] = nume_produs_textField.getText();
                    	row2[3] = material_produs_textField.getText();
                    	row2[4] = culoare_textField.getText();
                    	row2[5] = pret_textField.getText();
                    	row2[6] = numar_bucati_textField.getText();
                    	
                    	pret_total = pret_total + (Float.parseFloat(pret_textField.getText() ) * 
	            			     	 Integer.parseInt( numar_bucati_textField.getText() ) );
                    	pret_total_textField.setText("" + pret_total);
                    	
		            	model2.addRow(row2);
		            	model.removeRow(i);
	                }			 
					
					String query = "DELETE FROM Produs WHERE cod = ?";

			        try
			        {	
			            // create the mysql insert preparedstatement
			            PreparedStatement preparedStmt = con.prepareStatement(query);

			            preparedStmt.setInt (1, pp.getCod()  );

			            // execute the preparedstatement
			            preparedStmt.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Stergerea produsului din BD a fost efectuata cu succes");			           			            		                       

			        } catch (SQLException e) {
			            e.printStackTrace();
			        } 		        
				}
				
				
				else if(pp.getNumar_bucati() > Integer.parseInt(numar_bucati_textField.getText() ) ) {
					int nr_bucati_actual = pp.getNumar_bucati() - Integer.parseInt(numar_bucati_textField.getText() );
					String query = "update Produs set numar_bucati = ? where  cod = ?";
					
					try
					{			
						// create the mysql insert preparedstatement
						PreparedStatement preparedStmt = con.prepareStatement(query);

						preparedStmt.setInt (1, nr_bucati_actual );
			     
						preparedStmt.setInt (2, Integer.parseInt(cod_textField.getText() ) );
						//preparedStmt.setString (2, nume_produs_textField.getText() );

						// execute the preparedstatement
						preparedStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Actualizarea produsului in BD a fost efectuata cu succes");
						
						int i = table.getSelectedRow();
		                if(i >= 0) {
		                	int cod = Integer.parseInt(cod_textField.getText() );
	                    	int cod_magazin = Integer.parseInt(cod_magazin_textField.getText() );
	                    	String nume_produs = nume_produs_textField.getText();
	                    	String material_produs = material_produs_textField.getText();
	                    	String culoare = culoare_textField.getText();
	                    	float pret = Float.parseFloat(pret_textField.getText() ) ;
	                    	int nr_bucati = Integer.parseInt(numar_bucati_textField.getText() ) ;
	                    	
	                    	produs_list_din_cosulet.add(new Produs(cod, cod_magazin, nume_produs, material_produs,
	                    									culoare, pret, nr_bucati) );
						
		                	row2[0] = cod_textField.getText();
	                    	row2[1] = cod_magazin_textField.getText();
	                    	row2[2] = nume_produs_textField.getText();
	                    	row2[3] = material_produs_textField.getText();
	                    	row2[4] = culoare_textField.getText();
	                    	row2[5] = pret_textField.getText();
	                    	row2[6] = numar_bucati_textField.getText();
			            	model2.addRow(row2);
			            	
			            	model.setValueAt(nr_bucati_actual , i, 6);
			            	pret_total = pret_total + (Float.parseFloat(pret_textField.getText() ) * 
			            			     Integer.parseInt( numar_bucati_textField.getText() ) );
			            	pret_total_textField.setText("" + pret_total);
		                }
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
	                JOptionPane.showMessageDialog(null, "Adaugare produs in cosulet cu succes");
	                
				}
				

				else {
					JOptionPane.showMessageDialog(null, "Numarul de bucati cerut este prea mare");
				}
				
				break;
            }
		}
    	
		
	}
	
	
	// functie care scoate un produs din cosuletul de cumparaturi si il introduce din nou in BD
	public void scoatere_produs_din_cos() {
		connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
		//magazin2 = new Magazin( connect_db.get_conexiune("root", "0000") );
		
		
		for (Produs prod_magaz : magazin.getProdus_list() ) 
		{
	
		for (Produs pp : produs_list_din_cosulet ) 
		{
			if (pp.getCod() == prod_magaz.getCod() ) {
			
			if( pp.getCod() == Integer.parseInt(cod_textField.getText() )  )  
            {
				
				if (Integer.parseInt(numar_bucati_textField.getText() ) <= 0 ) {
					JOptionPane.showMessageDialog(null, "Numarul de bucati din cosulet nu poate fi mai mic sau egal cu 0");
				}
				
				else if (pp.getNumar_bucati() == Integer.parseInt(numar_bucati_textField.getText() ) ) 
				{
					JOptionPane.showMessageDialog(null, "Suntem in cazul de egalitate, deci stergem produsul din cosulet");		
					
					int nr_bucati_actual = prod_magaz.getNumar_bucati() + Integer.parseInt(numar_bucati_textField.getText() );
					String query = "update Produs set numar_bucati = ? where  cod = ?";
					
					try
					{			
						// create the mysql insert preparedstatement
						PreparedStatement preparedStmt = con.prepareStatement(query);

						preparedStmt.setInt (1, nr_bucati_actual );
			     
						preparedStmt.setInt (2, Integer.parseInt(cod_textField.getText() ) );
						//preparedStmt.setString (2, nume_produs_textField.getText() );

						// execute the preparedstatement
						preparedStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Actualizarea produsului in BD a fost efectuata cu succes");
						
						int i = table_1.getSelectedRow();
						int k = table.getSelectedRow();
		                if(i >= 0) {						
		                	
	                    	model.setValueAt(cod_textField.getText(), k, 0);
	                        model.setValueAt(cod_magazin_textField.getText(), k, 1);
	                        model.setValueAt(nume_produs_textField.getText(), k, 2);
	                        model.setValueAt(material_produs_textField.getText(), k, 3);
	                        model.setValueAt(culoare_textField.getText(), k, 4);
	                        model.setValueAt(pret_textField.getText(), k, 5);
	                        model.setValueAt(nr_bucati_actual, k, 6);
	                        //model.setValueAt(numar_bucati_textField.getText() + prod_magaz.getNumar_bucati(), i, 6);
	                        JOptionPane.showMessageDialog(null, "Actualizare cu succes");
	                        //update_produs();
			            	
	                        pret_total = pret_total - (Float.parseFloat(pret_textField.getText() ) * 
		            			     	 Integer.parseInt( numar_bucati_textField.getText() ) );
	                        pret_total_textField.setText("" + pret_total);
	                        
			            	model2.removeRow(i);
			            	produs_list_din_cosulet.remove(pp);
		                }		
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				
				else if(pp.getNumar_bucati() > Integer.parseInt(numar_bucati_textField.getText() ) ) {
					int nr_bucati_actual = prod_magaz.getNumar_bucati() + Integer.parseInt(numar_bucati_textField.getText() );
					String query = "update Produs set numar_bucati = ? where  cod = ?";
					
					try
					{			
						// create the mysql insert preparedstatement
						PreparedStatement preparedStmt = con.prepareStatement(query);

						preparedStmt.setInt (1, nr_bucati_actual );
			     
						preparedStmt.setInt (2, Integer.parseInt(cod_textField.getText() ) );
						//preparedStmt.setString (2, nume_produs_textField.getText() );

						// execute the preparedstatement
						preparedStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Actualizarea produsului in BD a fost efectuata cu succes");
						
						int i = table_1.getSelectedRow();
						int k = table.getSelectedRow();
		                if(i >= 0) {						
		                	
	                    	model.setValueAt(cod_textField.getText(), k, 0);
	                        model.setValueAt(cod_magazin_textField.getText(), k, 1);
	                        model.setValueAt(nume_produs_textField.getText(), k, 2);
	                        model.setValueAt(material_produs_textField.getText(), k, 3);
	                        model.setValueAt(culoare_textField.getText(), k, 4);
	                        model.setValueAt(pret_textField.getText(), k, 5);
	                        model.setValueAt(nr_bucati_actual, k, 6);
	                        //model.setValueAt(numar_bucati_textField.getText() + prod_magaz.getNumar_bucati(), i, 6);
	                        JOptionPane.showMessageDialog(null, "Actualizare cu succes");
	                        //update_produs();
			            	
	                        pret_total = pret_total - (Float.parseFloat(pret_textField.getText() ) * 
	            			     	 	 Integer.parseInt( numar_bucati_textField.getText() ) );
	                        pret_total_textField.setText("" + pret_total);
	                        
			            	//model2.removeRow(i);
			            	//produs_list_din_cosulet.remove(pp);
		                }		
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
	                JOptionPane.showMessageDialog(null, "Scoatere produs din cosulet cu succes");
	                
				}
				

				else {
					JOptionPane.showMessageDialog(null, "Numarul de bucati cerut este prea mare");
				}
				break;
            }
					
            }

			//System.out.println(k);
		}
		
		}
		
	}
	
	
	// functie care sterge un produs din BD
	public void delete_produs(){
    	connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
    	
        String query = "DELETE FROM Produs WHERE cod = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt (1, Integer.parseInt(cod_textField.getText() ) );

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Stergerea produsului din BD a fost efectuata cu succes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	// functie pentru generarea de PDF
	void generare_pdf() {
		
		// Pentru PDF
		String path = "";
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int x = j.showSaveDialog(j);
		
		if(x == JFileChooser.APPROVE_OPTION) {
			path = j.getSelectedFile().getPath();
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		
		try {
						
			//String file_name = "C:\\D\\An3\\An3_sem1_exerc\\IS\\factura_proiect_IS.pdf";
			Document document = new Document();
			
			PdfWriter.getInstance(document, new FileOutputStream(path + " factura_magazin_roboti.pdf") );			
			document.open();
			
			Paragraph parag1 = new Paragraph("Factura din data: " + dtf.format(now) );
			document.add(parag1);	
			Paragraph parag2 = new Paragraph("Pretul total: " + pret_total);
			document.add(parag2);
			
			PdfPTable table_pdf = new PdfPTable(7);
			table_pdf.setWidthPercentage(105);
			table_pdf.setSpacingBefore(11f);
			table_pdf.setSpacingBefore(11f);
		
			float[] colWidth = {2f, 2f, 2f, 2f, 2f, 2f, 2f};		
			table_pdf.setWidths(colWidth);
			
			PdfPCell c1 = new PdfPCell(new Phrase("Cod_prod") );
			PdfPCell c2 = new PdfPCell(new Phrase("Cod_magazin") );
			PdfPCell c3 = new PdfPCell(new Phrase("Nume_prod") );
			PdfPCell c4 = new PdfPCell(new Phrase("Material") );
			PdfPCell c5 = new PdfPCell(new Phrase("Culoare") );
			PdfPCell c6 = new PdfPCell(new Phrase("Pret") );
			PdfPCell c7 = new PdfPCell(new Phrase("Nr_buc") );
			table_pdf.addCell(c1);
			table_pdf.addCell(c2);
			table_pdf.addCell(c3);
			table_pdf.addCell(c4);
			table_pdf.addCell(c5);
			table_pdf.addCell(c6);
			table_pdf.addCell(c7);
		
			table_pdf.setHeaderRows(1);
		
			for(int ii = 0; ii <model2.getRowCount(); ii++) {	
				table_pdf.addCell("" + model2.getValueAt(ii, 0) );
				table_pdf.addCell("" + model2.getValueAt(ii, 1) );
				table_pdf.addCell("" + model2.getValueAt(ii, 2) );
				table_pdf.addCell("" + model2.getValueAt(ii, 3) );
				table_pdf.addCell("" + model2.getValueAt(ii, 4) );
				table_pdf.addCell("" + model2.getValueAt(ii, 5) );
				table_pdf.addCell("" + model2.getValueAt(ii, 6) );
			}			
					
			document.add(table_pdf);
			
			document.close();
			JOptionPane.showMessageDialog(null, "Factura a fost generata cu succes. Va rugam sa va delogati.");
		}
		
		catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

