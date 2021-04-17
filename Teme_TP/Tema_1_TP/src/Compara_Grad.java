import java.util.Comparator;

class Compara_Grad implements Comparator<Monom> {
		public int compare(Monom x, Monom y) {
			return y.getGrad() - x.getGrad();
		}
}
