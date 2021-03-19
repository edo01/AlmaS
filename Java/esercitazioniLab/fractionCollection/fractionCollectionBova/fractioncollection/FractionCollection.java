package fractioncollection;

import java.util.StringJoiner;

import frazione.Frazione;
public class FractionCollection {
	private static final int DEFAULT_GROWTH_FACTOR = 2;
	private static final int DEFAULT_PHYSICAL_SIZE = 10;
	private Frazione[] innerContainer;
	int size = 0;
	
	public FractionCollection() {
		this.innerContainer = new Frazione[DEFAULT_PHYSICAL_SIZE]; 
	}
	
	public FractionCollection(int physicalSize) {
		this.innerContainer = new Frazione[physicalSize];
	}
	
	public FractionCollection(Frazione[] collection) {
		this.innerContainer = collection.clone();
		this.size = initialSize(collection);
	}
	
	private int initialSize(Frazione[] collection) {
		int i = 0;
		while(i < collection.length && collection[i] != null) i++;
		return i;
	}
	
	public int size() {
		return this.size;
	}
	
	public Frazione get(int index) {
		return innerContainer[index];
	}
	
	public void put(Frazione f) {
		if(this.size == innerContainer.length) {
			Frazione[] extended = new Frazione[innerContainer.length * DEFAULT_GROWTH_FACTOR];
			for (int i = 0; i < innerContainer.length; i++) {
				extended[i] = innerContainer[i];
			}
			extended[size] = f;
			this.size++;
			innerContainer = extended;
		} else {
			innerContainer[size] = f;
			this.size++;
		}
	}
	
	public void remove(int index) {
		if (index != (this.size - 1)) {
			for (int i = index; i < size; i++) {
				innerContainer[i] = innerContainer[i+1];
			}
			this.size--;
		} else {
			innerContainer[size] = null;
			this.size--;
		}
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		for (int i = 0; i < this.innerContainer.length; i++) {
			sj.add(String.valueOf(this.innerContainer[i]));
		}
		return sj.toString();
	}
	
	public FractionCollection sum(FractionCollection collection) {
		if(this.size != collection.size) return null;
		FractionCollection result = new FractionCollection(this.size);
		for (int i = 0; i < this.size; i++) {
			result.put(this.get(i).sum(collection.get(i)));
		}
		return result;
	}
	
	public FractionCollection sub(FractionCollection collection) {
		if(this.size != collection.size) return null;
		FractionCollection result = new FractionCollection(this.size);
		for (int i = 0; i < this.size; i++) {
			result.put(this.get(i).sum(collection.get(i).mul(new Frazione(-1)))); //sum the opposite
		}
		return result;
	}
	
	public FractionCollection mul(FractionCollection collection) {
		if(this.size != collection.size) return null;
		FractionCollection result = new FractionCollection(this.size);
		for (int i = 0; i < this.size; i++) {
			result.put(this.get(i).mul(collection.get(i)));
		}
		return result;
	}
	
	public FractionCollection div(FractionCollection collection) {
		if(this.size != collection.size) return null;
		FractionCollection result = new FractionCollection(this.size);
		for (int i = 0; i < this.size; i++) {
			result.put(this.get(i).mul(new Frazione(collection.get(i).getDen(), collection.get(i).getNum())));
		}
		return result;
	}
}
