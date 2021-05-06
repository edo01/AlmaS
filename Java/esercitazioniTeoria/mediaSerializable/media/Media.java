package media;

import java.io.Serializable;

import media.filters.HasType;

public abstract class Media implements HasType, Serializable{
	
	private String title;
	private int year = -1;
	

	public Media(String title, int year) {
		this.title = title;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public abstract Type getType();
	
	@Override
	public String toString() {
		return "Title:" + title + "\n year:" + year + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Media other))
			return false;
		return this.title.equals(other.title) && year == other.year;
	}

	
}
