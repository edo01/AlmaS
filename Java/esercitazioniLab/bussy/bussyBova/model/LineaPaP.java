package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaPaP extends Linea{

	public LineaPaP (String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
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
			if (orarioA > orarioDa) percorso = Optional.of(new Percorso(fermataDa, fermataA, this, orarioA-orarioDa));
		} catch (IllegalArgumentException i) {}
		return percorso;
	}

}
