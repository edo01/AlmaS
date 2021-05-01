package zannotaxi.model;

import java.time.LocalTime;


public class FasciaOraria {

	private double costoScattoIniziale;
	private LocalTime fine, inizio;
	

	public FasciaOraria( LocalTime inizio, LocalTime fine, double costoScattoIniziale) {
		super();
		this.costoScattoIniziale = costoScattoIniziale;
		this.fine = fine;
		this.inizio = inizio;
	}


	public double getCostoScattoIniziale() {
		return costoScattoIniziale;
	}

	public boolean contiene(LocalTime l) {
		return l.isAfter(inizio) && l.isBefore(fine);
	}

	
}
