package Bussiness_Layer;

import java.io.Serializable;

public class Menu_Item implements Serializable{

	private String name;
	private float price;
	private char type;
	
	/***
	 * constructor fara parametru
	 */
	public Menu_Item(){
	}
	
	/***
	 * constructor cu 3 parametrii
	 * @param name
	 * @param price
	 * @param type_bool
	 */
	public Menu_Item(String name, float price, char type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	
	/***
	 * constructor cu 2 parametrii
	 * @param name
	 * @param price
	 */
	public Menu_Item(String name, float price) {
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
	 * @param name
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
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	


}
