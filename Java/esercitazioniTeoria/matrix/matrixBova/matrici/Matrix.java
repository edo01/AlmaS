/*
 * DA FARE:
 * 1) "Oggettizzare" il metodo sum
 */

package matrici;

public class Matrix {
	private double[][] values;
	
	private double calcDet() {
		if(this.getRows() == 2 && this.getCols() == 2) {
			return (this.getValue(0,0) * this.getValue(1,1) - this.getValue(0, 1) * this.getValue(1,0));
		}
		double det = 0;
			for (int j = 0; j < values[0].length; j++) {
				det += this.getValue(0,j) * Math.pow(-1, (1)+(j+1)) * this.extractMinor(0, j).det();
		}
		return det;
	}
	
	public double det() {
		return isSquared() ? calcDet(): Double.NaN;
	}
	
	private Matrix removeColoumn(int col) { 
		Matrix reduced = new Matrix(this.getRows(), this.getCols()-1);
		for (int i = 0; i < this.values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				if(j < col) reduced.setValue(i, j, this.getValue(i, j));
				else if (j > col)  reduced.setValue(i, j-1, this.getValue(i, j));
			}
		}
		return reduced;
	}
	
	/*
	 * This method removes a row and then uses removeColoumn to remove the selected coloumn
	 */
	public Matrix extractMinor(int row, int col) {
		if(row > this.getRows() || col > this.getCols() || !this.isSquared()) return null;
		Matrix valMinor = new Matrix(this.getRows()-1, this.getCols());
		for (int i = 0; i < this.values.length; i++) {
			for (int j = 0; j < this.values[0].length; j++) {
				if(i < row) valMinor.setValue(i, j, this.getValue(i, j));
				else if (i > row) valMinor.setValue(i-1, j, this.getValue(i, j));
			}
		}
		Matrix reduced = valMinor.removeColoumn(col);
		return reduced;
	}
	
	public Matrix extractSubMatrix(int startRow, int startCol, int rowCount, int colCount) {
		if(this.getRows() < startRow + rowCount || this.getCols() < startCol + colCount) return null;
		Matrix subMatrix = new Matrix(this.getCols() - startRow, this.getCols() - startCol);
		int subRow = 0, subCol = 0;
		for (int i = startRow; i < startRow + rowCount; i++) {
			for (int j = startCol; j < startCol + colCount; j++) {
				subMatrix.setValue(subRow, subCol, this.getValue(i, j));
				subCol++;
			}
			subRow++;
			subCol = 0;
		}
		return subMatrix;
	}
	
	public int getCols() {
		return this.values[0].length;
	}
	
	public int getRows() {
		return this.values.length;
	}
	
	public double getValue(int row, int col) {
		return this.values[row][col];
	}
	
	public boolean isSquared() {
		if (this.values.length != this.values[0].length) return false;
		else return true;
	}
	
	private Matrix(int rows, int cols) {
		values = new double[rows][cols];
	}
	
	/*
	 * this method checks whether the double[][] defines a well formed matrix or not 
	 */
	public Matrix(double[][] values) {
		if(isRectangular(values)) this.values = values.clone();
	}
	
	public Matrix mul(Matrix m) {
		if(this.getCols() != m.getRows()) return null;
		Matrix c = new Matrix(new double[this.values.length][m.values[0].length]);
		for (int i = 0; i < c.values.length; i++) {
			for (int j = 0; j < c.values[0].length; j++) {
				double somma = 0;
				for (int k = 0; k < this.values.length; k++) {
					somma += this.values[i][k] * m.values[k][j];
				}
				c.setValue(i, j, somma);
			}
		}
		return c;
	}
	
	private void setValue(int row, int col, double value) {
		this.values[row][col] = value;
	}
	
	public Matrix sum(Matrix m) {
		if(m.getCols() != this.getCols() || m.getRows() != this.getRows()) return null;
		double[][] c = new double[this.values.length][this.values[0].length];
		for (int i = 0; i < this.values.length; i++) {
			for (int j = 0; j < this.values[0].length; j++) {
				c[i][j] = this.values[i][j] + m.values[i][j];
			}
		}
		return new Matrix(c);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				s += this.values[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
	
	private boolean isRectangular(double[][] values) {
		for (int i = 0; i < values.length; i++) {
			if(values[0].length != values[i].length) return false;
		}
		return true;
	}
}
