package media;

import java.util.StringJoiner;

import media.filters.HasGenre;
import utils.StringUtils;

public class Ebook extends Media implements HasGenre {
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
	
	@Override
	public String toString() {
		StringJoiner s = new StringJoiner(", ");
		for (int i = 0; i < this.authors.length; i++) {
			s.add(this.authors[i]);
		}
		return super.toString() + "Autori: "+ s +"\nGenere: " + genre + "\n";
	}
	
	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) && obj instanceof Ebook e) {
			return (StringUtils.areEquivalent(this.authors, e.authors) && this.genre.equals(e.genre));
		}
		return false;
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
}
