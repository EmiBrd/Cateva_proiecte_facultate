package start;


import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Bussiness_Layer.Order;
import Bussiness_Layer.Restaurant;
import Presentation_Layer.*;


public class Mainn {
 
	public static void main(String[] args) {
		
		/*
		System.out.println("Hello");
		new Restaurant("restaurant.ser"); 
		*/
		
		
		System.out.println("Hi");
		if(args[0].length()>0) {
			new Restaurant( args[0] );
		}
		
		
	} 

}
