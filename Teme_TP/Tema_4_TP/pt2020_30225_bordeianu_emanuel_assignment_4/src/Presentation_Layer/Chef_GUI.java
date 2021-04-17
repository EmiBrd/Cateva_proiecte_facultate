package Presentation_Layer;


import Bussiness_Layer.*;
import java.util.*;
import javax.swing.*;


public class Chef_GUI implements Observer{

	private JFrame chef_frame = new JFrame("  ");
	private JTextArea order_text = new JTextArea();
	private Restaurant restaurant;
	
	/***
	 * constructor cu 1 parametru
	 * @param r
	 */
	public Chef_GUI(Restaurant r){
		restaurant = r;
		order_text.setEditable(false);
		chef_frame.add(order_text);
		chef_frame.setSize(900, 650);
	}
	
	/***
	 * metoda pentru actualizarea unue comenzi
	 */
	@Override
	public void update(Observable obs, Object obj) {
	}

	
}
 