package Bussiness_Layer;


import java.util.LinkedList;


public interface IRestaurantProcessing {

	/***
	 * metoda pentru crearea meniului
	 * @param menui
	 * @param bp
	 * @param cp
	 * @param s
	 */
	public void create_menu(Menu_Item menui, Base_Product bp, Composite_Product cp);
	
	/***
	 * metoda pentru modificarea meniului
	 * @param menui1
	 * @param menui2
	 */
	public void edit_menu(Menu_Item menui1, Menu_Item menui2);
	
	/***
	 * metoda pentru stergerea meniului
	 * @param menui
	 */
	public void remove_menu(Menu_Item menui);
	
	
	/***
	 * metoda pentru crearea unei comenzi
	 */
	public void create_order();
	
	/***
	 * metoda pentru calcularea pretului meniului
	 * @param menui
	 * @return
	 */
	public float computee_pricee_interface(LinkedList<Menu_Item> menui);
	
	/***
	 * metoda pentru calcularea facturii meniului
	 * @param menui
	 */
	public void generate_bill(Menu_Item menui);
	
	
	
}
