package zannotaxi.model;

import java.time.LocalTime;
import java.util.Optional;

public class Tassametro implements ITassametro {

	private ITariffaTaxi[] tariffe; 
	private FasciaOraria[] fasceOrarie;
	


	public Tassametro(ITariffaTaxi[] tariffe, FasciaOraria[] fasceOrarie) {
		super();
		this.tariffe = tariffe;
		this.fasceOrarie = fasceOrarie;
	}
	
	private Optional<Scatto> findScatto(double spazioPercorso, int tempoPercorso, double costoCorrente){
		Optional<Scatto> temp = Optional.empty();
		for(ITariffaTaxi t: tariffe) {
			temp = t.getScattoCorrente(tempoPercorso, spazioPercorso, costoCorrente);
			if(temp.isPresent()) return temp;
		}
		return temp;
	}

	private double getScattoIniziale(LocalTime inizioCorsa) {
		for(FasciaOraria f : fasceOrarie) {
			if(f.contiene(inizioCorsa)) return f.getCostoScattoIniziale();
		}
		return Double.NaN;
	}

	@Override
	public double calcolaCostoCorsa(CorsaTaxi corsaTaxi) {
		double cv = 0, space = 0;
		int time = 0;
		double[] rilevazioni = corsaTaxi.getRilevazioniDistanze();
		Optional<Scatto> temp;
		
		for (int i = 1; i < rilevazioni.length; i++) {
			time++;
			space += rilevazioni[i] - rilevazioni[i-1];
			temp = findScatto(space, time, cv);
			if(temp.isPresent()) {
				space -= temp.get().getSpazio();
				time -= temp.get().getTempo();
				cv += temp.get().getCosto();
			}
		}
		cv += getScattoIniziale(corsaTaxi.getOraPartenza());
		return cv;
	}

}
