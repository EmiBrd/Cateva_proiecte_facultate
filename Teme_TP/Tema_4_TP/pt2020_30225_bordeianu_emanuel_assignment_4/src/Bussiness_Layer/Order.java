package Bussiness_Layer;


import java.util.Date;
import java.util.LinkedList;


public class Order {

	private int id;
	private Date date;
	private int Table;
	private LinkedList<Menu_Item> menu_list = new LinkedList<Menu_Item>();
	
	/***
	 * constructor fara parametru
	 */
	public Order(){
	}
	
	/***
	 * constructor cu 3 parametrii
	 * @param id
	 * @param date
	 * @param table
	 */
	public Order(int id, Date date, int table){
		this.id=id;
		this.date = date;
		this.Table=table;
	}
	
	/***
	 * getter pentru id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/***
	 * setter pentru id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/***
	 * getter pentru data
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	
	/***
	 * setter pentru data
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/***
	 * getter pentru tabel
	 * @return Table
	 */
	public int getTable() {
		return Table;
	}
	
	/***
	 * setter pentru tabel
	 * @param table
	 */
	public void setTable(int table) {
		Table = table;
	}
	
	/***
	 * getter pentru lista de meniuri
	 * @return menu_list
	 */
	public LinkedList<Menu_Item> getMenu_list() {
		return menu_list;
	}
	
	/***
	 * setter pentru lista de meniuri
	 * @param menu_list
	 */
	public void setMenu_list(LinkedList<Menu_Item> menu_list) {
		this.menu_list = menu_list;
	}
	
	/***
	 * metoda pentru hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 999;
		int result = 1;
		result = prime * result + Table;
		result = prime * result + id;
		return result;
	}
	
	/***
	 * metoda pentru equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (Table != other.Table)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date) )
			return false;
		if (id != other.id)
			return false;
		if (menu_list == null) {
			if (other.menu_list != null)
				return false;
		} else if (!menu_list.equals(other.menu_list) )
			return false;
		return true;
	}
	
	
	
	
	
	
}
