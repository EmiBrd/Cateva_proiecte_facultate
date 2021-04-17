package Presentation_Layer;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Bussiness_Layer.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class Waiter_GUI extends JFrame implements IRestaurantProcessing{
	
	private Restaurant restaurant;
	private LinkedHashMap<Order,LinkedList<Menu_Item>> orders;

	private JTable data_menu;
	private JTable orders_table;
	private DefaultTableModel table_model;
	private FileWriter file_wr;

	/***
	 * constructor cu 1 parametru
	 * @param r
	 */
	public Waiter_GUI(Restaurant r){
		restaurant = r;
		orders_table = new JTable();
		table_model = new DefaultTableModel();
		String[] line = {"ID", "Data", "Number_of_table", "Price"};
		table_model.setColumnIdentifiers(line);
		data_menu = restaurant.getData();
		orders = restaurant.getOrderr();
		
		Container con = new Container();
		con.setLayout( new GridLayout(3, 1) );
		
		JButton order_button = new JButton("Add_new_order");
		JButton generate_bill_button = new JButton("Generate_bill");
		JButton list_orders_button = new JButton("List_orders");
		
		JPanel panou1 = new JPanel(); 
		order_button.setBounds(30, 0, 40, 40);
		generate_bill_button.setBounds(70, 0, 40, 40);
		list_orders_button.setBounds(110, 0, 40, 40);
		
		order_button.setVisible(true);
		
		order_button.addActionListener( e->{
			create_order();
		} );
		
		generate_bill_button.addActionListener( e->{
			generate_bill(new Menu_Item());
		} );
		
		list_orders_button.addActionListener( e->{
			JFrame orderss = new JFrame("  ");
			JScrollPane jsp = new JScrollPane();
			orders_table.setModel(table_model);
			Set<Order> o = orders.keySet();
			for(Order restaurant : o){
				LinkedList<Menu_Item> m = orders.get(restaurant);
				float pret = computee_pricee_interface(m);
				table_model.addRow(new Object[] {restaurant.getId(), 
						restaurant.getDate(), restaurant.getTable(), pret} );
			}
			
			jsp.setViewportView(orders_table);
			orderss.add(jsp);
			orderss.setSize(700, 500);
			orderss.setVisible(true);
		} );
		
		panou1.add(order_button);
		panou1.add(generate_bill_button);
		panou1.add(list_orders_button);
		add(panou1);
		setTitle("Waiter");
		setSize(700, 500);
		setVisible(true);
	}

	/***
	 * metoda pentru crearea unei comenzi
	 */
	@Override
	public void create_order() {
		JFrame orderr = new JFrame("  ");
		JPanel panou1 = new JPanel();
		JTextField text_fld = new JTextField(35);
		JButton add_button = new JButton("Add_order");
		orderr.setLayout(new FlowLayout() );
		panou1.add( new JLabel("Introduce the number of table:") );
		panou1.add(text_fld);
		panou1.add(add_button);
		orderr.add(panou1);
		
		add_button.addActionListener(e->{
			try {
				int table_number = Integer.parseInt(text_fld.getText() );
				
					boolean okay = false;
					for(Order current_order : orders.keySet() ){
						if(table_number == current_order.getTable() ){
							okay = true;
							choose_menu(current_order, okay);
						}
					}
					if(okay == false){
						choose_menu( new Order(orders.size()+1, new Date(), table_number), okay);
					}
					orderr.dispose();	
				
			}				
						
			catch(Exception e1){
				JOptionPane.showMessageDialog(null,"Please, introduce correct data",
					"Err",JOptionPane.ERROR_MESSAGE);
			}
		});
		orderr.setSize(800, 400);
		orderr.setVisible(true);
	}
	
	/***
	 * metoda pentru generarea unei facturi
	 */
	@Override
	public void generate_bill(Menu_Item menuit) {
		JFrame genearete_bill_frame = new JFrame("Generate_bill");
		JScrollPane jscroll = new JScrollPane();
		orders_table.setModel(table_model);
		JPanel panou1 = new JPanel();
		JButton calcul = new JButton("Compute_bill");

		for(Order current_order : orders.keySet() ){
			float pret = computee_pricee_interface( orders.get(current_order) );
			table_model.addRow(new Object[] {current_order.getId(),
					current_order.getDate(), current_order.getTable(), pret} );
		}
		
		jscroll.setViewportView(orders_table);
		genearete_bill_frame.setLayout(new FlowLayout() );
		panou1.add(jscroll);
		panou1.add(calcul);
		genearete_bill_frame.add( panou1 );
		
		calcul.addActionListener( e-> {
				LinkedList<Menu_Item> menu_list2 = new LinkedList<Menu_Item>();
				int[] select_rows = orders_table.getSelectedRows();
				for(int i=0; i < select_rows.length; i++){
					try {
						int id_order =(int)table_model.getValueAt(select_rows[i], 0);
						Iterator<Order> interate1 = orders.keySet().iterator();
						while(interate1.hasNext() ) {
							Order orrdd = interate1.next();
							if(id_order == orrdd.getId() ){
								menu_list2 = orders.get( orrdd );
								interate1.remove();
							}
						}
						file_wr = new FileWriter("file"+(int)table_model.getValueAt(select_rows[i], 2)+
									".txt");
								
						file_wr.write("ID_table = "+id_order+"   Data = "+
								(Date)table_model.getValueAt(select_rows[i], 1)+"    ");
						
						Iterator<Menu_Item> interate2 = menu_list2.iterator();
						while( interate2.hasNext() ) {
							Menu_Item current_menu = interate2.next();
							file_wr.write("Menu_name = "+current_menu.getName()+
									"    menu_price = "+current_menu.getPrice()+"    ");
						}
						file_wr.write("Final_price = "+computee_pricee_interface(menu_list2) );
						file_wr.close();		
					} 
					
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
		});
		genearete_bill_frame.setSize(800, 550);
		genearete_bill_frame.setVisible(true);
	}
	
	/***
	 * metoda pentru calcularea pretului unui produs
	 */
	@Override
	public float computee_pricee_interface(LinkedList<Menu_Item> menuit) {
		float price = 0;
		for(Menu_Item menu_i : menuit){
			price = price + menu_i.getPrice();
		}
		return price;	
	}
	
	/***
	 * metoda pentru alegerea meniului
	 * @param order_param
	 * @param okay
	 */
	public void choose_menu(Order order_param, boolean okay){
		JFrame take_frame = new JFrame("  ");
		JScrollPane jscroll = new JScrollPane();
		
		Container con = new Container();
		con.setLayout(new GridLayout() );
		
		JButton add_button = new JButton("Add_from_table");
		DefaultTableModel table_model = (DefaultTableModel)data_menu.getModel();
		LinkedList<Menu_Item> menu_listt = new LinkedList<Menu_Item>();
		jscroll.setViewportView(data_menu);
		
		con.add(jscroll);
		con.add(add_button);
		
		add_button.addActionListener( e-> {
			int[] r = data_menu.getSelectedRows(); 
			if(okay == false){
				for(int i=0; i < r.length; i++){
					menu_listt.add( new Menu_Item((String)(table_model.getValueAt(r[i], 0)),
							(float)(table_model.getValueAt(r[i], 1)), 
							(char)(table_model.getValueAt(r[i], 2) ) ) );
				}
				orders.put(order_param, menu_listt);		
			}
			else{
				LinkedList<Menu_Item> a = orders.get( order_param );
				for(int i=0; i < r.length; i++){
					a.add( new Menu_Item((String)(table_model.getValueAt(r[i], 0)),
							(float)(table_model.getValueAt(r[i], 1)),
							(char)(table_model.getValueAt(r[i], 2) ) ) );
				}
				orders.put(order_param, a);	
			}
			restaurant.create_order();
			take_frame.dispose();
		});
			
		take_frame.add(con);
		take_frame.setSize(800, 500);
		take_frame.setVisible(true);
	}

	/***
	 * metoda pentru crearea unui meniu
	 */
	@Override
	public void create_menu(Menu_Item m,Base_Product bp,Composite_Product cp) {
	}

	/***
	 * metoda pentru editarea/modificarea unui meniu
	 */
	@Override
	public void edit_menu(Menu_Item m,Menu_Item m1) {
	}
	
	/***
	 * metoda pentru stergerea unui meniu
	 */
	@Override
	public void remove_menu(Menu_Item m) {
	}

	
}
