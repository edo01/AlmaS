package libreriastatica;

/**
* Test della libreria statica MatriceLib
* 
* Contiene le assert per verificare le funzionalità di base
* 
* @author Nicolo Valdiserri
* @version 1.0 12/03/2021
*/
public class MatriceLibTest {
	public static void main(String[] args) {
		//somma
		double[][] m = {{ 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		double[][] n = {{ 2, 1, 1 }, { 1, 1, 0 },{ 0, 0, 1 } };    
		double[][] q = MatriceLib.sommaMatrici(m,n);
		
		// verifico la prima riga
		assert(3.0 == q[0][0]);
		assert(1.0 == q[0][1]);
		assert(1.0 == q[0][2]);
		// verifico la seconda riga
		assert(1.0 == q[1][0]);
		assert(2.0 == q[1][1]);
		assert(0.0 == q[1][2]);
		// verifico la terza riga
		assert(0.0 == q[2][0]);
		assert(0.0 == q[2][1]);
		assert(2.0 == q[2][2]);
		
		// prodotto
		q = MatriceLib.prodottoMatrici(m,n);
	 	  
		// verifico la prima riga
		assert(2.0 == q[0][0]);
		assert(1.0 == q[0][1]);
		assert(1.0 == q[0][2]);
		// verifico la seconda riga
		assert(1.0 == q[1][0]);
		assert(1.0 == q[1][1]);
		assert(0.0 == q[1][2]);
		// verifico la terza riga
		assert(0.0 == q[2][0]);
		assert(0.0 == q[2][1]);
		assert(1.0 == q[2][2]);	  
	}	
}