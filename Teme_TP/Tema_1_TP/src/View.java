
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class View {

	public static void main(String[] args) {

		JFrame frame=new JFrame("Calculare polinoame");
		frame.setSize(700, 180);
		
		frame.setLayout(new BorderLayout());
		
		JPanel panou1 = new JPanel();
		JPanel panou2 = new JPanel();
		JPanel panou3 = new JPanel();
		
		JButton b_add = new JButton("ADD");
		JButton b_sub = new JButton("SUB");
		JButton b_mul = new JButton("MUL");
		JButton b_div = new JButton("DIV");
		JButton b_deriv = new JButton("DERIV");
		JButton b_integr = new JButton("INTEGR");
		
		JTextField  txt = new JTextField("");
		JTextField  txt2 = new JTextField("");
		JTextField txt3 = new JTextField("");
		JTextField txt4=new JTextField("");
		txt.setSize(30, 150);
		txt2.setSize(30, 150);
		txt3.setSize(30, 150);
		txt4.setSize(30, 150);
		
		Polin button=new Polin();
		Operation buttonn = new Operation();
		
		
		panou1.setLayout(new GridLayout(2, 4));
		JLabel label1 = new JLabel("Polin1:", JLabel.CENTER);
		panou1.add(label1);
		panou1.add(txt);
		JLabel label2 = new JLabel("Polin2:", JLabel.CENTER);
		panou1.add(label2);
		panou1.add(txt2);
		
		//panou3.setLayout(new GridLayout(2, 2));
		JLabel label3 = new JLabel("Rezultat:", JLabel.CENTER);
		panou1.add(label3);
		panou1.add(txt3);	
		JLabel label4 = new JLabel("Rest:", JLabel.CENTER);
		panou1.add(label4);
		panou1.add(txt4);
		
		
		panou2.setLayout(new GridLayout(1,6));
		
		b_add.addActionListener(e -> 
		{
			String p1=txt.getText();
			String p2=txt2.getText();
			button.Suma(p1,p2,txt3);
		}
		);
		
		b_sub.addActionListener(e -> 
		{
			String p1=txt.getText();
			String p2=txt2.getText();
			button.Scade(p1,p2,txt3);
		}
		);
		
		b_mul.addActionListener(e -> 
		{
			String p1=txt.getText();
			String p2=txt2.getText();
			button.Multiply(p1,p2,txt3);
		}
		);
		
		b_div.addActionListener(e -> 
		{
			String p1=txt.getText();
			String p2=txt2.getText();
			buttonn.Divid(p1,p2,txt3, txt4);
		}
		);
		
		b_deriv.addActionListener(e -> 
		{
			String p1=txt.getText();
			button.Deriv(p1, txt3);
		}
		);
		
		b_integr.addActionListener(e -> 
		{
			String p1=txt.getText();
			button.Integrate(p1,txt3);
		}
		);
		
		
		panou2.add( b_add );
		panou2.add( b_sub );
		panou2.add( b_mul );
		panou2.add( b_div );
		panou2.add( b_deriv );
		panou2.add( b_integr );
		
		
		frame.add(panou1,BorderLayout.NORTH);
		frame.add(panou2,BorderLayout.CENTER);
		frame.add(panou3, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		

	}

}
