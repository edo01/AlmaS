
public class CodiceFiscale {
  
    public static String calcolaCodiceFiscale(String nome, String cognome, int giorno, int mese, 
			int anno, String sesso, String comune) {
		cognome=cognome.toUpperCase();
		nome=nome.toUpperCase();
		sesso=sesso.toUpperCase();
		String codiceParziale= calcolaCognome(cognome)+calcolaNome(nome)+calcolaAnno(anno)+calcolaMese(mese)+
				calcolaGiorno(giorno, sesso)+calcolaComune(comune);
		String codiceFiscale=codiceParziale+calcolaCarControllo(codiceParziale);
		return codiceFiscale;
	}
	
	public static boolean verificaCodiceFiscale(String nome, String cognome, int giorno, int mese, 
			int anno, String sesso, String comune, String codiceFiscale) {
		String codiceGenerato= calcolaCodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comune);
		if(codiceFiscale.equals(codiceGenerato)) return true;
		else return verificaOmocodia(codiceGenerato, codiceFiscale);
	}
	
   private static boolean isConsonante(char c) {
	   String vocali= "AEIOU";
	   if(vocali.indexOf(c)<0) return true;  //restituisce vero se è una consonante
	   else return false;
   }
   
   private static boolean isNumber(char c) { 
	   String alfabeto="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   if(alfabeto.indexOf(c)<0) return true; //se non fa parte dell'alfabeto è un numero
	   else return false;
   }
    
   private static String calcolaCognome(String cognome) {
	   int countCognome=0;
	   StringBuilder sbb= new StringBuilder();
	   for(int i=0; i<cognome.length() && countCognome<3; i++) {  //creo una stringa di 3 consonanti (se ci sono)
		  if(isConsonante(cognome.charAt(i))) {sbb.append(cognome.charAt(i)); countCognome++; }
		  }
	   if(countCognome<3) { //riempo con vocali e , se non bastano, con "X"
		   for(int i=0; i<cognome.length() && countCognome<3; i++) {
			   if(!isConsonante(cognome.charAt(i))) { sbb.append(cognome.charAt(i)); countCognome++; }
		   }
		   while(countCognome<3) { sbb.append("X"); countCognome++;}
	   }
	   String siglaCognome= sbb.toString();
	   return siglaCognome;
   }
   
   private static String calcolaNome(String nome) {
	   StringBuilder sb=new StringBuilder();
	   int countNome=0;
	   for(int i=0; i<nome.length(); i++) { //creo una stringa con solo consonanti
		   if(isConsonante(nome.charAt(i))) sb.append(nome.charAt(i));
	   }
	   String consNome=sb.toString();
	   StringBuilder sbb=new StringBuilder();
	   for(int i=0; i<consNome.length() && countNome<3; i++) {
		   if(consNome.length()>3 && i==1); //se le consonanti sono più di 3, salto la seconda
		   else { 
			   sbb.append(consNome.charAt(i));
			   countNome++;
	       }
      }
	   if(countNome<3) {
		   for(int i=0; i<nome.length() && countNome<3; i++) { //riempo con vocali e , se non bastano, con "X";
			   if(!isConsonante(nome.charAt(i))) { sbb.append(nome.charAt(i)); countNome++; }
		   }
		   while(countNome<3) { sbb.append("X"); countNome++; }
	   }
	   String siglaNome=sbb.toString();
	   return siglaNome;
   }
   
   private static String calcolaAnno(int anno) {
	   String annoStr=String.valueOf(anno);
	   String lastCifre= annoStr.substring(annoStr.length()-2); //tengo le ultime due cifre
	   return lastCifre;
   }
   
   private static String calcolaMese(int mese) {
	   String mesi=" ABCDEHLMPRST"; //spazio all'inizio così ogni mese corridponde all'indice 
	   String codiceMese= String.valueOf(mesi.charAt(mese)); //converto il char del mese in stringa
	   return codiceMese;
   }
   
   private static String calcolaGiorno(int giorno, String sesso) {
	   boolean tooShort;
	   if(giorno>=0 && giorno<10) tooShort=true;
	   else tooShort=false;
	   if(sesso.equals("F")) giorno=giorno+40; //Se è donna il giorno va aumentato di 40
	   String giornoStr= String.valueOf(giorno); //converto in stringa
	   if(tooShort) giornoStr= "0"+giornoStr;
	   return giornoStr;
   }
   
   private static String calcolaComune(String comune) {
	   return comune;
   }
   
   private static String calcolaCarControllo(String codice) {
	   String dispDispari="BAKPLCQDREVOSFTGUHMINJWZYX";
	   String dispPari="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   String numAlfa="ABCDEFGHIJ";
	   for(int i=0; i<codice.length(); i++) {
		   if(dispPari.indexOf(codice.charAt(i))<0) {
			int value= Integer.parseInt(String.valueOf(codice.charAt(i)));//converto il char in stringa e poi ne prendo il valore
			char newChar= numAlfa.charAt(value);
			codice=codice.replace(codice.charAt(i), newChar); //sostiuisco il vecchio carattere numeri con l'alfabetico relativo
		   }
	   }
	   int count=0;
	   for(int i=0; i<codice.length(); i++) {
		   if(i%2==0) count= count+ dispDispari.indexOf(codice.charAt(i)); //codici dispari(0=pos1, 2=pos3,..)
		   else count=count+ dispPari.indexOf(codice.charAt(i)); //codici pari
	   }
	   int val=count%26; //valore del carattere
	   String carControllo= String.valueOf(dispPari.charAt(val)); //trovare carattere in base al valore e poi converisone in stringa
	   return carControllo;
   }
   
   private static char omocodiaConversioneNumLett(char c) { //trasforma numeri da 0 a 9 in caratteri secondo l'ordine della tabella
	   String tabellaConversione="LMNPQRSTUV";
	   int value= Integer.parseInt(String.valueOf(c));
	   char convertedChar= tabellaConversione.charAt(value);
	   return convertedChar;
   }
   
  /*
   * Verifica una omocodia generando i 128 casi diversi (si ferma alla prima corrispodeza);
   * implementabile per generazione di nuovi codici in casi di omocodia
   */
   @SuppressWarnings("unused")
