/**
* CFMain
* 
* @author Nicolo Valdiserri
* @version 1.0 07/03/2021
*/
public class CFMain {

	public static void main(String[] args) {
		//generazione del codice fiscale base
		System.out.println("Generazione codice di Base -> " + CFLib.calcolaCodiceFiscale(
				"Nicolo",
				"Valdiserri",
				10,
				10, 
				2001,
				"M",
				"Bologna"
		));
		
		//verifica di omocodia: devo ottenere 'true' (ho sostituito gli 0 con 'L')
		System.out.println("\nEsito della verifica di " + "'VLDNCLL1R1LA944F' -> " + CFLib.verificaCodiceFiscale(
				"Nicolo",
				"Valdiserri",
				10,
				10, 
				2001,
				"M",
				"Bologna",
				"VLDNCLL1R1LA944F"
		));
		
		//ulteriori test di controllo (N.B aggiungere la direttiva -ea nelle RunConfig)
		assert(CFLib.calcolaCodiceFiscale("Mario", "Rossi", 12, 6, 1976, "M", "Bologna").equals("RSSMRA76H12A944I"));
		assert(CFLib.calcolaCodiceFiscale("Mario", "Rossi", 1, 1, 1990, "M", "Milano").equals("RSSMRA90A01F205Z"));
		assert(CFLib.verificaCodiceFiscale("Mario", "Rossi", 12, 6, 1976, "M", "Bologna", "RSSMRA76H12A94QF")) == true;
		assert(CFLib.verificaCodiceFiscale("Mario", "Rossi", 12, 6, 1976, "M", "Bologna", "RSSMRA76H12A9Q4U")) == true;
		assert(CFLib.verificaCodiceFiscale("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A01F20RU")) == true;
		assert(CFLib.verificaCodiceFiscale("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A01F2L5K")) == true;
		assert(CFLib.verificaCodiceFiscale("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A01FN05O")) == true;
		assert(CFLib.verificaCodiceFiscale("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90AL1F205K")) == true;
		assert(CFLib.verificaCodiceFiscale("Nicolò", "Valdiserri", 10, 10, 2001, "M", "Bologna", "VLDNCLL1R1LA944F")) == true;
		assert(CFLib.verificaCodiceFiscale("Nicolò", "Valdiserri", 10, 10, 2001, "M", "Bologna", "VLDNCL01R10A944P")) == false;		
	}
}