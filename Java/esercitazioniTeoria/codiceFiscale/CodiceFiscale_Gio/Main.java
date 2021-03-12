/* Riceve in ingresso i dati per generare un codice fiscale e in aggiunta, ricevuto il codice, verifica che esso 
 * sia correto.
 * Non ancora risolti i problemi di omocodia. Il codice generato è quello base
 */
public class Main {
     public static void main(String[] args) {
	   String nome=args[0];
	   String cognome=args[1];
	   int giorno= Integer.parseInt(args[2]);
	   int mese=Integer.parseInt(args[3]);
	   int anno=Integer.parseInt(args[4]);
	   String sesso=args[5];
	   String comune=args[6];
	   String codiceFiscale=CodiceFiscale.calcolaCodiceFiscale(nome,cognome,giorno,mese,anno,sesso,comune);
	   System.out.println("Codice Fiscale base: "+codiceFiscale);
	   String codiceDaVerificare=args[7];
	   if(CodiceFiscale.verificaCodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comune, codiceDaVerificare)) {
		   System.out.println("Codice inserito:     "+ codiceDaVerificare+ '\n' +
				   "Il codice fiscale è stato verificato correttamente");
		   if(!codiceFiscale.equals(codiceDaVerificare)) {
			   String omocodiaMessage=
					   """
					   		Non ti spaventare se è diverso dal codice base. Sei stato 'vittima' di una omocodia e
					   		quindi hanno dovuto cambiare qualche numero in lettera!
					   		""";
			   System.out.println(omocodiaMessage);
	     }
	   }
	   else {
		   String error=
				   """
				   ATTENZIONE! Il codice fiscale potrebbe esser stato inserito non correttamente. Ricontrolla!
				   """;
		System.out.println("Codice inserito:     "+codiceDaVerificare+'\n'+ error);
	   }
     }
  }
