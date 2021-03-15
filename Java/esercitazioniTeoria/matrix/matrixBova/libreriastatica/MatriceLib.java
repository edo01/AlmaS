package libreriastatica;

public class MatriceLib {

	public static double[][] sommaMatrici(double[][] a, double[][] b){
		//if(!sameDim(a, b)); --> this can be a check
		double[][] c = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		return c;
	}

	/*private static boolean sameDim(double[][] a, double[][] b) {
		if(a.length != b.length) return false;
		for (int i = 0; i < a.length; i++) {
			if(a[i].length != b[i].length) return false;
		}
		return true;
	}*/
	
	public static double[][] prodottoMatrici(double[][] a, double[][] b){
		double[][] c = new double[a.length][b[0].length];
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				double somma = 0;
				for (int k = 0; k < b.length; k++) {
					somma += a[i][k] * b[k][j];
				}
				c[i][j] = somma;
			}
		}
		return c;
	}
	
	public static void stampaMatrice(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}
