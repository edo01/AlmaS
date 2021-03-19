package fractioncollection;

import frazione.Frazione;

/**
 * Classe di Test per il tipo di dato astratto (FractionCollection)
 * 
 * @author Nicolo Valdiserri
 * @version 1.0 03/03/2021
 */
public class FractionCollectionTests {
	public static void main(String[] args) {
		// testMyFractionCollectionFrazioneArray
		Frazione f1 = new Frazione(1, 2);
		Frazione f2 = new Frazione(2, 3);
		Frazione[] fa = new Frazione[] { f1, f2 };
		// act
		FractionCollection c = new FractionCollection(fa);
		// assert
		assert(2==c.getSize());
		assert(f1.equals(c.getFrazione(0))==true);
		assert(f2.equals(c.getFrazione(1))==true);
	
		// testMyFractionCollectionInt
		c = new FractionCollection(5);
		// assert
		assert(0==c.getSize());

		// testMyFractionCollection
		c = new FractionCollection();
		// assert
		assert(0==c.getSize());

		// testPut
		f1 = new Frazione(1, 2);
		c = new FractionCollection();
		c.put(f1);
		// assert
		assert(1==c.getSize());
		assert(f1.equals(c.getFrazione(0))==true);

		// testPutWithRegetSize
		f1 = new Frazione(1, 1);
		f2 = new Frazione(1, 2);
		Frazione f3 = new Frazione(1, 3);
		Frazione f4 = new Frazione(1, 4);
		Frazione f5 = new Frazione(1, 5);
		c = new FractionCollection(3);
		c.put(f1);
		c.put(f2);
		c.put(f3);
		c.put(f4); // needs regetSize
		c.put(f5);
		// assert
		assert(5==c.getSize());
		assert(f1.equals(c.getFrazione(0))==true);
		assert(f2.equals(c.getFrazione(1))==true);
		assert(f3.equals(c.getFrazione(2))==true);
		assert(f4.equals(c.getFrazione(3))==true);
		assert(f5.equals(c.getFrazione(4))==true);
		
		// testRemove
		f1 = new Frazione(1, 1);
		f2 = new Frazione(1, 2);
		f3 = new Frazione(1, 3);
		f4 = new Frazione(1, 4);
		f5 = new Frazione(1, 5);
		c = new FractionCollection();
		c.put(f1);
		c.put(f2);
		c.put(f3);
		c.put(f4);
		c.put(f5);
		c.remove(4);
		// assert
		assert(4==c.getSize());
		assert(f1.equals(c.getFrazione(0))==true);
		assert(f2.equals(c.getFrazione(1))==true);
		assert(f3.equals(c.getFrazione(2))==true);
		assert(f4.equals(c.getFrazione(3))==true);
		
		// testSum
		FractionCollection c1 = new FractionCollection();
		c1.put(new Frazione(1, 1));
		c1.put(new Frazione(1, 2));
		c1.put(new Frazione(1, 3));
		c1.put(new Frazione(1, 4));
		c1.put(new Frazione(1, 5));
		FractionCollection c2 = new FractionCollection();
		c2.put(new Frazione(1, 1));
		c2.put(new Frazione(1, 2));
		c2.put(new Frazione(1, 3));
		c2.put(new Frazione(1, 4));
		c2.put(new Frazione(1, 5));
		FractionCollection res = c1.sum(c2);
		// assert
		assert(5==res.getSize());
		assert(new Frazione(2).equals(res.getFrazione(0))==true);
		assert(new Frazione(1).equals(res.getFrazione(1))==true);
		assert(new Frazione(2, 3).equals(res.getFrazione(2))==true);
		assert(new Frazione(1, 2).equals(res.getFrazione(3))==true);
		assert(new Frazione(2, 5).equals(res.getFrazione(4))==true);
	}
}