package Dummy_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alegere_produs_sau_client_GUI {

	private JFrame frmAlegeremanipulareclientisauproduse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alegere_produs_sau_client_GUI window = new Alegere_produs_sau_client_GUI();
					window.frmAlegeremanipulareclientisauproduse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Alegere_produs_sau_client_GUI() {
		initialize();
		frmAlegeremanipulareclientisauproduse.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlegeremanipulareclientisauproduse = new JFrame();
		frmAlegeremanipulareclientisauproduse.setTitle("ALEGERE_MANIPULARE_CLIENTI_SAU_PRODUSE");
		frmAlegeremanipulareclientisauproduse.setBounds(100, 100, 481, 273);
		frmAlegeremanipulareclientisauproduse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlegeremanipulareclientisauproduse.getContentPane().setLayout(null);
		
		JButton view_clients_btn = new JButton("Vizualizare_clienti");
		view_clients_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Admin_tabel_client_GUI();
				
			}
		});
		view_clients_btn.setBounds(28, 75, 186, 63);
		frmAlegeremanipulareclientisauproduse.getContentPane().add(view_clients_btn);
		
		JButton view_products_btn = new JButton("Vizualizare_produse");
		view_products_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Admin_tabel_produs_GUI();
				
			}
		});
		view_products_btn.setBounds(250, 75, 186, 63);
		frmAlegeremanipulareclientisauproduse.getContentPane().add(view_products_btn);
	}

}
