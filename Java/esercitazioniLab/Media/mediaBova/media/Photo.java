package media;

import utils.StringUtils;

public class Photo extends Media {
	
	private String[] authors;
	
	public Photo(String title, int year, String[] authors) {
		super(title, year);
		this.authors = authors;
	}
	
	public Type getType() {
		return Type.PHOTO;
	}
	
	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) && obj instanceof Photo p) {
			return (StringUtils.areEquivalent(this.authors, p.authors));
		}
		return false;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	
	
}
