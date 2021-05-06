package media;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import media.filters.HasGenre;

public class Ebook extends Media implements HasGenre{

	private String[] authors;
	private String genre;
	

	public Ebook(String title, int year, String[] authors, String genre) {
		super(title, year);
		this.authors = authors;
		this.genre = genre;
	}

	@Override
	public Type getType() {
		return Type.EBOOK;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return super.toString() + "\nauthors:" + Arrays.toString(authors) + "\ngenre:" + genre + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Ebook other))
			return false;
		return Arrays.equals(authors, other.authors) && genre.equals(other.genre);
	}

	
}
