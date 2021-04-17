package Presentation_Layer;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import  Bussiness_Layer.*;


public class Administrator_GUI extends JFrame implements IRestaurantProcessing{

	private Restaurant restaurant;
	private Base_Product base_porduct;
	private Composite_Product composite_product;
	
	private JButton add_menu;
	private JButton edit_menu;
	private JButton remove_menu;
	
	/***
	 * constructor cu 1 parametru
	 * @param r
	 */
	public Administrator_GUI(Restaurant r){
		restaurant = r;
		base_porduct = new Base_Product();
		composite_product = new Composite_Product();
		
		Container con = new Container();
		con.setLayout( new GridLayout(3, 1) );
		
		add_menu = new JButton("Add_menu");
		edit_menu = new JButton("Edit_menu");
		remove_menu = new JButton("Remove_menu");
		
		add_menu.setBounds(10, 30, 40, 40);
		edit_menu.setBounds(10, 70, 40, 40);
		remove_menu.setBounds(10, 110, 40, 40);
		
		con.add(add_menu);
		con.add(edit_menu);
		con.add(remove_menu);
		
		setTitle("Operations_of_administrator_over_menu");
		add(con);
		
		setSize(600, 400);
		setVisible(true);
		
		add_menu.addActionListener( e ->{
			create_menu(new Menu_Item(), base_porduct, composite_product);
		} );
		
		edit_menu.addActionListener( e ->{
            	edit_menu(new Menu_Item(),new Menu_Item());
        } );
		
		remove_menu.addActionListener( e-> {
            	remove_menu(new Menu_Item());   
		} );	
	}
	
	/***
	 * metoda pentru crearea unui meniu
	 */
	@Override
	public void create_menu(Menu_Item menui, Base_Product base, Composite_Product composite) {
		JFrame frame_menu = new JFrame("Add_menu");
		frame_menu.setLayout( new BorderLayout() );
		JPanel panou1 = new JPanel();
		
		JButton base_prod=new JButton("base_product_0");
		JButton comp_prod=new JButton("composite_product_1");
		JTextField txt1 = new JTextField("");
		txt1.setSize(30, 30);
		
		JLabel nbp = new JLabel("Base_product_name");
		JTextField tbp = new JTextField(35);
		JLabel ncp = new JLabel("Composite_product_name");
		JTextField tcp = new JTextField(35);
		JLabel pbp = new JLabel("Price_base_product");
		JTextField tpbp = new JTextField(25);
		JLabel pcp = new JLabel("Price_base_product");
		JTextField tpcp = new JTextField(25);
		nbp.setBounds( 0, 30, 20, 20);
		tbp.setBounds( 25, 30, 20, 20);
		
		ncp.setBounds(0, 100, 20, 20);
		tcp.setBounds(25, 100, 20, 20);
		
		pbp.setBounds( 0, 50, 20, 20);
		tpbp.setBounds( 25, 50 , 20, 20);
		
		pcp.setBounds( 75, 30, 20, 20);
		tpcp.setBounds(0, 125, 20, 20);
	
		base_prod.addActionListener( e -> {	
			frame_menu.dispose();
			float pricee_act = (float) 0.0;
			try{
				pricee_act = Float.parseFloat(tpbp.getText() );
				 String name_act = new String(tbp.getText() );
				 restaurant.create_menu(new Menu_Item(name_act, pricee_act,
						 'b'), new Base_Product(name_act, pricee_act), 
						 new Composite_Product() );
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, "Price must have float type",
				"Err", JOptionPane.ERROR_MESSAGE);
			}	
		});
		
