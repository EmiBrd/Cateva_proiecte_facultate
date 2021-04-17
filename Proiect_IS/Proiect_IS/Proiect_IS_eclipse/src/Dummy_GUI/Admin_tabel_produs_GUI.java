package Dummy_GUI;

import Conectare_BD.Connect_DB;
import Magazin_pachet.Magazin;
import Magazin_pachet.Produs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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

public class Admin_tabel_produs_GUI {

    private JFrame frmAdminguimanipulareproduse;
    private JTextField numar_bucati_textField;
    private JTextField pret_textField;
    private JTextField culoare_textField;
    private JTextField material_produs_textField;
    private JTextField nume_produs_textField;
    private JTextField cod_magazin_textField;
    private JTextField cod_textField;
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
                	Admin_tabel_produs_GUI window = new Admin_tabel_produs_GUI();
                    window.frmAdminguimanipulareproduse.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Admin_tabel_produs_GUI() {
        initialize();
        frmAdminguimanipulareproduse.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Connect_DB connect_db = new Connect_DB();
        magazin = new Magazin( connect_db.get_conexiune("root", "0000") );


        frmAdminguimanipulareproduse = new JFrame();
        frmAdminguimanipulareproduse.setTitle("ADMIN_GUI_MANIPULARE_PRODUSE");
        frmAdminguimanipulareproduse.setBounds(100, 100, 1220, 562);
        frmAdminguimanipulareproduse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAdminguimanipulareproduse.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1204, 523);
        frmAdminguimanipulareproduse.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Cod_produs");
        lblNewLabel.setBounds(10, 11, 96, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Cod_magazin");
        lblNewLabel_1.setBounds(10, 36, 96, 14);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Nume_produs");
        lblNewLabel_2.setBounds(10, 61, 96, 14);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Material_produs");
        lblNewLabel_3.setBounds(10, 86, 96, 14);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Culoare");
        lblNewLabel_4.setBounds(10, 111, 96, 14);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Pret");
        lblNewLabel_5.setBounds(10, 136, 96, 14);
        panel.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Numar_bucati");
        lblNewLabel_6.setBounds(10, 161, 96, 14);
        panel.add(lblNewLabel_6);

        numar_bucati_textField = new JTextField();
        numar_bucati_textField.setBounds(111, 158, 164, 20);
        panel.add(numar_bucati_textField);
        numar_bucati_textField.setColumns(10);

        pret_textField = new JTextField();
        pret_textField.setBounds(111, 133, 164, 20);
        panel.add(pret_textField);
        pret_textField.setColumns(10);

        culoare_textField = new JTextField();
        culoare_textField.setBounds(111, 108, 164, 20);
        panel.add(culoare_textField);
        culoare_textField.setColumns(10);

        material_produs_textField = new JTextField();
        material_produs_textField.setBounds(111, 83, 164, 20);
        panel.add(material_produs_textField);
        material_produs_textField.setColumns(10);

        nume_produs_textField = new JTextField();
        nume_produs_textField.setBounds(111, 58, 164, 20);
        panel.add(nume_produs_textField);
        nume_produs_textField.setColumns(10);

        cod_magazin_textField = new JTextField();
        cod_magazin_textField.setBounds(111, 33, 164, 20);
        panel.add(cod_magazin_textField);
        cod_magazin_textField.setColumns(10);

        cod_textField = new JTextField();
        cod_textField.setBounds(111, 8, 164, 20);
        panel.add(cod_textField);
        cod_textField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(285, 11, 909, 501);
        panel.add(scrollPane);

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
                "Pret", "Numar_bucati"};
        Object[] row = new Object[7];
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);


        for(Produs pp : magazin.getProdus_list() )
        {

            model.addRow(new Object[]{
                    pp.getCod(), pp.getCod_magazin(), pp.getNume_produs(), pp.getMaterial_produs(),
                    pp.getCuloare_produs(), pp.getPret_produs(), pp.getNumar_bucati() });
        }



        JButton btnNewButton = new JButton("Adauga_produs");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// daca nu sunt completate toate campurile, afisam un mesaj de eroare
                if(cod_textField.getText().equals("") || cod_magazin_textField.getText().equals("") ||
                        nume_produs_textField.getText().equals("") || material_produs_textField.getText().equals("") ||
                        culoare_textField.getText().equals("") || pret_textField.getText().equals("") ||
                        numar_bucati_textField.getText().equals("") )
                {
                    JOptionPane.showMessageDialog(null, "Adaugare nereusita. Completati toate campurile");
                }
                // altfel adaugam produsul
                else {
                    row[0] = cod_textField.getText();
                    row[1] = cod_magazin_textField.getText();
                    row[2] = nume_produs_textField.getText();
                    row[3] = material_produs_textField.getText();
                    row[4] = culoare_textField.getText();
                    row[5] = pret_textField.getText();
                    row[6] = numar_bucati_textField.getText();
                    model.addRow(row);
                    
                    add_produs();
                    // dupa ce adaugam produsul, facem campurile de scriere text libere
                    cod_textField.setText("");
                    cod_magazin_textField.setText("");
                    nume_produs_textField.setText("");
                    material_produs_textField.setText("");
                    culoare_textField.setText("");
                    pret_textField.setText("");
                    numar_bucati_textField.setText("");
                    JOptionPane.showMessageDialog(null, "Adaugare cu succes");
                }

            }
        });
        btnNewButton.setBounds(53, 203, 156, 23);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Actualizare_produs");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// daca am selectat un rand, se actualizeaza produsul
                int i = table.getSelectedRow();
                if(i >= 0) {
                    model.setValueAt(cod_textField.getText(), i, 0);
                    model.setValueAt(cod_magazin_textField.getText(), i, 1);
                    model.setValueAt(nume_produs_textField.getText(), i, 2);
                    model.setValueAt(material_produs_textField.getText(), i, 3);
                    model.setValueAt(culoare_textField.getText(), i, 4);
                    model.setValueAt(pret_textField.getText(), i, 5);
                    model.setValueAt(numar_bucati_textField.getText(), i, 6);
                    JOptionPane.showMessageDialog(null, "Actualizare cu succes");
                    update_produs();
                }
                // altfel afisam un mesaj de eroare
                else {
                    JOptionPane.showMessageDialog(null, "Actualizare nereusita");
                }
            }
        });
        btnNewButton_1.setBounds(53, 250, 156, 23);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Stergere_produs");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// daca am selectat un rand, stergem produsul
                int i = table.getSelectedRow();
                if(i >= 0) {
                    model.removeRow(i);
                    JOptionPane.showMessageDialog(null, "Stergere cu succes");
                    delete_produs();
                }
                // altfel afisam mesaj de eroare
                else {
                    JOptionPane.showMessageDialog(null, "Stergere nereusita");
                }
            }
        });
        btnNewButton_2.setBounds(53, 295, 156, 23);
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Clear");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// stergem textul din toate campurile de scriere text
                cod_textField.setText("");
                cod_magazin_textField.setText("");
                nume_produs_textField.setText("");
                material_produs_textField.setText("");
                culoare_textField.setText("");
                pret_textField.setText("");
                numar_bucati_textField.setText("");
            }
        });
        btnNewButton_3.setBounds(53, 342, 156, 23);
        panel.add(btnNewButton_3);
        
        JButton delogare_btn = new JButton("Delogare");
        delogare_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// daca apasam de butonul "Delogare", vom fi intrebati daca vrem sa ne delogam
				// daca raspunsul este da, vom fi delogati, altfel ramanem logati
        		int a = JOptionPane.showConfirmDialog(delogare_btn, "Sunteti sigur ca vreti sa va delogati?");
                if (a == JOptionPane.YES_OPTION) {       
                	JOptionPane.showMessageDialog(null, "Delogare admin realizata cu succes");
                	System.exit(0);               
                }	
        		
        	}
        });
        delogare_btn.setBounds(53, 431, 156, 31);
        panel.add(delogare_btn);
    }

    
    // functie care adauga un produs in BD
    public void add_produs(){
    	connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
    	
    	
        String query = "insert into Produs (cod, cod_magazin, nume_produs, material_produs, culoare_produs, " +
                "pret_produs, numar_bucati)" + " values (?, ?, ?, ?, ?, ?, ?)";
        //String q="insert into Produs values";
        try
        {
            //System.out.println("Suntem in try de la produs");
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Integer.parseInt(cod_textField.getText() ) );
            preparedStmt.setInt (2, Integer.parseInt(cod_magazin_textField.getText() ) );
            preparedStmt.setString   (3, nume_produs_textField.getText() );
            preparedStmt.setString(4, material_produs_textField.getText() );
            preparedStmt.setString    (5, culoare_textField.getText() );
            preparedStmt.setFloat(6, Float.parseFloat(pret_textField.getText() ) );
            preparedStmt.setInt(7, Integer.parseInt(numar_bucati_textField.getText() ) );

            // execute the preparedstatement
            preparedStmt.execute();
            //System.out.println("A scris produsul in BD");
            JOptionPane.showMessageDialog(null, "Inregistrarea produsului in BD a fost efectuata cu succes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // functie care actualizeaza un produs in BD
    public void update_produs(){
    	connect_db = new Connect_DB();
		con = connect_db.get_conexiune("root", "0000");
		magazin = new Magazin( connect_db.get_conexiune("root", "0000") );
    	
        // create the java mysql update preparedstatement
        String query = "update Produs set cod = ?, material_produs = ?," + " culoare_produs = ?," 
                       + " pret_produs = ?, numar_bucati = ? where  nume_produs = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt (1, Integer.parseInt(cod_textField.getText() ) );
            preparedStmt.setString (2, material_produs_textField.getText() );
            preparedStmt.setString (3, culoare_textField.getText() );
            preparedStmt.setFloat (4, Float.parseFloat(pret_textField.getText() ) );
            preparedStmt.setInt (5, Integer.parseInt(numar_bucati_textField.getText() ) );
            
            preparedStmt.setString (6, nume_produs_textField.getText() );
            //preparedStmt.setInt (2, produs.getCod_magazin() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizarea produsului in BD a fost efectuata cu succes");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // functie care sterge un produs in BD
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

    
    
}



