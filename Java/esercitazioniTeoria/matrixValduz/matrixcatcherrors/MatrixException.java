package matrixcatcherrors;

/**
* Classe contenente delle eccezioni rudimentali per cogliere
* le inconsistenze nelle varie parti del codice.
* Non vengono utilizzate le eccezioni Java poichè non sono
* ancora state affrontate a lezione.
* 
* @author Nicolo Valdiserri
* @version 1.0 12/03/2021
*/
public class MatrixException {
	/**
	 * Verifica che gli indici di riga e colonna passati come
	 * parametro non eccedano oltre la dimensione della matrice
	 * @param row
	 * @param limitRow
	 * @param col
	 * @param limitCol
	 * @return true se almeno una delle espressioni è verificata, false altrimenti
	 */
	public static boolean outOfBoundException(int row, int limitRow, int col, int limitCol) {
        return row > limitRow || col > limitCol;
	}
	
	/**
	 * Verifica che gli indici di riga e colonna passati come
	 * parametro non siano uguali e che non eccedano oltre la dimensione della matriceù
	 * @param row
	 * @param limitRow
	 * @param col
	 * @param limitCol
	 * @return true se almeno una delle espressioni è verificata, false altrimenti
	 */
	public static boolean outOfIndexException(int row, int limitRow, int col, int limitCol) {
        return row >= limitRow || col >= limitCol;
	}
 
	/**
	 * Verifica che le due matrici da sommare rispettino la condizione di
	 * possedere lo stesso numero di righe e colonne
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @return true se almeno una delle espressioni è verificata, false altrimenti
	 */
	public static boolean checkSum(int row1, int col1, int row2, int col2) {
        return row1!=row2 || col1!=col2;
	}
	
	/**
	 * Verifica che il numero di colonne della prima matrice sia pari
	 * al numero di righe della seconda matrice
	 * @param row1
	 * @param col2
	 * @return true se la condizione necessaria del prodotto matriciale
	 * 				risulta non verificata, false altrimenti
	 */
	public static boolean checkMul(int col1, int row2) {
        return col1!=row2;
	}
	
	/**
	 * Verifica che la matrice non sia degenere, ossia che non venga passato
	 * come parametro nel metodo per l'estrazione della sotto-matrice un valore
	 * di numero righe o colonne pari a 0
	 * @param row
	 * @param col
	 * @return true se almeno una delle espressioni è verificata, false altrimenti
	 */
	public static boolean isDegenerate(int row, int col) {
        return row==0 || col==0;
	}
	
	/**
	 * Verifica che il numero di righe e colonne della sotto-matrice passate come
	 * parametro non ecceda rispetto allo punto di startRow e startColum e alla
	 * dimensione della matrice di partenza
	 * @param rowCount
	 * @param limitRow
	 * @param colCount
	 * @param limitCol
	 * @return true se almeno una delle espressioni è verificata, false altrimenti
	 */
	public static boolean outofBoundMatrix(int rowCount, int limitRow, int colCount, int limitCol) {
        return rowCount > limitRow || colCount > limitCol;
	}
}