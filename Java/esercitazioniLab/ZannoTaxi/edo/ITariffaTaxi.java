package zannotaxi.model;

import java.util.Optional;

public interface ITariffaTaxi {
	public String getNome();
	public Optional<Scatto> getScattoCorrente(int tempoTrascorso, double spazioPercorso, double costoCorrente);
	public double getValoreScatto();
}
