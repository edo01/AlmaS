package media;

import java.io.Serializable;
import java.util.Arrays;

public class Photo extends Media{

	private String[] authors;

	
	public Photo(String title, int year, String[] authors) {
		super(title, year);
		this.authors = authors;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	@Override
	public Type getType() {
		return Type.PHOTO;
	}

	@Override
	public String toString() {
		return super.toString() + "\nauthors=" + Arrays.toString(authors);
	}

	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Photo other))
			return false;
		return Arrays.equals(authors, other.authors);
	}

	
	
}
