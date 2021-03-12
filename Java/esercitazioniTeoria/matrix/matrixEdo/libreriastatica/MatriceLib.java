package libreriastatica;

import matrixExceptions.InconsistentDimension;
import java.util.Random;

public class MatriceLib {
	
	/**
	 * 
	 * @param a first matrix
	 * @param b second matrix
	 * @return the sum of the two matrices
	 * @throws InconsistentDimension if the two matrices have different dimensions
	 */
	public static double[][] sommaMatrici(double[][] a, double[][] b) throws InconsistentDimension{
		if(a.length != b.length) throw new InconsistentDimension("Rows should be consistent");
		if(a[0].length != b[0].length) throw new InconsistentDimension("Columns should be consistent");

		double[][] result = new double[a.length][a[0].length];
		
		for(int i = 0; i<a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				result[i][j] = a[i][j] + b[i][j];
			}
		}
		return result;
	}
	
	/*
	 * a.length indicates the number of rows
	 * a[0].length indicates the number of columns 
	 */
	/**
	 * 
	 * @param a first matrix
	 * @param b second matrix
	 * @return the product of the two matrices
	 * @throws InconsistentDimension if the first matrix has a different number of rows compared to the number of columns of the second one
	 */
	public static double[][] prodottoMatrici(double[][] a, double[][] b) throws InconsistentDimension{
		if(a[0].length != b.length) throw new InconsistentDimension("Columns of the first matrix and rows of the second one should be consistent");
		
		double[][] result = new double[a.length][b[0].length];
		
		for(int i = 0; i<result.length; i++) {
			for(int j = 0; j < result[0].length; j++) {
				double r = 0;
				for(int z = 0; z < b.length; z++) {// sommatoria
					r += a[i][z] * b[z][j]; 
				}
				result[i][j] = r;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param a matrix to print
	 */
	public static void stampaMatrice(double[][] a) {
		for(int i = 0; i<a.length; i++) {
			for(int j = 0; j< a[0].length; j++) {
				System.out.print(String.format("%f\t", a[i][j]));
			}
			System.out.println("");
		}
	}
	
	public static double[][] randMatrix(int r, int c,double min, double max) {
		Random random = new Random();
		double[][] m = new double[r][c];
		
		for(int i = 0; i<m.length; i++) {
			for(int j = 0; j<m[0].length; j++) {
				m[i][j] = random.nextFloat()*(max-min) + min;
			}
		}
		
		return m;
	}
	
}