		comp_prod.addActionListener( e -> {	
			frame_menu.dispose();
			float pricee_act = (float) 0.0;
			try{
				pricee_act = Float.parseFloat( tpcp.getText() );
				 String name_act = new String( tcp.getText() );
				 restaurant.create_menu(new Menu_Item(name_act, pricee_act,
						 'c'), new Base_Product(name_act, pricee_act), 
						 new Composite_Product() );
			}
			
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, "Price must have float type",
				"Err", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		panou1.add(nbp);
		panou1.add(tbp);
		
		panou1.add(pbp);
		panou1.add(tpbp);
		
		panou1.add(base_prod);
		
		panou1.add(ncp);
		panou1.add(tcp);
		
		panou1.add(pcp);
		panou1.add(tpcp);
		
		panou1.add(comp_prod);
		
		frame_menu.add(panou1);
		frame_menu.setSize(600, 500);
		frame_menu.setVisible(true);		
	}
	
	/***
	 * metoda pentru modificarea unui meniu
	 */
	@Override
	public void edit_menu(Menu_Item menui1, Menu_Item menui2) {
		JFrame editt_menu = new JFrame("Edit_menu");
		JButton edit = new JButton("Edit_by_selecting_something_first");
		JTable menu_table = restaurant.getData();
		JScrollPane jsroll = new JScrollPane();
		JPanel control = new JPanel();
		DefaultTableModel table_model = (DefaultTableModel)menu_table.getModel();
		jsroll.setViewportView(menu_table);
		control.add(jsroll);
		control.add(edit);
		editt_menu.add(control);
		editt_menu.setSize(800, 600);
		editt_menu.setVisible(true);
		edit.addActionListener( e-> {			
			int select_row = menu_table.getSelectedRow();
			if( menu_table.getSelectedRowCount() == 1){
				menui1.setName( (String)(table_model.getValueAt(select_row, 0) ) );
				menui1.setPrice( (float)table_model.getValueAt(select_row, 1) );
				JFrame edit_frame = new JFrame("Edit");
				JButton save_btn = new JButton("Save_chenges");
				JTextField name_fld = new JTextField(40);
				JTextField price_fld = new JTextField(40);			
				
				edit_frame.setLayout(new FlowLayout());
				edit_frame.add(new JLabel("Name_of_product") );
				edit_frame.add(name_fld);
				edit_frame.add(new JLabel("Price_of_product") );
				edit_frame.add(price_fld);				
				edit_frame.add(save_btn);
				edit_frame.setSize(600, 500);
				edit_frame.setVisible(true);
				
				save_btn.addActionListener( f -> {
					float pricee_act = (float) 0.0;
					try	{	
						if(price_fld.getText().isEmpty()==true ){
							menui2.setPrice(menui1.getPrice() );
						}
						else {
							pricee_act = Float.parseFloat(price_fld.getText() );
							table_model.setValueAt(pricee_act, select_row, 1);
							menui2.setPrice(pricee_act);
						}			
									
						if(name_fld.getText().isEmpty()==true){
							menui2.setName(menui1.getName() );
						}
						else {
							String name_act = new String(name_fld.getText() );
							table_model.setValueAt(name_act, select_row, 0);
							menui2.setName(name_act);	 
						}
									 						 									 
						edit_frame.dispose();
						editt_menu.dispose();
						restaurant.getData().setModel(table_model);
						restaurant.setData(menu_table);									 
						restaurant.edit_menu(menui1, menui2);								 
					}
					
					catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(null, "Price must be float type",
								"Err",JOptionPane.ERROR_MESSAGE);
					}																	
				});
			}																			
		});	
	}					
	
	/***
	 * metoda pentru stergerea unui meniu
	 */
	@Override
	public void remove_menu(Menu_Item menui1) {
		JFrame restaurant_meniu = new JFrame("Remove_menu");
		JButton remove_btn = new JButton("Remove_product");
		JScrollPane jscroll = new JScrollPane();
		JPanel control = new JPanel();
		JTable meniu = restaurant.getData();
		DefaultTableModel table_model =(DefaultTableModel)meniu.getModel();
		jscroll.setViewportView(meniu);
		control.add(jscroll);
		control.add(remove_btn);
		restaurant_meniu.add(control);
		restaurant_meniu.setSize(800, 500);
		
		remove_btn.addActionListener( e-> {
				int[] select_row = meniu.getSelectedRows();
				for(int i=0; i<select_row.length; i++){
					String type_act = String.valueOf(table_model.getValueAt(select_row[i], 2) );
					char type_char = type_act.charAt(0);
					float price_act = Float.parseFloat(String.valueOf(table_model.getValueAt(select_row[i], 1) ) );
					String name_act = String.valueOf(table_model.getValueAt(select_row[i], 0) );
					table_model.removeRow(select_row[i]);						
					
					Menu_Item menui2 = new Menu_Item(name_act, price_act, type_char);
					restaurant.setData(meniu);
					restaurant.remove_menu(menui2);
					restaurant_meniu.dispose();																		
				}	
		});															
		restaurant_meniu.setVisible(true);
	}
	
	/***
	 * metoda pentru crearea unei comenzi
	 */
	@Override
	public void create_order() {
	}	
	
	/***
	 * metoda pentru calcularea pretului unui meniu
	 */
	@Override
	public float computee_pricee_interface(LinkedList<Menu_Item> menuit) {
		return (Float) null;
	}
	
	/***
	 * metoda pentru generarea facturii
	 */
	@Override
	public void generate_bill(Menu_Item menuit) {
	}
	
	
}
