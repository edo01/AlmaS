package matrici;

/**
* Main di Test dell'ADT Matrix
* 
* @author Nicolo Valdiserri
* @version 1.0 12/03/2021
*/
public class MatrixTestMain {
	public static void main(String[] args) {
		Matrix m = new Matrix(new double[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } });
		System.out.println("\tMATRICE M:\n"+m);
		System.out.println("\tDeterminante di 'm': " + m.det());
		
		Matrix m1 = new Matrix(new double[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 0, 1 } });
		System.out.println("\n\tMATRICE M1:\n"+m1);
		System.out.println("\tDeterminante di 'm1': " + m1.det());
		
		Matrix sum_m_m1 = m.sum(m1);
		System.out.println("\n\tMATRICE SOMMA m+m1:\n"+ sum_m_m1);

		Matrix mul_m_m1 = m.mul(m1);
		System.out.println("\n\tMATRICE PRODOTTO m*m1:\n" + mul_m_m1);

		Matrix subMatrix = m.extractSubMatrix(1, 1, 2, 2);
		System.out.println("\n\tSubMatrix:\n" + subMatrix);

		Matrix minor = m1.extractMinor(2, 2);
		System.out.println("\n\tMinor:\n" + minor);
	}
}