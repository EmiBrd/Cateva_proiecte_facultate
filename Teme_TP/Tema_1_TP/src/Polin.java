

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polin extends Monom {
	private ArrayList <Monom> cat, rest = new ArrayList<>();
	private String pppolin;
	private ArrayList <Monom> polin1 = new ArrayList<>();
	private ArrayList <Monom> polin2 = new ArrayList<>();
	public Polin() {
		super();
	}
	Polin( ArrayList<Monom> polin1 ){
		this.polin1 = polin1;
	}
	public String getPolin_String() {
		return polin1.toString();
	}
	public String getPolin_String2() {
		return polin2.toString();
	}
	public void setPolin_String(String pppolin) {
		this.pppolin = pppolin;
	}
	
	ArrayList<Monom> Desparte_Regex(String ppp){
		ArrayList <Monom> ppppp = new ArrayList<>();
		Pattern p = Pattern.compile( "(-?\\b\\d+)[xX]\\^(-?\\d+\\b)" );
		Matcher m = p.matcher( ppp );
		while (m.find()) {
		    double coef =  Double.parseDouble(String.valueOf( m.group(1) ) ); 
		    int grad = Integer.parseInt(String.valueOf( m.group(2) ) ) ;
		    ppppp.add(new Monom( coef, grad ) );
		}
		return ppppp;
	}
	
	public ArrayList<Monom> Suma(String p1, String p2, JTextField t) {
		ArrayList <Monom> suma = new ArrayList<>();
		polin1 = Desparte_Regex(p1);
		polin2 = Desparte_Regex(p2);
		Collections.sort(polin1, new Compara_Grad() );
		Collections.sort(polin2, new Compara_Grad() );
		double coef=0.0;
		int grad = 0;
		for (Monom ii : polin1) {
			for (Monom jj : polin2) {
				if( ii.getGrad() == jj.getGrad() ) {
					coef = ii.getCoef() + jj.getCoef();
					grad = ii.getGrad();
					ii.setGrad(-123);
					jj.setGrad(-123);
					suma.add( new Monom(coef, grad) );
				}
			}
		}
		for (Monom iii : polin1) {
			if( iii.getGrad()>=0 ) {
				suma.add(iii);
			}
		}
		for (Monom jjj : polin2) {
			if(jjj.getGrad()>=0) {
				suma.add(jjj);
			}
		}
		String poll= "";
		Collections.sort(suma, new Compara_Grad() );
		for (Monom iiii : suma) {
			if( iiii.getCoef()!=0.0 ) {
				if(iiii.getCoef()>0) {
					poll+= "+";
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
				}
				else
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
			}
		}	
		int nr=0;
		for (Monom iiii : suma) {
			if(iiii.getCoef()!=0)
				nr++;
			if(nr==0 ) {
				poll = "0";
			}
		}
		t.setText(poll);
		
		return suma;
	}
	
	ArrayList<Monom> Scade(String p1, String p2, JTextField t) {
		ArrayList <Monom> scade = new ArrayList<>();
		polin1 = Desparte_Regex(p1);
		polin2 = Desparte_Regex(p2);
		Collections.sort(polin1, new Compara_Grad() );
		Collections.sort(polin2, new Compara_Grad() );
		double coef=0.0;
		int grad = 0;
		for (Monom ii : polin1) {
			for (Monom jj : polin2) {
				if( ii.getGrad() == jj.getGrad() ) {
					coef = ii.getCoef() - jj.getCoef();
					grad = ii.getGrad();
					ii.setGrad(-5);
					jj.setGrad(-5);
					scade.add( new Monom(coef, grad) );
				}
			}
		}
		for (Monom iii : polin1) {
			if( iii.getGrad()>=0.0 ) {
				scade.add(iii);
			}
		}
		for (Monom jjj : polin2) {
			if(jjj.getGrad()>=0.0) {
				double x = jjj.getCoef();
				x = -x;
				jjj.setCoef(x);
				scade.add(jjj);
			}
		}
		String poll= "";
		Collections.sort(scade, new Compara_Grad() );
		for (Monom iiii : scade) {
			if( iiii.getCoef()!=0.0 ) {
				if(iiii.getCoef()>0) {
					poll+= "+";
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
				}
				else
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
			}
		}	
		int nr=0;
		for (Monom iiii : scade) {
			if(iiii.getCoef()!=0)
				nr++;
			if(nr==0 ) {
				poll = "0";
			}
		}
		t.setText(poll);
		return scade;
	}
	
	ArrayList<Monom> Multiply(String p1, String p2, JTextField t) {
		ArrayList <Monom> multip = new ArrayList <>();
		int grad=0, power1=0;
		double coef=0.0;
		polin1 = Desparte_Regex(p1);
		polin2 = Desparte_Regex(p2);
		Collections.sort(polin1, new Compara_Grad() );
		Collections.sort(polin2, new Compara_Grad() );
		for (Monom i : polin1) {
			for (Monom j : polin2) {
				coef = i.getCoef() * j.getCoef();
				grad = i.getGrad() + j.getGrad();
				multip.add( new Monom(coef, grad) );
			}
		}
		for (Monom ii : multip) {
			int power2 = 0;
			for (Monom jj : multip) {
				if( (ii.getGrad()==jj.getGrad() ) && ( power1 != power2 ) ) {
					ii.setCoef( ii.getCoef()+jj.getCoef() );
					jj.setCoef(0);
				}
				power2++;
			}
			power1++;
		}
		String poll="";
		Collections.sort(multip, new Compara_Grad() );
		for (Monom iiii : multip) {
			if( iiii.getCoef()!=0.0 ) {
				if(iiii.getCoef()>0) {
					poll+= "+";
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
				}
				else
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
			}
			t.setText(poll);
		}	
		return multip;
	}
	
	ArrayList<Monom> Deriv(String p1, JTextField t) {
		polin1 = Desparte_Regex(p1);
		Collections.sort(polin1, new Compara_Grad() );
		for (Monom i : polin1) {
			if(i.getGrad()==0) {
				i.setCoef(0);
			}
			i.setCoef( i.getCoef() * i.getGrad() );
			i.setGrad( i.getGrad()-1 );
		}
		String poll="";
		Collections.sort(polin1, new Compara_Grad() );
		for (Monom iiii : polin1) {
			if( iiii.getCoef()!=0.0 ) {
				if(iiii.getCoef()>0) {
					poll+= "+";
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
				}
				else
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
			}
			t.setText(poll);
		}	
		return polin1;
	}
	
	ArrayList<Monom> Integrate(String p1, JTextField t) {
		polin1 = Desparte_Regex(p1);
		for (Monom i1 : polin1) {
			i1.setGrad( i1.getGrad()+ 1);
			i1.setCoef( i1.getCoef()/ i1.getGrad());
		}
		String poll="";
		Collections.sort(polin1, new Compara_Grad() );
		for (Monom iiii : polin1) {
			if( iiii.getCoef()!=0.0 ) {
				if(iiii.getCoef()>0) {
					poll+= "+";
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
				}
				else
					poll+= Double.toString( iiii.getCoef() ) + "x^" + Integer.toString(iiii.getGrad());
			}
			t.setText(poll);
			//System.out.println(poll);
		}
		return polin1;
		
	}
	
	
}


