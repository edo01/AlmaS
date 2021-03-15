package libreriastatica;

/**
* Main di Test della libreria statica MatriceLib
* 
* @author Nicolo Valdiserri
* @version 1.0 12/03/2021
*/
public class MatriceLibTestMain {
	public static void main(String[] args) {
		double[][] m = {{ 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		double[][] n = {{ 2, 1, 1 }, { 1, 1, 0 },{ 0, 0, 1 } };    
		double[][] q = MatriceLib.sommaMatrici(m,n);
	
		System.out.println("SOMMA");
		MatriceLib.stampaMatrice(q);
	  
		m = new double[][] {{ 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		n  = new double[][]{{ 2, 1, 1 }, { 1, 1, 0 }, { 0, 0, 1 } };    
		q = MatriceLib.prodottoMatrici(m,n);	
     
		System.out.println("\nPRODOTTO");
		MatriceLib.stampaMatrice(q);
	}
}