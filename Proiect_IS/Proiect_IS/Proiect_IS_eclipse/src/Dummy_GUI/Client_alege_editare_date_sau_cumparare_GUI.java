package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Client_alege_editare_date_sau_cumparare_GUI {

	private JFrame frmAlegeredintreeditaredatesauvizualizareproduse;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_alege_editare_date_sau_cumparare_GUI window = new Client_alege_editare_date_sau_cumparare_GUI();
					window.frmAlegeredintreeditaredatesauvizualizareproduse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_alege_editare_date_sau_cumparare_GUI() {
		initialize();
		frmAlegeredintreeditaredatesauvizualizareproduse.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlegeredintreeditaredatesauvizualizareproduse = new JFrame();
		frmAlegeredintreeditaredatesauvizualizareproduse.setTitle("Alegere_dintre_editare_date_sau_vizualizare_produse");
		frmAlegeredintreeditaredatesauvizualizareproduse.setBounds(100, 100, 518, 287);
		frmAlegeredintreeditaredatesauvizualizareproduse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlegeredintreeditaredatesauvizualizareproduse.getContentPane().setLayout(null);
		
		JButton editare_note_btn = new JButton("Editare_date");
		editare_note_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Client_actualizare_date_GUI();
				
			}
		});
		editare_note_btn.setBounds(59, 62, 156, 76);
		frmAlegeredintreeditaredatesauvizualizareproduse.getContentPane().add(editare_note_btn);
		
		JButton vizualizare_produse_btn = new JButton("Vizualizare_produse");
		vizualizare_produse_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Client_shopping_cart_GUI();
				
			}
		});
		vizualizare_produse_btn.setBounds(287, 61, 156, 79);
		frmAlegeredintreeditaredatesauvizualizareproduse.getContentPane().add(vizualizare_produse_btn);
		
		JButton delogare_btn = new JButton("Delogare");
		delogare_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = JOptionPane.showConfirmDialog(delogare_btn, "Sunteti sigur ca vreti sa iesiti?");
                if (a == JOptionPane.YES_OPTION) {       
                	JOptionPane.showMessageDialog(null, "Delogare client realizata cu succes");
                	System.exit(0);
                	//Alege_logare_ca_Admin_sau_Client_GUI obj = new Alege_logare_ca_Admin_sau_Client_GUI();                 
                }		
				
			}
		});
		delogare_btn.setBounds(174, 164, 138, 53);
		frmAlegeredintreeditaredatesauvizualizareproduse.getContentPane().add(delogare_btn);
	}

}
