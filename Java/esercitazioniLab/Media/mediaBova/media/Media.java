package media;

import media.filters.HasType;

public abstract class Media implements HasType {
	private String title;
	private int year = -1;
	
	public Media(String title, int year) {
		this.title = title;
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public abstract Type getType();
	
	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Media m) {
			return (this.getTitle().equals(m.getTitle()) && this.getYear() == m.getYear());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Dati del media:\nTitolo: " + title +"Anno: " + year + "\n";
	}
}
