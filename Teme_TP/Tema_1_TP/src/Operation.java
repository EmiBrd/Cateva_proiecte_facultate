
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTextField;


public class Operation extends Polin{
	
	private ArrayList <Monom> polin1 = new ArrayList<>();
	private ArrayList <Monom> polin2 = new ArrayList<>();
	public ArrayList<Monom> cat = new ArrayList<>();
	public ArrayList<Monom> rest = new ArrayList<>();
	
	public Operation() {
		super();
	}
	
	public Operation (ArrayList<Monom> polin) {
		super(polin);
	}
	
	public int getGradPolin_din_Polin( ArrayList<Monom> pp ) {
		Collections.sort( pp, new Compara_Grad() );
		for (Monom i : pp) {
			if( i.getCoef() !=0 ) {
				return i.getGrad();
			}
		}
		return 0;
	}
	public double getCoefPolin_din_Polin( ArrayList<Monom> pp ) {
		Collections.sort( pp, new Compara_Grad() );
		for (Monom i : pp) {
			if( i.getCoef() !=0 ) {
				return i.getCoef();
			}
		}
		return 0.0;
	}
	public Monom getMax_Monom_din_Polin( ArrayList<Monom> pp ) {
		for (Monom i : pp) {
			if(i.getCoef()!=0) {
				return i;
			}
		}
		return new Monom(0, 0);
	}
	
	
	ArrayList<Monom> Scade_al(ArrayList<Monom> p1, ArrayList<Monom> p2, JTextField t) {
		ArrayList <Monom> scade = new ArrayList<>();
		Collections.sort(p1, new Compara_Grad() );
		Collections.sort(p2, new Compara_Grad() );
		double coef=0.0;
		int grad = 0;
		for (Monom ii : p1) {
			for (Monom jj : p2) {
				if( ii.getGrad() == jj.getGrad() ) {
					coef = ii.getCoef() - jj.getCoef();
					grad = ii.getGrad();
					ii.setGrad(-5);
					jj.setGrad(-5);
					scade.add( new Monom(coef, grad) );
				}
			}
		}
		for (Monom iii : p1) {
			if( iii.getGrad()>=0.0 ) {
				scade.add(iii);
			}
		}
		for (Monom jjj : p2) {
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
	
	ArrayList<Monom> Multiply_al(ArrayList<Monom> p1, ArrayList<Monom> p2, JTextField t) {
		ArrayList <Monom> multip = new ArrayList <>();
		Collections.sort(p1, new Compara_Grad() );
		Collections.sort(p2, new Compara_Grad() );
		int grad=0, power1=0;
		double coef=0.0;
		for (Monom i : p1) {
			for (Monom j : p2) {
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

	void Divid(String p1, String p2, JTextField t, JTextField t1) {
		ArrayList<Monom> i, j = new ArrayList<>();
		ArrayList<Monom> cat = new ArrayList<>();
		ArrayList<Monom> rest = new ArrayList<>();
		ArrayList<Monom> divide = new ArrayList<>();
		polin1 = Desparte_Regex(p1);
		polin2 = Desparte_Regex(p2);
		Monom mon_i=new Monom(), mon_j=new Monom();
		Collections.sort( polin1, new Compara_Grad() );
		Collections.sort( polin2, new Compara_Grad() );
		i = polin1;
		j = polin2;
		Polin pol1 = new Polin(i);
		Polin pol2 = new Polin(j);
		int grad_max_i=  getGradPolin_din_Polin(i), grad_max_j= getGradPolin_din_Polin(j), nr=0;
		double coef_max_i= getCoefPolin_din_Polin(i), coef_max_j = getCoefPolin_din_Polin(j);
		if( (coef_max_j == 0) && (grad_max_j == 0) ) {
			System.out.println("Nu putem imparti la 0 !");
			t.setText("Nu putem imparti la 0 !");
		}

		else {
			while( getGradPolin_din_Polin(i) >= getGradPolin_din_Polin(j) ) {
				Monom md = getMax_Monom_din_Polin(i);
				Monom mi = getMax_Monom_din_Polin(j);
				Monom mc= new Monom(md.getCoef()/mi.getCoef(),md.getGrad()-mi.getGrad());
				System.out.println( "grad mc="+mc.getGrad() );
				ArrayList<Monom> invmc = new ArrayList<>();
				invmc.add(mc);
				ArrayList<Monom> aux = new ArrayList<>();
				aux = Multiply_al(j, invmc, t);
				System.out.println("grad aux="+getGradPolin_din_Polin(aux));
				i = Scade_al(i, aux, t);
				System.out.println("grad i="+getGradPolin_din_Polin(i));
				nr++;
				System.out.println("nr="+nr);
				cat.add(mc);
				System.out.println("coef cat="+getCoefPolin_din_Polin(cat)+"  grad cat="+getGradPolin_din_Polin(cat));
			}
			String poll="";
			Collections.sort(cat, new Compara_Grad() );
			for (Monom iiii : cat) {
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
			rest = i;
			String poll1="";
			if( getGradPolin_din_Polin(i) == 0 ) {
				poll1 = Double.toString( getCoefPolin_din_Polin(i) )+"x^"+Integer.toString( getGradPolin_din_Polin(i) );
			}
			for (Monom jjjj : rest) {
				if( jjjj.getCoef()!=0.0 ) {
					if(jjjj.getCoef()>0) {
						poll+= "+";
						poll+= Double.toString( jjjj.getCoef() ) + "x^" + Integer.toString(jjjj.getGrad());
					}
					else
						poll+= Double.toString( jjjj.getCoef() ) + "x^" + Integer.toString(jjjj.getGrad());
				}
				t1.setText(poll1);
			}
			System.out.println("cat="+poll+"  rest="+poll1);
		}
		
	}
	
}
	