private static boolean verificaEgeneraOmocodia(String codice, String codiceDaVerificare) {
	   /*Studio i casi di omocodia in cui uno o più numeri sono stati convertiti in lettere
	    * converto i caratteri numerici partendo da destra e confronto con il codice da verificare
	    */
	   int countNum=0;
	   boolean verified=false;
	           int countGiri=-1; //mostra l'indice dell'ultimo numero tra quelli fissi e modificati
	           while(countGiri<7 && !verified) { //faccio sette giri di verifica
	        	   int index=0;
	        	   String newCodice=null;
	        	   StringBuilder fisso= new StringBuilder();
	        	   fisso.append(codice);  //creo una stringa in cui converto uno o più caratteri fissi
	        	   fisso.deleteCharAt(codice.length()-1); //cancello il carattere di controllo
	        	   for(int i=codice.length()-1; index<=countGiri && i>=0 && !verified; i--) {
	        		   if(isNumber(codice.charAt(i))) {
	        			   index++;
	        			   char convertedChar=omocodiaConversioneNumLett(codice.charAt(i)); 
	        			   fisso.setCharAt(i, convertedChar);
	        			   newCodice=fisso.toString()+calcolaCarControllo(fisso.toString()); //creo e verifico il nuovo codice
	        			   if(newCodice.equals(codiceDaVerificare)) verified=true; 
	        	           }
	        	   }
	        	   countNum=index; //parto dall'ultimo numero fisso modificato
	        	   //faccio il giro modificando solo un carattere alla volta (esclusi quelli fissi)
		           for(int i=codice.length()-1; countNum<=7 && i>=0 && !verified; i--) {
		    	   StringBuilder prove= new StringBuilder();
		    	   if(isNumber(codice.charAt(i))){
		    		  countNum++;
		    		  char convertedChar=omocodiaConversioneNumLett(codice.charAt(i));
		    		  if(countGiri<0) prove.append(codice);
		    		  else prove.append(newCodice);
		    		  prove.setCharAt(i, convertedChar);
		    		  prove.deleteCharAt(codice.length()-1);
		    		 String newCodiceProva=prove.toString()+calcolaCarControllo(prove.toString());
		    		  if(newCodiceProva.equals(codiceDaVerificare)) verified=true;
		    	   }
		       }
		           countGiri++; 
	       }
	   return verified;
   }
   
   /*
    * Rispetto al tipo di verifica precedente (dove genero fino a 128 configurazioni), in questa guardo
    * i singoli caratteri del codice base e li confronto con quello da verificare.
    * Se manca una corridpondenza e sta su un numero (del codice base), verifico che il numero sia stato cambiato secondo criterio;
    * Se manca una corridpondenza e NON sta su un numero, se non è il carattere finale do errore.
    * Se invece è il carattere finale lo ricalcolo per vedere se è giusto
    */
   private static boolean verificaOmocodia(String codiceGenerato, String codiceDaVerificare) {
	   boolean verified=true;
	   for(int i=0; i<codiceGenerato.length() && verified; i++) {
		   if(codiceGenerato.charAt(i)==codiceDaVerificare.charAt(i)); 
		   else if(isNumber(codiceGenerato.charAt(i))){
			   if(omocodiaConversioneNumLett(codiceGenerato.charAt(i))==codiceDaVerificare.charAt(i));
			   else verified=false;
	       }
		   else {
			   if(i==codiceGenerato.length()-1) {
				  StringBuilder sb=new StringBuilder();
				  sb.append(codiceDaVerificare);
				  sb.deleteCharAt(codiceDaVerificare.length()-1);
				  if(calcolaCarControllo(sb.toString()).equals(String.valueOf(codiceDaVerificare.charAt(i))));
				  else verified=false;
			   }
			   else verified=false;
			   
		   }
	   }
	   return verified;
   }
}
   

