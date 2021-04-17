package Bussiness_Layer;


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.Observable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Data_Layer.*;
import Presentation_Layer.*;


public class Restaurant extends Observable implements IRestaurantProcessing{
	
	private Restaurant_Serializator serial_restaur;
	
	private LinkedHashMap<Order, LinkedList<Menu_Item>> orderr;
	
	private LinkedList<Menu_Item> menu_listt;
	private LinkedList<Base_Product> base_product_list;
	private LinkedList<Composite_Product> composite_product_list;
	
	JFrame restaurant = new JFrame("  ");
	
	private JButton administrator;
	private JButton waiter;
	private JButton chef;
	
	DefaultTableModel table_def;
	
	private JPanel panou1;
	private JTable data;
	private JScrollPane jscroll;
	
	private String[] line_model = {"Menu", "Price", "BaseP || CompositeP"};
	
	/***
	 * constructor cu 1 parametru
	 * @param file_path
	 */
	public Restaurant(String file_path){
		
		Container con = new Container();
		con.setLayout( new GridLayout(3, 1) );
		
		serial_restaur = new Restaurant_Serializator(file_path);
		table_def = new DefaultTableModel();
		menu_listt = new LinkedList<Menu_Item>();
		base_product_list = new LinkedList<Base_Product>();
		composite_product_list = new LinkedList<Composite_Product>();
		orderr = new LinkedHashMap<Order, LinkedList<Menu_Item>>(); 
		
		restaurant.setSize(1000, 700);
		restaurant.setDefaultCloseOperation(restaurant.EXIT_ON_CLOSE);
		
		menu_listt = serial_restaur.deserialize();
		
		administrator = new JButton("Administrator");
		waiter = new JButton("Waiter");
		chef = new JButton("Chef");
		
		panou1 = new JPanel();
		data = new JTable();
		jscroll = new JScrollPane();
		well_formed();
		restaurant.setVisible(true);			
	}
	
	/***
	 * metoda pentru crearea unui meniu 
	 */
	@Override
	public void create_menu(Menu_Item menuit, Base_Product bp, Composite_Product cp) {
		menu_listt.add(menuit);
		String type_string = new String();
		//
		if(menuit.getType() == 'b'){
			type_string = "Base_Product";
			base_product_list.add(bp);
		}	
		else if( menuit.getType() == 'c' ){
			type_string = "Composite_Product";
			composite_product_list.add(cp);
		}	
		table_def.addRow(new Object[] {menuit.getName(), menuit.getPrice(), type_string } );
		serial_restaur.serialize( menu_listt );
		panou1.remove(jscroll);
		jscroll.setViewportView(data);
		panou1.add(jscroll);
		restaurant.add(panou1);
	}

	/***
	 * metoda pentru facerea unui tabel
	 */
	public void make_table() {
		panou1.remove(jscroll);
		jscroll.setViewportView(data);
		panou1.add(jscroll);
		restaurant.add(panou1);
	}

	/***
	 * metoda pentru modificarea/editarea unui meniu
	 */
	@Override
	public void edit_menu(Menu_Item menuit1, Menu_Item menuit2) {
		for(int i=0; i < menu_listt.size(); i++){
			if(menu_listt.get(i).getName().equals(menuit1.getName() ) ||
					menu_listt.get(i).getPrice()==(menuit1.getPrice() ) ){
				
				menu_listt.get(i).setName(menuit2.getName() );
				menu_listt.get(i).setPrice(menuit2.getPrice() );
				serial_restaur.serialize( menu_listt );
			}
		}
		if(menuit1.getType() == 'b'){
			int i = 0;
			while(i < base_product_list.size() ) {
				if(base_product_list.get(i).getName().equals(menuit1.getName() ) ){
					base_product_list.get(i).setName(menuit2.getName() );
					base_product_list.get(i).setPrice(menuit2.getPrice() );
					serial_restaur.serialize(menu_listt);
				}
				i++;
			}
		}
		else if( menuit1.getType() == 'c' ){
			int ji = 0;
			while(ji < composite_product_list.size() ) {
				if(composite_product_list.get(ji).getName().equals(menuit1.getName() ) ){
					composite_product_list.get(ji).setName(menuit2.getName() );
					composite_product_list.get(ji).setPrice(menuit2.getPrice() );
					for(int j=0; j < menu_listt.size(); j++){
						if(composite_product_list.get(ji).getName().equals(menu_listt.get(j).getName() ) ){
							menu_listt.get(j).setName(menuit2.getName() );
							menu_listt.get(j).setPrice(menuit2.getPrice() );
						}
					}
					serial_restaur.serialize(menu_listt);
				}
				ji++;
			}
		}
		for(Composite_Product current_cp : composite_product_list){
			boolean okay = false;
			for(Menu_Item current_menuit : current_cp.getMeniuri() ) {
				if(current_menuit.getName().equals(menuit1.getName() )|| 
						current_menuit.getPrice() == menuit1.getPrice() ) {
					okay = true;
					current_menuit.setName(menuit2.getName() );
					current_menuit.setPrice(menuit2.getPrice() );
				}
			}
			current_cp.setPrice(current_cp.compute_price() );
			if(okay == true){
				for(Menu_Item current_menuit2 : menu_listt){
					if(current_menuit2.getName().equals(current_cp.getName() ) ){
						current_menuit2.setName(current_cp.getName() );
						current_menuit2.setPrice(current_cp.getPrice() );
					}	
				}
			}
		}
		serial_restaur.serialize(menu_listt);
		well_formed();
	}

