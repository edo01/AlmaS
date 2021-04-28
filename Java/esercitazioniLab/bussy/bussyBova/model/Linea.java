package bussy.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public abstract class Linea {
	private String id;
	private Map<Integer, Fermata> orariPassaggioAlleFermate;
	
	public String getId() {
		return id;
	}
	
	public Map<Integer, Fermata> getOrariPassaggioAlleFermate() {
		return orariPassaggioAlleFermate;
	}
	
	public Linea(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		if((id == null) || (id.isEmpty()) || (orariPassaggioAlleFermate == null) || !(orariPassaggioAlleFermate instanceof Map<?,?>)) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.orariPassaggioAlleFermate = orariPassaggioAlleFermate;
	}
	
	public Entry<Integer, Fermata> getCapolineaIniziale() {
		Optional<Entry<Integer, Fermata>> entryFirst = Optional.empty();
		for(Entry<Integer, Fermata> e: orariPassaggioAlleFermate.entrySet()) {
			if(!entryFirst.isPresent() || entryFirst.get().getKey() > e.getKey())
				entryFirst = Optional.of(e);
		}
		if(entryFirst.isPresent()) {
			return entryFirst.get();
		}
		throw new IllegalArgumentException();
	}
	
	public Entry<Integer, Fermata> getCapolineaFinale() {
		Optional<Entry<Integer, Fermata>> entryLast = Optional.empty();
		for(Entry<Integer, Fermata> e: orariPassaggioAlleFermate.entrySet()) {
			if(!entryLast.isPresent() || entryLast.get().getKey() < e.getKey())
				entryLast = Optional.of(e);
		}
		if(entryLast.isPresent()) {
			return entryLast.get();
		}
		throw new IllegalArgumentException();
	}
	
	public boolean isCapolineaFinale(String nome) {
		return (getCapolineaFinale().getValue().getNome().equals(nome));
	}
	
	public boolean isCapolineaIniziale(String nome) {
		return (getCapolineaIniziale().getValue().getNome().equals(nome));
	}
	
	public boolean isCircolare() {
		return getCapolineaIniziale().getValue().getId().equals(getCapolineaFinale().getValue().getId());
	}
	
	public int getOrarioPassaggioAllaFermata(String nome) {
		Optional<Integer> orario = Optional.empty();
		for(Entry<Integer, Fermata> e : orariPassaggioAlleFermate.entrySet()) {
			if (e.getValue().getNome().equals(nome)) orario = Optional.of(e.getKey());
		}
		if(orario.isPresent()) return orario.get();
		throw new IllegalArgumentException();
	}
	
	public abstract Optional<Percorso> getPercorso(String fermataDa, String fermataA);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Linea)) return false;
		Linea other = (Linea) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}
	
	
}
