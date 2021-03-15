package libreriastatica;

//importo la libreria per catturare gli errori
import matrixcatcherrors.MatrixException;

/**
* Libreria statica MatriceLib
* 
* @author Nicolo Valdiserri
* @version 1.0 12/03/2021
*/
public class MatriceLib {
	
	public static double[][] sommaMatrici(double[][] a, double[][] b) {
		if(MatrixException.checkSum(a.length, a[0].length, b.length, b[0].length)) return null;
		
		double[][] c = new double[a.length][a[0].length];
		for (int i=0; i < a.length; i++) {
			for (int j=0; j < a[0].length; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		
		return c;
	}
	
	public static double[][] prodottoMatrici(double[][] a, double[][] b) {
		if(MatrixException.checkMul(a[0].length, b.length)) return null;
		
		double[][] c = new double[a.length][a[0].length];
        
        /*
        i primi due cicli 'for' mi permettono di spazzolare righe e colonne,
        il terzo mi permette di completare l'operazione prodotto
        */
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for(int k = 0; k < b.length; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }

		return c;
	}

	public static void stampaMatrice(double[][] a) {
		for (int i=0; i < a.length; i++) {
			for (int j=0; j < a[0].length; j++) {
				System.out.print(String.format("\t%f", a[i][j]));
			}
			System.out.println("");
		}
	}
}