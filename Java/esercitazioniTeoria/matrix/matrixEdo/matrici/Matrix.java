package matrici;

import java.util.Iterator;

import matrixExceptions.InconsistentDimension;

public class Matrix {

	private double[][] m;
	
	/**
	 * 
	 * @param m
	 */
	public Matrix(double[][] m) {
		this.m = m.clone();
	}
	
	/**
	 * initialize the matri
	 * @param r
	 * @param c
	 */
	public Matrix(int r, int c) {
		m = new double[r][c];
		for(int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				m[i][j] = 0;
			}
		}
	}
	
	public int getCols() {
		return m[0].length;
	}
	
	public int getRows() {
		return m.length;
	}
	
	public double getValue(int row, int col) {
		return m[row][col];
	}
	
	private double[][] getMatrix() {
		return m;
	}
	
	public Matrix mul(Matrix m2) throws InconsistentDimension {
		//controllo delle dimensioni delle due matrici
		if(this.getCols() != m2.getRows()) 
			throw new InconsistentDimension("Columns of the first matrix and rows of the second one should be consistent");
		
		
		Matrix result = new Matrix(this.getRows(),m2.getCols());
		
		for(int i = 0; i<result.getRows(); i++) {
			for(int j = 0; j < result.getCols(); j++) {
				double r = 0;
				for(int z = 0; z < m2.getRows(); z++) {// sommatoria da 0 fino al numero di righe di m2/numero di colonne m1
					r += this.getValue(i, z) * m2.getValue(z, j); 
				}
				result.setValue(i, j, r);
			}
		}
		return result;
	}

	public Matrix sum(Matrix m2) throws InconsistentDimension {
		/*
		 * Controllo delle dimensioni
		 */
		// righe diverse
		if(this.getRows() != m2.getRows()) 
			throw new InconsistentDimension("Rows should be consistent");
		// colonne diverse
		if(this.getCols() != m2.getCols()) 
			throw new InconsistentDimension("Columns should be consistent");

		// Creo la nuova matrice settando tutti i valori a zero
		Matrix result = new Matrix(this.getMatrix().length,this.getMatrix()[0].length);
		
		// Setto le singole celle della matrice
		for(int i = 0; i< result.getRows(); i++) {
			for(int j = 0; j < result.getCols(); j++) {
				result.setValue(i, j, this.getValue(i, j) + m2.getValue(i, j));
			}
		}
		return result;
	}
	
	public boolean isSquared() {
		return this.getCols()==this.getRows();
	}
	
	private void setValue(int row, int col, double value) {
		m[row][col] = value;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i<getRows(); i++) {
			for(int j = 0; j< getCols(); j++) {
				s += String.format("%f\t",getValue(i,j) );
			}
			s += "\n";
		}
		return s;
	}

	public Matrix extractSubMatrix(int startRow, int startCol, int rowCount, int colCount) throws InconsistentDimension {
		
		//controllare che le righe e le colonne di partenza non eccedano
		if(startRow >= this.getCols() || startCol >= this.getCols()) 
			throw new InconsistentDimension(String.format("indexes should remain between [%d,%d]",
					this.getRows(), this.getCols()));
		
		else if(colCount == 0 || rowCount == 0) //controllare che la matrice non sia degenere
				throw new InconsistentDimension("Matrix dimension cannot be zero");
		
		// controllare che la sottomatrice non ecceda
		else if(rowCount >(this.getRows()-startRow) ||
				colCount > (this.getCols()-startCol))
			throw new InconsistentDimension("Out of bounds");
		
		Matrix result = new Matrix(rowCount, colCount);
		
		for (int i = startRow; i < startRow+rowCount; i++) {
			for (int j = startCol; j < startCol+colCount; j++) {
				result.setValue(i-startRow, j-startCol, this.getValue(i, j));;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param vector
	 * @param index
	 * @return
	 * @throws InconsistentDimension 
	 */
	private double[] delElementFromVector(double[] vector, int index) throws InconsistentDimension {
		if(index >= vector.length) 
			throw new InconsistentDimension();
		double[] result = new double[vector.length-1];
		
		for(int i = 0; i<vector.length; i++) {
			if(i<index) result[i] = vector[i];
			else if(i>index) result[i-1] = vector[i];
		}
		return result;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 * @throws InconsistentDimension
	 */
	public Matrix extractMinor(int row, int col) throws InconsistentDimension {
		
		if(row >= this.getCols() || col >= this.getCols()) 
			throw new InconsistentDimension();
		
		else if(!this.isSquared()) throw new InconsistentDimension("The matrix must be squared");

		// creo il minore vuoto
		Matrix result = new Matrix(getRows()-1,getCols()-1);
		
		for(int i = 0; i<getRows(); i++) {
			if(i<row)
				result.getMatrix()[i] = delElementFromVector(getMatrix()[i], col);
			else if(i>row)
				result.getMatrix()[i-1] = delElementFromVector(getMatrix()[i], col);
		}
		
		return result;
	}
	
	
	private double calcDet() throws InconsistentDimension {
		if(this.getCols() == 2) //caso base--> dimensione pari a 2
			return this.getValue(0, 0) * this.getValue(1, 1) -this.getValue(1, 0)*this.getValue(0, 1); 
		else {
			double result = 0;
			for(int j = 0; j < this.getCols(); j++) {
				if(j%2 == 0) result += this.getValue(0, j)*this.extractMinor(0,j).det();
				else result -= this.getValue(0, j)*this.extractMinor(0,j).det();
			}
			return result;
		}
	}
	
	public double det() throws InconsistentDimension {
		if(!this.isSquared()) throw new InconsistentDimension("The matrix must be squared");
		return calcDet();
	}
}








