package frazione;

import util.MyMath;

/**
 * Frazione come tipo di dato astratto (ADT)
 * 
 * @author Nicolo Valdiserri
 * @version 1.0 17/03/2021
 */
public class Frazione {
	private int num, den;

	/**
	 * Costruttore della Frazione
	 * @param num Numeratore
	 * @param den Denominatore
	 */
	public Frazione(int num, int den) {
		boolean negativo = num * den < 0;
		this.num = negativo ? -Math.abs(num) : Math.abs(num);
		this.den = Math.abs(den);
	}

	/**
	 * Costruttore della Frazione
	 * @param num Numeratore
	 */
	public Frazione(int num) {
		this(num, 1);
	}

	/**
	 * Recupera il numeratore
	 * @return Numeratore della frazione
	 */
	public int getNum() {
		return this.num;
	}

	/**
	 * Recupera il denominatore
	 * 
	 * @return Denominatore della frazione
	 */
	public int getDen() {
		return this.den;
	}

	/**
	 * Calcola la funzione ridotta ai minimi termini.
	 * @return Una nuova funzione equivalente all'attuale,
	 * 		   ridotta ai minimi termini.
	 */
	public Frazione minTerm() {
		if (this.getNum()==0) return new Frazione(this.getNum(), this.getDen());
		int mcd = MyMath.mcd(Math.abs(this.getNum()), this.getDen());
		int n = this.getNum()/mcd;
		int d = this.getDen()/mcd;
		return new Frazione(n, d);
	}

	/**
	 * Costruttore della Frazione
	 * @param num Numeratore
	 * @param den Denominatore
	 */
	public Frazione sum(Frazione f) {
		int n = this.getNum() * f.getDen() + this.getDen() * f.getNum();
		int d = this.getDen() * f.getDen();
		return new Frazione(n, d).minTerm();
	}
	
	/**
	 * Calcola la somma con un'altra frazione (versione con mcm)
	 * @param f Frazione da sommare all'attuale
	 * @return Nuova frazione risultato della somma
	 */
	public Frazione sumWithMcm(Frazione f) {
		int mcm = MyMath.mcm(this.getDen(),f.getDen());
		int n = (mcm / this.getDen()) * this.getNum() + (mcm / f.getDen()) * f.getNum();
		return new Frazione(n, mcm).minTerm();
	}
	
	/**
	 * Calcola la sottrazione con un'altra frazione
	 * @param f Frazione da sottrarre all'attuale
	 * @return Nuova frazione risultato della sottrazione
	 */
	public Frazione sub(Frazione f) {
		int mcm = MyMath.mcm(this.getDen(),f.getDen());
		int n = (mcm / this.getDen()) * this.getNum() - (mcm / f.getDen()) * f.getNum();
		return new Frazione(n, mcm).minTerm();
	}
	
	/**
	 * Calcola la moltiplicazione con un'altra frazione
	 * @param f Frazione da moltiplicare all'attuale
	 * @return Nuova frazione risultato della moltiplicazione
	 */
	public Frazione mul(Frazione f) {
		return new Frazione(this.getNum()*f.getNum(), this.getDen()*f.getDen()).minTerm();
	}
	
	/**
	 * Calcola la divisione con un'altra frazione
	 * @param f Frazione da dividere all'attuale
	 * @return Nuova frazione risultato della divisione
	 */
	public Frazione div(Frazione f) {
		return mul(f.reciprocal()).minTerm();
	}
	
	/** 
	 * Calcola il reciroco frazione
	 * @return Nuova frazione che rappresenta il reciproco giÃ Â  ai minimi termini
	 */
	public Frazione reciprocal() {
		return new Frazione(this.getDen(), this.getNum()).minTerm();
	}
	
	/**
	 * Recupera il numero reale equivalente alla frazione
	 * @return Valore reale
	 */
	public double getDouble() {
		return (double)this.getNum() / (double)this.getDen();
	}
	
	/**
	 * Verifica se due frazioni sono equivalenti
	 * @param  f Frazione da confrontare
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equals(Frazione f) {
		return f.getNum() * this.getDen() == f.getDen() * this.getNum();
	}
	
	/**
	 * Verifica se una frazione è maggiore o minore di quella passata
	 * @param  f Frazione da confrontare
	 * @return 0 se sono uguali, 1 se la Frazione  maggiore di quella passata, -1 se è minore
	 */
	public int compareTo(Frazione f) {
		if(this.equals(f)) return 0;
		else {
			int mcm = MyMath.mcm(this.getDen(),f.getDen());
			if(((mcm / this.getDen()) * this.getNum()) > ((mcm / f.getDen()) * f.getNum())) return 1;
			else return -1;
		}
	}
	
	@Override
	public String toString() {
		return getNum() + "/" + getDen();
	}
}