package agenda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact implements Comparable<Contact> {

	private String name, surname;
	private List<Detail> detailList;
	
	public Contact(String name, String surname) {
		this(name, surname, new ArrayList<>());
	}
	
	
	public Contact(String name, String surname, List<Detail> detailList) {
		if(name==null || name.equals("") || surname == null || surname.equals("") 
				|| detailList == null) throw new IllegalArgumentException("Arguments shouldn't be null");
		this.name = name;
		this.surname = surname;
		this.detailList = detailList;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public List<Detail> getDetailList() {
		return detailList;
	}


	@Override
	public int compareTo(Contact o) {
		int res = surname.compareTo(o.surname);
		if(res==0) return name.compareTo(o.name);
		return res;
	}


	@Override
	public int hashCode() {
		return Objects.hash(detailList, name, surname);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contact))
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(detailList, other.detailList) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}


	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(name + " " + surname + "\n");
		for(Detail d : detailList) {
			s.append(d.toString()+ "\n");
		}
		return s.toString();
	}

	
}
