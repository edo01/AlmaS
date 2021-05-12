package agenda.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import agenda.model.Contact;
import agenda.model.Detail;

public class TextContactsPersister implements ContactsPersister {

	private final String SEPARATOR = ";";
	
	public TextContactsPersister() {
		// TODO Auto-generated constructor stub
	}

	private Optional<Contact> readContact(BufferedReader br) throws IOException, BadFileFormatException{
		String firstLine = readLineSkippingEmpty(br);
		if(firstLine == null) return Optional.empty();
		//leggo la prima riga
		if(!firstLine.equals("StartContact")) throw new BadFileFormatException("StartContact expected");
		//salvo nome e cognome
		StringTokenizer name_surname = new StringTokenizer(readLineSkippingEmpty(br), SEPARATOR);
		//controllo che il formato sia corretto
		if(name_surname.countTokens() != 2) throw new BadFileFormatException("bad name and surname format");
		
		Contact c = new Contact(name_surname.nextToken().trim(), name_surname.nextToken().trim());
		
		readDetails(c, br);
		
		return Optional.of(c);
		
	}

	private void readDetails(Contact c, BufferedReader br) throws BadFileFormatException, IOException{
		//lettura dei dettagli
		StringTokenizer detail;
		String detailLine = readLineSkippingEmpty(br);
		if(detailLine==null) 
			throw new BadFileFormatException("Detail or EndContact expected");
		
		while(!detailLine.equals("EndContact")) {
			detail = new StringTokenizer(detailLine, SEPARATOR);
			DetailPersister detailPersister = DetailPersister.of(detail.nextToken());
			if(detailPersister == null) throw new BadFileFormatException("Unknown Detail Type");
			c.getDetailList().add(detailPersister.load(detail));
			detailLine = readLineSkippingEmpty(br);
			if(detailLine==null) 
				throw new BadFileFormatException("Detail or EndContact expected");

		}		
	}
	
	private String readLineSkippingEmpty(BufferedReader br) throws IOException {
		String line = br.readLine();
		while(line != null && line.equals("")) br.readLine();
		return line;
	}
	
	@Override
	public List<Contact> load(Reader reader) throws IOException, BadFileFormatException {
		if(reader == null) throw new IOException("reader null");
		
		BufferedReader br = new BufferedReader(reader);
		List<Contact> contactList = new ArrayList<>();
		Optional<Contact> c = readContact(br);
		while(c.isPresent()) {
			contactList.add(c.get()); 
			c = readContact(br);
		}
		return contactList;
	}

	private void saveContact(Contact c, Writer w) {
		PrintWriter pw = new PrintWriter(w);
		pw.println("StartContact");
		pw.println(c.getName() + SEPARATOR + c.getSurname());
		StringBuilder detailStr = new StringBuilder();
		saveDetails(c.getDetailList(), detailStr);
		pw.println(detailStr);
		pw.println("EndContact");
		pw.close();
	}
	
	private void saveDetails(List<Detail> details, StringBuilder sb ) {
		for(Detail d : details) {
			DetailPersister detailPersister = DetailPersister.of(d.getName());
			detailPersister.save(d, sb);
		}
	}
	
	@Override
	public void save(List<Contact> contacts, Writer writer) throws IOException {
		for(Contact c: contacts) {
			saveContact(c, writer);
		}
	}

}
