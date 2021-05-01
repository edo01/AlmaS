package bussy.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCercatore implements Cercatore{
	private Map<String, Linea> mappaLinee;
	
	public MyCercatore(Map<String, Linea> mappaLinee) {
		if(mappaLinee == null) throw new IllegalArgumentException();
		this.mappaLinee = mappaLinee;
	}
	
	@Override
	public SortedSet<Percorso> cercaPercorsi(String fermataDa, String fermataA, OptionalInt durataMax) {
		SortedSet<Percorso> percorsi = new TreeSet<>(); 
		if(fermataDa == null || fermataA == null || fermataDa.isEmpty() || fermataA.isEmpty()) throw new IllegalArgumentException();
		for(Entry<String, Linea> e : this.mappaLinee.entrySet()) {
			Optional<Percorso> p = e.getValue().getPercorso(fermataDa, fermataA);
			if (p.isPresent()) {
				if(durataMax.isPresent()) {
					if(p.get().getDurata() < durataMax.getAsInt()) percorsi.add(p.get());
				} else {
					percorsi.add(p.get());
				}
			}
		}
		//System.out.println(percorsi.first().getLinea().getId());
		return percorsi;
	}

	@Override
	public Map<String, Linea> getMappaLinee() {
		return this.mappaLinee;
	}

}
