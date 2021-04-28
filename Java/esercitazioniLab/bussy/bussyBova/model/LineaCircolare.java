package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaCircolare extends Linea {

	public LineaCircolare(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
	}
	
	@Override
	public Optional<Percorso> getPercorso(String fermataDa, String fermataA) {
		Optional<Percorso> percorso = Optional.empty();
		int orarioDa = 0;
		int orarioA = 0;
		try {
			orarioDa = getOrarioPassaggioAllaFermata(fermataDa);
			orarioA = getOrarioPassaggioAllaFermata(fermataA);
			if(isCapolineaFinale(fermataA)) {
				orarioA = getCapolineaFinale().getKey();
			} else if(isCapolineaIniziale(fermataDa)) {
				orarioDa = getCapolineaIniziale().getKey();
			}
			int durata = orarioA - orarioDa;
			if(durata < 0) {
				durata = getCapolineaFinale().getKey() + durata;
			}
			if(fermataDa.equals(fermataA)) durata = getCapolineaFinale().getKey();
			percorso = Optional.of(new Percorso(fermataDa, fermataA, this, durata));
		} catch (IllegalArgumentException i) {}
		return percorso;
	}

}
