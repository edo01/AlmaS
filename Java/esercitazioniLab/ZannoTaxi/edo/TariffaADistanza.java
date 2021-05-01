package zannotaxi.model;

import java.util.Optional;

public class TariffaADistanza implements ITariffaTaxi {

	private double costoMassimo, costoMinimo, distanzaDiScatto;
	private String nome;
	private double valoreScatto, velocitaMassima, velocitaMinima;

	public TariffaADistanza(String nome,double velocitaMinima, double velocitaMassima,
			double costoMinimo, double costoMassimo, double valoreScatto,
			double distanzaDiScatto) {
		this.nome = nome;
		this.costoMassimo = costoMassimo;
		this.costoMinimo = costoMinimo;
		this.distanzaDiScatto = distanzaDiScatto;
		this.valoreScatto = valoreScatto;
		this.velocitaMassima = velocitaMassima;
		this.velocitaMinima = velocitaMinima;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public Optional<Scatto> getScattoCorrente(int tempoTrascorso, double spazioPercorso,
			double costoCorrente) {
		double velMedia = (spazioPercorso/tempoTrascorso) * 3.6;

		//cortocicuito
		boolean effettuaScatto = costoCorrente<costoMassimo && costoCorrente>=costoMinimo;
		effettuaScatto = effettuaScatto && (velMedia<velocitaMassima && velMedia >= velocitaMinima);
		effettuaScatto = effettuaScatto && Math.round(spazioPercorso)>=distanzaDiScatto;
		double ct = getValoreScatto()*Math.floor(Math.round(spazioPercorso)/distanzaDiScatto); //nel caso che ci sia più di uno scatto
		
		return effettuaScatto? 	Optional.of(new Scatto(tempoTrascorso,spazioPercorso, ct)) :
								Optional.empty(); //type inference
	}

	@Override
	public double getValoreScatto() {
		return valoreScatto;
	}

	public double getCostoMassimo() {
		return costoMassimo;
	}

	public double getCostoMinimo() {
		return costoMinimo;
	}

	public double getDistanzaDiScatto() {
		return distanzaDiScatto;
	}

	public double getVelocitaMassima() {
		return velocitaMassima;
	}

	public double getVelocitaMinima() {
		return velocitaMinima;
	}

	
	
}
