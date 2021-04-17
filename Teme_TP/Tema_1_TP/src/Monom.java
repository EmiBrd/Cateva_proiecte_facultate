import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTextField;
import java.util.Comparator;


public class Monom {
		ArrayList<Monom> polin1 = new ArrayList<>();
		private int grad;
		private double coef;
		
		public Monom() {
			this.coef = 0;
			this.grad = 0;
		}
		public Monom (double coef, int grad) {
			this.coef = coef;
			this.grad = grad;
		}
		public int getGrad() {
			return grad;
		}
		public void setGrad(int grad) {
			this.grad = grad;
		}
		public double getCoef() {
			return coef;
		}
		public void setCoef(double coef) {
			this.coef = coef;
		}
		
		public int getGradPolin_din_Monom() {
			Collections.sort( polin1, new Compara_Grad() );
			for (Monom i : polin1) {
				if( i.getCoef() !=0 ) {
					return i.getGrad();
				}
			}
			return 0;
		}
		public double getCoefPolin_din_Monom() {
			Collections.sort( polin1, new Compara_Grad() );
			for (Monom i : polin1) {
				if( i.getCoef() !=0 ) {
					return i.getCoef();
				}
			}
			return 0.0;
		}
		public Monom getMax_Monom_din_Monom() {
			for (Monom i : polin1) {
				if(i.getCoef()!=0) {
					return i;
				}
			}
			return new Monom(0, 0);
		}
		
		public ArrayList<Monom> getPolin_din_Monom() {
			return polin1;
		}

		public void setPolin_din_Monom(ArrayList<Monom> polin1) {
			this.polin1 = polin1;
		}

}

