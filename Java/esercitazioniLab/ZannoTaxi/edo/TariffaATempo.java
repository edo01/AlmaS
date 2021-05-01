package zannotaxi.model;

import java.util.Optional;

public class TariffaATempo implements ITariffaTaxi {

	private String nome;
	private double valoreScatto, velocitaMassima, velocitaMinima, tempoDiScatto;
	

	public TariffaATempo(String nome, double velocitaMinima, double velocitaMassima, double valoreScatto,
			double tempoDiScatto) {
		super();
		this.nome = nome;
		this.velocitaMinima = velocitaMinima;
		this.velocitaMassima = velocitaMassima;
		this.valoreScatto = valoreScatto;
		this.tempoDiScatto = tempoDiScatto;
	}

	public double getVelocitaMassima() {
		return velocitaMassima;
	}

	public double getVelocitaMinima() {
		return velocitaMinima;
	}

	public double getTempoDiScatto() {
		return tempoDiScatto;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public Optional<Scatto> getScattoCorrente(int tempoTrascorso, double spazioPercorso, double costoCorrente) {
		double velMedia = (spazioPercorso/tempoTrascorso) * 3.6;

		//cortocicuito
		boolean effettuaScatto = velMedia<velocitaMassima && velMedia >= velocitaMinima;
		effettuaScatto = effettuaScatto && Math.round(tempoTrascorso)>= tempoDiScatto;
		double ct = getValoreScatto()*Math.floor(tempoTrascorso/tempoDiScatto); //nel caso che ci sia più di uno scatto

		return effettuaScatto? 	Optional.of(new Scatto(tempoTrascorso,spazioPercorso, ct)) :
								Optional.empty(); //type inference
	}

	@Override
	public double getValoreScatto() {
		return valoreScatto;
	}
	

}
