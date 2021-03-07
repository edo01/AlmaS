
public class Persona {
	private String nameSurname;
	private int yearOfBirth;
	
	public Persona(String name, int yearOfBirth){
		this.nameSurname = name;
		this.yearOfBirth= yearOfBirth;
	}
	
	public Persona(String name, String yearOfBirth){
		this.nameSurname = name;
		this.yearOfBirth= Integer.parseInt(yearOfBirth);
	}
	
	public String getNameSurname() {
		return nameSurname;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public boolean omonimo(Persona p) {
		return this.nameSurname.equalsIgnoreCase(p.getNameSurname());	
	}
	
	public int olderThan(Persona other) {
		if(this.yearOfBirth < other.getYearOfBirth()) return -1;
		else if (this.yearOfBirth == other.getYearOfBirth()) return 0;
		else return 1;
	}
}
