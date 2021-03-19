package fractioncollection;

import java.util.StringJoiner;
import frazione.Frazione;

/**
 * FractionCollection come tipo di dato astratto (ADT)
 * 
 * @author Nicolo Valdiserri
 * @version 1.0 03/03/2021
 */
public class FractionCollection {	
	//attributi di classe
	private Frazione[] innerContainer;
	private int size;
	//costanti
	private final static int DEFAULT_GROWTH_FACTOR = 2;
	private final static int DEFAULT_PHYSICAL_SIZE = 10;
	
	/**
	 * Costruisce una collezione (logicamente vuota) data la dimensione fisica
	 * iniziale dell'array interno (parametro)
	 * @param int: physicalSize
	 */
	public FractionCollection(int physicalSize) {
		this.innerContainer = new Frazione[physicalSize];
		this.size = 0;
	}
	
	/**
	 * Costruisce una collezione (logicamente vuota) con dimensione fisica iniziale
	 * dell'array di default, hardcoded nel codice
	 */
	public FractionCollection() {
		this.innerContainer = new Frazione[DEFAULT_PHYSICAL_SIZE];
		this.size = 0;
	}
	
	/**
	 * Costruisce una collezione dato un array di frazioni (parametro)
	 * che sarà copiato in quello interno
	 * @param Frazione: collection
	 */
	public FractionCollection(Frazione[] collection) {
		this.innerContainer = new Frazione[collection.length];
		this.size = 0;
		for(int i = 0; i<collection.length; i++) {
			if(collection[i] != null) {
				this.innerContainer[this.size]=collection[i];
				this.size++;
			}
		}
	}
	
	/**
	 * Restituisce la dimensione logica della collezione
	 * @return int: size logica (numero di elementi effettivamente inizializzati dell'array)
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Restituisce l'elemento i-esimo (parametro) della collezione
	 * @param int: index (indice della frazione da restituire)
	 * @return Frazione
	 */
	public Frazione getFrazione(int index) {
		if(index>=0 && index<this.getSize()) return this.innerContainer[index];
		else return null;
	}
	
	/**
	 * Aggiunge un elemento in coda alla collezione e incrementa la dimensione
	 * logica; se non c'è posto: (1) crea un nuovo array grande il doppio del corrente,
	 * (2) vi copia tutte le frazioni del corrente (3) rende corrente il nuovo array
	 * @param Frazione
	 */
	public void put(Frazione f) {
		if(this.getSize()<this.innerContainer.length) {
			this.innerContainer[this.size] = f;
		}
		else {
			Frazione[] provv = new Frazione[this.innerContainer.length*DEFAULT_GROWTH_FACTOR];
			for(int i = 0; i<this.getSize(); i++) {
				provv[i]=this.innerContainer[i];
			}
			provv[this.innerContainer.length] = f;
			this.innerContainer = provv;
		}
		this.size++;
	}
	
	/**
	 * Elimina un elemento dalla posizione i-esima (parametro) della collezione e, se
	 * necessario, compatta la collezione eliminando il "buco"
	 * @param int: index (indice dell'elemento da rimuovere)
	 */
	public void remove(int index) {
		for(int i = 0; i< this.getSize()-1; i++) {
			if(i >= index) {
				this.innerContainer[i] = this.innerContainer[i+1];
			}
		}
		this.size--;
	}

	@Override
	public String toString() {
		StringJoiner res = new StringJoiner(", ", "[", "]");
		for (int i=0; i<this.getSize(); i++) {
			res.add(this.innerContainer[i].toString());
		}
		return res.toString();
	}
	
	/**
	 * Esegue la sommma con gli elementi di un'altra collezione
	 * di pari dimensione (parametro) e restituisce una nuova
	 * collezione contenente i risultati.
	 * @param FractionCollection: altro insieme di funzioni
	 * @return FractionCollection: nuovo insieme risultante
	 */
	public FractionCollection sum(FractionCollection collection) {
		if (this.getSize() != collection.getSize()) return null;
		Frazione[] provv = new Frazione[this.getSize()];
		for (int i=0; i<provv.length; i++) {
			provv[i] = this.getFrazione(i).sum(collection.getFrazione(i));
		}
		return new FractionCollection(provv);
	}
	
	/**
	 * Esegue la sottrazione con gli elementi di un'altra collezione
	 * di pari dimensione (parametro) e restituisce una nuova
	 * collezione contenente i risultati.
	 * @param FractionCollection: altro insieme di funzioni
	 * @return FractionCollection: nuovo insieme risultante
	 */
	public FractionCollection sub(FractionCollection collection) {
		if (this.getSize() != collection.getSize()) return null;
		Frazione[] provv = new Frazione[this.getSize()];
		for (int i=0; i<provv.length; i++) {
			provv[i] = this.getFrazione(i).sub(collection.getFrazione(i));
		}
		return new FractionCollection(provv);
	}
	
	/**
	 * Esegue la moltiplicazione con gli elementi di un'altra collezione
	 * di pari dimensione (parametro) e restituisce una nuova
	 * collezione contenente i risultati.
	 * @param FractionCollection: altro insieme di funzioni
	 * @return FractionCollection: nuovo insieme risultante
	 */
	public FractionCollection mul(FractionCollection collection) {
		if (this.getSize() != collection.getSize()) return null;
		Frazione[] provv = new Frazione[this.getSize()];
		for (int i=0; i<provv.length; i++) {
			provv[i] = this.getFrazione(i).mul(collection.getFrazione(i));
		}
		return new FractionCollection(provv);
	}
	
	/**
	 * Esegue la divisione con gli elementi di un'altra collezione
	 * di pari dimensione (parametro) e restituisce una nuova
	 * collezione contenente i risultati.
	 * @param FractionCollection: altro insieme di funzioni
	 * @return FractionCollection: nuovo insieme risultante
	 */
	public FractionCollection div(FractionCollection collection) {
		if (this.getSize() != collection.getSize()) return null;
		Frazione[] provv = new Frazione[this.getSize()];
		for (int i=0; i<provv.length; i++) {
			provv[i] = this.getFrazione(i).div(collection.getFrazione(i));
		}
		return new FractionCollection(provv);
	}
}