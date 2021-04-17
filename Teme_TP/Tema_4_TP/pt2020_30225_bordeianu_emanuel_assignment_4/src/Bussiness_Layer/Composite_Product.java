package Bussiness_Layer;

import java.util.LinkedList;


public class Composite_Product extends Menu_Item{ 

	private String name;
	private LinkedList<Menu_Item> menu_list = new LinkedList<Menu_Item>();
	private float price;
	
	/***
	 * constructor fara parametu
	 */
	public Composite_Product(){	
	}
	
	/***
	 * constructor 2 cu 2 parametii
	 */
	public Composite_Product(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	
	/***
	 * getter pentru nume
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/***
	 * setter pentru nume
	 * @param
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/***
	 * getter pentru pret
	 * @return price
	 */
	public float getPrice() {
		return price;
	}

	/***
	 * setter pentru pret
	 * @param
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/***
	 * getter pentru lista de meniuri
	 * @return menu_list
	 */
	public LinkedList<Menu_Item> getMeniuri() {
		return menu_list;
	}

	/***
	 * setter pentru lista de meniuri
	 * @param 
	 */
	public void setMeniuri(LinkedList<Menu_Item> menu_list) {
		this.menu_list = menu_list;
	}
	
	/***
	 * metoda pentru calcularea pretului
	 * @return pricee
	 */
	public float compute_price(){
		float pricee = 0;
		for(Menu_Item mn : menu_list ){
			pricee = pricee + mn.getPrice();
		}
		return pricee;
	}

	
}
