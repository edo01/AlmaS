package fractioncollection;

import frazione.Frazione;

/**
 * Main per il tipo di dato astratto FractionCollection
 * 
 * @author Nicolo Valdiserri
 * @version 1.0 03/03/2021
 */
public class CollectionMain {
	public static void main(String[] args) {
	  FractionCollection collezioneA = new FractionCollection(2);
	  collezioneA.put(new Frazione(1,3));
	  collezioneA.put(new Frazione(2,3));
	  collezioneA.put(new Frazione(-1,2));
	  collezioneA.put(new Frazione(1,6));
	  
	  FractionCollection collezioneB = new FractionCollection(1);
	  collezioneB.put(new Frazione(1,5));
	  collezioneB.put(new Frazione(2,8));
	  collezioneB.put(new Frazione(1,7));
	  collezioneB.put(new Frazione(-1,6));
	  
	  FractionCollection somma = collezioneA.sum(collezioneB);
	  System.out.println(somma);
	  
	  FractionCollection prodotto = collezioneA.mul(collezioneB);
	  System.out.println(prodotto);
  }
}