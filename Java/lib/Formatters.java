package Alma.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatters {

		
	//01 maggio 2001
	final public DateTimeFormatter mese_Esteso = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ITALY);

	
	private Formatters() {}
}
