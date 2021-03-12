/**
* Libreria Codice Fiscale
* 
* Contiene tutte le funzioni necessarie per la generazione e verifica
* di un codice fiscale.
* 
* @author Nicolo Valdiserri
* @version 1.0 07/03/2021
*/
public class CFLib {
	
	//costanti stringhe
	private static final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String vocali = "AEIOU¿»Ã“Ÿ";
	private static final String consonanti = "BCDFGHJKLMNPQRSTVWXYZ";
	private static final String cMese = "ABCDEHLMPRST";
	private static final String cOmocodia = "LMNPQRSTUV";
	private static final String cPesoDispari = "BAKPLCQDREVOSFTGUHMINJWZYX";
	
	/**
	 * Genera il codice fiscale. Visibile esternamente e, quindi, invocabile.
	 * @param nome
	 * @param cognome
	 * @param giorno
	 * @param mese
	 * @param anno
	 * @param sesso
	 * @param comune
	 * @return String: result.toString() [il codice fiscale generato]
	 */
	public static String calcolaCodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso, String comune) {
		StringBuilder result = new StringBuilder();
		result.append(calcolaCognome(cognome));
		result.append(calcolaNome(nome));
		result.append(calcolaAnno(anno));
		result.append(calcolaMese(mese));
		result.append(calcolaGiorno(giorno, sesso));
		result.append(calcolaComune(comune));
		result.append(calcolaCarControllo(result.toString()));
		
		return result.toString();
	}
	
	/**
	 * Verifica cf passato come parametro. Visibile esternamente e, quindi, invocabile.
	 * @param nome
	 * @param cognome
	 * @param giorno
	 * @param mese
	 * @param anno
	 * @param sesso
	 * @param comune
	 * @param cf
	 * @return Boolean: true [se cf Ë corretto] oppure false [se cf Ë incorretto]
	 */
	public static boolean verificaCodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso, String comune, String cf) {
		if(!cf.substring(cf.length()-1).equalsIgnoreCase(calcolaCarControllo(cf.substring(0,cf.length()-1)))) {
			/*
			 * CODICE INCORRETTO
			 * Risulta errato il calcolo della cifra di controllo
			 */
			return false;
		}
		
		String cfCalcolato = calcolaCodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comune);
		if(cf.equalsIgnoreCase(cfCalcolato)) {
			/*
			 * CODICE CORRETTO
			 * Il cf passato come parametro e quello calcolato corrispondono
			 */
			return true;
		}
		else {
			/*
			 * Il cf passato come parametro e quello calcolato NON corrispondono:
			 * verifichiamo qui se si tratta di omocodia corretta 
			 */
		    for(int i=0; i<cf.length()-1; i++) {
		    	if(cf.charAt(i) != cfCalcolato.charAt(i)) {
		    		if(cf.charAt(i) != cOmocodia.charAt(Integer.parseInt(String.valueOf(cfCalcolato.charAt(i))))) {
		    			/*
		    			 * CODICE INCORRETTO
		    			 * Non sono rispettate le regole di sostituzione cifre dell'omocodia
		    			 */
		    			return false;
		    		}
		    		
		    	}
		    }
		}
		
		/*
		 * CODICE CORRETTO
		 * Sono rispettate le regole di sostituzione cifre: si tratta di omocodia
		 */
		return true;
	}
	
	/*
	 * Seguono i metodi ausiliari della classe. Tutti presentano visibilit‡ privata:
	 * sono di propriet‡ della classe e, pertanto, non invocabili esternamente.
	 */
	
	/**
	 * Estrae i caratteri in base alle specifiche passate come parametro.
	 * @param s (stringa da cui estrarre i caratteri)
	 * @param elenco (caratteri ammessi da estrarre)
	 * @param limite (numero di caratteri da estrarre)
	 * @return String: caratteri estratti
	 */
	private static String estraiCaratteri(String s, final String elenco, int limite) {
		StringBuilder result = new StringBuilder();
		s=s.toUpperCase();
		
		for(int i = 0; i<s.length() && result.length()<limite; i++) {
			if(elenco.contains(String.valueOf(s.charAt(i)))) result.append(s.charAt(i));			
		}
		
		return result.toString();
	}
	
	/**
	 * Estrae i caratteri in base alle specifiche passate come parametro.
	 * Inoltre, diversamente dal metodo precedente, salta la seconda
	 * occorrenza individuata. Estrae, quindi, la prima, la terza e 
	 * la quarta occorrenza rintracciata.
	 * @param s (stringa da cui estrarre i caratteri)
	 * @param elenco (caratteri ammessi da estrarre)
	 * @param limite (numero di caratteri da estrarre)
	 * @return String: caratteri estratti
	 */
	private static String estraiCaratteriConSalto(String s, final String elenco, int limite) {
		StringBuilder result = new StringBuilder();
		s=s.toUpperCase();
		int nLettera=1;
		
		for(int i = 0; i<s.length() && result.length()<limite; i++) {
			if(elenco.contains(String.valueOf(s.charAt(i)))) {
				if (nLettera != 2) {
					nLettera++;
					result.append(s.charAt(i));
				}
			}
		}
		
		return result.toString();
	}	
	
	/**
	 * Calcolo del codice del cognome (3 cifre)
	 * @param cognome
	 * @return String: codice del cognome
	 */
	private static String calcolaCognome(String cognome) {
		StringBuilder result = new StringBuilder();
		result.append(estraiCaratteri(cognome, consonanti, 3));
		
		if(result.length()<3) result.append(estraiCaratteri(cognome, vocali, 3-result.length()));
		if(result.length()<3) { for(int i = result.length(); i<3; i++) result.append("X"); }
		
		return result.toString();
	}
	
	/**
	 * Calcolo del codice del nome (3 cifre)
	 * @param nome
	 * @return String: codice del nome
	 */
	private static String calcolaNome(String nome) {
		StringBuilder result = new StringBuilder();
		result.append(estraiCaratteriConSalto(nome, consonanti, 3));
		
		if(result.length()<3) result.setLength(0); result.append(estraiCaratteri(nome, consonanti, 3-result.length()));
		if(result.length()<3) result.append(estraiCaratteriConSalto(nome, vocali, 3-result.length()));
		if(result.length()<3) { for(int i = result.length(); i<3; i++) result.append("X"); }
		
		return result.toString();
	}
	
	/**
	 * Calcolo dell'anno di nascita (2 cifre)
	 * @param anno
	 * @return String: anno di nascita
	 */
	private static String calcolaAnno(int anno) {
		return String.valueOf(anno).substring(2);
	}
	
	/**
	 * Calcolo della lettera di nascita
	 * @param mese
	 * @return String: lettera di nascita
	 */
	private static String calcolaMese(int mese) {
		return String.valueOf(cMese.charAt(mese-1));
	}
	
	/**
	 * Calcolo del giorno di nascita in base al sesso
	 * @param giorno
	 * @param sesso
	 * @return String: giorno di nascita
	 */
	private static String calcolaGiorno(int giorno, String sesso) {
		if(sesso.equalsIgnoreCase("F")) {
			return String.valueOf(giorno+=40);
		}
		if(giorno < 10 && String.valueOf(giorno).charAt(0) != 0) {
			return String.valueOf("0" + giorno);
		}
		return String.valueOf(giorno);
	}
	
	/**
	 * Calcolo del comune
	 * N.B in fututo si aggiunger‡ la lettura automatica da file
	 * dei codici dei comuni associati
	 * @param comune
	 * @return String: codice del comune
	 */
	private static String calcolaComune(String comune) {
		StringBuilder result = new StringBuilder();
		switch(comune.toLowerCase()) {
			case "bologna": result.append("A944"); break;
			case "milano": result.append("F205"); break;
		}
		return result.toString();
	}
	
	/**
	 * Calcolatore dell'ultimo carattere di controllo
	 * @param cf
	 * @return String: carattere di controllo
	 */
	private static String calcolaCarControllo(String cf) {
		int somma=0;
		
		//ogni cifra fra 0 e 9 Ë sostituita da una lettera fra A e J
		for(int i=0; i<cf.length(); i++) {
			if(!alfabeto.contains(String.valueOf(cf.charAt(i)))) {
				cf = cf.replace(cf.charAt(i), alfabeto.charAt(Integer.parseInt(String.valueOf(cf.charAt(i)))));
			}
		}
		
		//calcolo della somma in base alla posizione pari o dispari
		for(int i=0; i<cf.length(); i++) {
			if((i+1)%2==0) somma += alfabeto.indexOf(cf.charAt(i));
			else somma += cPesoDispari.indexOf(cf.charAt(i));
		}
				
		return String.valueOf(alfabeto.charAt(somma%26));
	}
}