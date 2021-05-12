package agenda.model;

import java.util.Collection;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class Agenda {

	private SortedSet<Contact> contactSet;
	
	public Agenda() {
		contactSet = new TreeSet<>();
	}
	
	public Agenda(Collection<Contact> c) {
		this.contactSet = new TreeSet<>(c);
	}
	
	public void addContact(Contact c) {
		if(c==null) throw new IllegalArgumentException("Contact shouldn't be null");
		contactSet.add(c);
	}
	
	public SortedSet<Contact> getContacts() {
		return contactSet;
	}

	public Optional<Contact> getContact(String name, String surname){
		if(name==null || name.equals("") || surname == null || surname.equals("")) 
			throw new IllegalArgumentException("Arguments shouldn't be null");
		
		Optional<Contact> c = Optional.empty();
		for(Contact contact: contactSet) {
			if(contact.getName().equals(name) && contact.getSurname().equals(surname)) c = Optional.of(contact);
		}
		return c;
	}
	
	public Optional<Contact> getContact(int i){
		if(i<0) 
			throw new IllegalArgumentException("Index should be positive");
		
		Optional<Contact> c = Optional.empty();
		int count = 0;
		for(Contact contact: contactSet) {
			if((count++) == i) c = Optional.of(contact);
		}
		return c;
	}

	public void removeContact(Contact c) {
		if(c==null) throw new IllegalArgumentException("Contact shouldn't be null");
		contactSet.remove(c);
	}
	
	public SortedSet<Contact> searchContacts(String secondName){
		if(secondName == null || secondName.equals("")) 
			throw new IllegalArgumentException("Arguments shouldn't be null");
		
		SortedSet<Contact> contacts = new TreeSet<>();
		
		for(Contact c : contactSet) {
			if(c.getSurname().equals(secondName)) {
				contacts.add(c);
			}
		}
		
		return contacts;
	}
}
