package matrici;

//importo la libreria per catturare gli errori
import matrixcatcherrors.MatrixException;

/**
* ADT Matrix
* 
* N.B i test non sono stati implementati con le eccezioni java
* poichè ancora un argomento non affrontato a lezione.
* Sono previste delle catture errori rudimentali.
* 
* @author Nicolo Valdiserri
* @version 1.0 12/03/2021
*/
public class Matrix {

	private double[][] a;
	
	/**
	 * Costruttore pubblico, costruisce una nuova matrice partendo
	 * dai valori che la matrice dovrà contenere
	 * @param a
	 */
	public Matrix(double[][] a) {
		this.a = a.clone();
	}
	
	/**
	 * Costruttore privato ausiliario che costruisce una matrice vuota,
	 * riservato ai metodi interni alla classe
	 * @param rows
	 * @param cols
	 */
	private Matrix(int rows, int cols) {
		this.a = new double[rows][cols];
		
		for(int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = 0;
			}
		}
	}
	
	/**
	 * Getter righe matrice
	 * @return int: numero di righe
	 */
	public int getRows() {
		return this.a.length;
	}
	
	/**
	 * Getter colonne matrice
	 * @return int: numero di colonne
	 */
	public int getCols() {
		return this.a[0].length;
	}

	/**
	 * Getter valore della matrice con
	 * indici passati come parametro
	 * @return double: valore alla posizione i-esima riga e j-esima colonna
	 */
	public double getValue(int row, int col) {
		//controllo
		if(MatrixException.outOfBoundException(row, this.getRows(), col, this.getCols())) return Double.NaN;
		return this.a[row][col];
	}
	
	/**
	 * Setter privato del valore della matrice
	 * presso la posizione denotata dagli indici
	 * @param row
	 * @param col
	 * @param value
	 */
	private void setValue(int row, int col, double value) {
		//controllo
		if(!MatrixException.outOfBoundException(row, this.getRows(), col, this.getCols())) this.a[row][col] = value;
	}
	
	/**
	 * Conrollo della dimensione, verifica se la matrice è nxn
	 * @return boolean: true se la matrice è quadrata, falso altrimenti
	 */
	public boolean isSquared() {
		return this.getRows() == this.getCols();
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i<this.getRows(); i++) {
			for(int j = 0; j<this.getCols(); j++) {
				s += String.format("\t%f",this.getValue(i,j));
			}
			 if(i<this.getRows()-1) s+="\n";
		}
		return s;
	}
	
	/**
	 * Somma la matrice con un’altra (passata come parametro) e
	 * restituisce una nuova matrice che rappresenta il risultato
	 * @param b
	 * @return Matrix: matrice somma
	 */
	public Matrix sum(Matrix b) {
		//controllo
		if(MatrixException.checkSum(this.getRows(), this.getCols(), b.getRows(), b.getCols())) return null;
		
		Matrix result = new Matrix(this.getRows(), this.getCols());
		
		for (int i=0; i < this.getRows(); i++) {
			for (int j=0; j < this.getCols(); j++) {
				result.setValue(i, j, this.getValue(i, j)+ b.getValue(i, j));
			}
		}
		
		return result;
	}
	
	/**
	 * Moltiplica la matrice con un’altra (passata come parametro) e
	 * restituisce una nuova matrice che rappresenta il risultato
	 * @param b
	 * @return Matrix: matrice prodotto
	 */
	public Matrix mul(Matrix b) {
		//controllo
		if(MatrixException.checkMul(this.getCols(), b.getRows())) return null;
		
		Matrix result = new Matrix(this.getRows(), b.getCols());
		
		for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                double parzial = 0;
            	for(int k = 0; k < b.getRows(); k++) {
                    parzial += this.getValue(i, k)*b.getValue(k, j);
                }
            	result.setValue(i, j, parzial);
            }
        }
		
		return result;
	}
	
	/**
	 * Estrae una sottomatrice della matrice su cui viene invocato tale metodo.
	 * @param startRow indice di riga da cui partire per l’estrazione della sottomatrice
	 * @param startCol indice di colonna da cui partire per l’estrazione della sottomatrice
	 * @param rowCount numero di righe che comporranno la sottomatrice
	 * @param colCount numero di colonne che comporranno la sottomatrice
	 * @return Matrix: la sottomatrice
	 */
	public Matrix extractSubMatrix(int startRow, int startCol, int rowCount, int colCount) {
		//controlli
		if(MatrixException.outOfIndexException(startRow, this.getRows(), startCol, this.getCols())) return null;
		if(MatrixException.isDegenerate(rowCount, colCount)) return null;
		if(MatrixException.outofBoundMatrix(rowCount, this.getRows()-startRow, colCount, this.getCols()-startCol)) return null;

		Matrix result = new Matrix(rowCount, colCount);
		
		for (int i = startRow; i < startRow+rowCount; i++) {
			for (int j = startCol; j < startCol+colCount; j++) {
				result.setValue(i-startRow, j-startCol, this.getValue(i, j));
			}
		}
		
		return result;
	}
	
	/**
	 * Estrae il minore, quindi una sottomatrice quadrata della matrice su
	 * cui viene invocato tale metodo
	 * @param row riga da eliminare
	 * @param col colonna da eliminare
	 * @return Matrix: il minore
	 */
	public Matrix extractMinor(int row, int col) {
		//controlli
		if(MatrixException.outOfIndexException(row, this.getRows(), col, this.getCols())) return null;
		if(!this.isSquared()) return null;
		
		Matrix result = new Matrix(this.getRows()-1, this.getCols()-1);
		int r=0,c=0;
		
		for(int i = 0; i<this.getRows(); i++) {
			if(i!= row) {
				for(int j = 0; j<this.getRows(); j++) {
					if(j != col) {
						result.setValue(r, c, this.getValue(i, j));
						c++;
					}
				}
				r++; c=0;
			}
			
		}
		return result;
	}
	
	/**
	 * Metodo di controllo preliminare al calcolo del determinante
	 * @return double (il valore del determinante se la matrice è nxn)
	 */
	public double det() {
		//controllo
		return isSquared() ? calcDet() : Double.NaN;
	}
	
	/**
	 * Calcola il determinante mediante la formula di LaPlace,
	 * il metodo è stato implementato ricorsivamente
	 * @return double: il valore del determinante calcolato
	 */
	public double calcDet() {
		if(this.getCols()==2)
			return this.getValue(0, 0)*this.getValue(1, 1)-this.getValue(1, 0)*this.getValue(0, 1); 
		else {
			double result = 0;
			for(int i=0; i<this.getCols(); i++) {
				if(i%2 == 0) result += this.getValue(0, i)*this.extractMinor(0,i).det();
				else result -= this.getValue(0, i)*this.extractMinor(0,i).det();
			}
			return result;
		}
	}
}