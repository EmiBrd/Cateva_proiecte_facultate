package Data_Layer;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import Bussiness_Layer.*;



public class Restaurant_Serializator {
	
	private String path;
	
	/***
	 * constructor cu 1 parametru
	 * @param filePath
	 */
	public Restaurant_Serializator(String filePath) {
		this.path = filePath;
	}
	
	/***
	 * metoda pentru serializare
	 * @param obj
	 */
	public void serialize(LinkedList<Menu_Item> obj) {
		try {
			FileOutputStream fileIn = new FileOutputStream(this.path);
			ObjectOutputStream objIn = new ObjectOutputStream(fileIn);
			objIn.writeObject(obj);
			objIn.close();
			fileIn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/***
	 * metoda pentru deserializare
	 * @return
	 */
	public LinkedList<Menu_Item> deserialize() {
		LinkedList<Menu_Item> myObj = null;
		try {
			FileInputStream fileOut = new FileInputStream(this.path);
			ObjectInputStream objOut = new ObjectInputStream(fileOut);
			myObj = (LinkedList<Menu_Item>)objOut.readObject();
			objOut.close();
			fileOut.close();
			return myObj;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myObj;
	}

	
}