	/***
	 * metoda pentru stergerea unui meniu
	 */
	@Override
	public void remove_menu(Menu_Item menuit) {
		if(menuit.getType() == 'b' ) {
			Iterator<Base_Product> iterate1 = base_product_list.iterator();
			while( iterate1.hasNext() ) {
				if( iterate1.next().getName().equals(menuit.getName() ) ) {
					iterate1.remove();
				}
			}
		}
		else if(menuit.getType() == 'c' ) {
			Iterator<Base_Product> iterate2 = base_product_list.iterator();
			while( iterate2.hasNext() ) {
				if( iterate2.next().getName().equals(menuit.getName() ) ) {
					iterate2.remove();
				}
			}
		}
		for(int i=0; i < menu_listt.size(); i++){
			if(menu_listt.get(i).getType() == 'c' ){
				for(int j=0; j < composite_product_list.size(); j++){
					for(Menu_Item current_menuit3 : composite_product_list.get(j).getMeniuri() ){
						if(current_menuit3.getName().equals(menuit.getName() ) ) {
							composite_product_list.remove(j);
							menu_listt.remove(i);
						}
					}
				}
			}
		}
			
		Iterator<Menu_Item> iterate = menu_listt.iterator();
		while(iterate.hasNext() ) {
			Menu_Item menu_itt4 = iterate.next();
			if(menu_itt4.getName().equals(menuit.getName() ) ) {
				iterate.remove();
			}
		}
		serial_restaur.serialize( menu_listt );
		well_formed();
	}
	
	/***
	 * metoda pentru instantierea Administrator_GUI
	 * @param e
	 */
	private void guiAdministrator(ActionEvent e){
		new Administrator_GUI(this);
	}
	
	/***
	 * metoda pentru instantierea Waiter_GUI
	 * @param e
	 */
	private void guiWaiter(ActionEvent e) {
		new Waiter_GUI(this);
	}

	/***
	 * metoda pentru instantierea Chef_GUI
	 * @param e
	 */
	private void guiChef(ActionEvent e) {
		new Chef_GUI(this);
	}
	
	/***
	 * metoda well formed
	 */
	public  void well_formed(){
		restaurant.setVisible(false);
		administrator.setBounds(30, 0, 40, 40);
		waiter.setBounds(70, 0, 40, 40);
		chef.setBounds(110, 0, 40, 40);
		table_def.setRowCount(0);
		table_def.setColumnIdentifiers(line_model);
		String type_string = new String();
		for(Menu_Item current_menuit : menu_listt){
			
			if(current_menuit.getType() == 'b') {
				type_string = "Base_Product";
			}
			else if( current_menuit.getType() == 'c' ) {
				type_string = "Composite_Product";
			}
			table_def.addRow(new Object[] {current_menuit.getName(), 
					current_menuit.getPrice(), current_menuit.getType() } );
		}
		data.setModel( table_def );
		data.setBounds(20, 30, 10, 10);
		panou1.add(administrator);
		panou1.add(waiter);
		panou1.add(chef);
		jscroll.setViewportView(data);
		panou1.add(jscroll);
		restaurant.add(panou1);
		
		administrator.addActionListener( e-> {
			guiAdministrator(e);
        } );
		
		waiter.addActionListener( e-> {
			guiWaiter(e);
        } );
		
		chef.addActionListener( e-> {
			guiChef(e);
        } );
		
		restaurant.setVisible(true);
	}
	
	/***
	 * metoda pentru crearea unei comenzi
	 */
	@Override
	public void create_order() {
	}
	
	/***
	 * metoda pentru calcularea pretului
	 */
	@Override
	public float computee_pricee_interface(LinkedList<Menu_Item> menuit_list) {
		float price = 0;
		for(Menu_Item current_menuu : menuit_list){
			price = price + current_menuu.getPrice();
		}
		return price;
	}

	/***
	 * metoda pentru generarea unei facturi
	 */
	@Override
	public void generate_bill(Menu_Item menuit) {
	}
	
	/***
	 * getter pentru lista de comenzi
	 * @return orderr
	 */
	public LinkedHashMap<Order, LinkedList<Menu_Item>> getOrderr() {
		return orderr;
	}

	/***
	 * setter pentru lista de comenzi
	 * @param orderr
	 */
	public void setOrderr(LinkedHashMap<Order, LinkedList<Menu_Item>> orderr) {
		this.orderr = orderr;
	}

	/***
	 * getter pentru data comenzii
	 * @return
	 */
	public JTable getData() {
		return data;
	}
	
	/***
	 * setter pentru data comenzii
	 * @param data
	 */
	public void setData(JTable data) {
		this.data = data;
	}
	
	
	
}
