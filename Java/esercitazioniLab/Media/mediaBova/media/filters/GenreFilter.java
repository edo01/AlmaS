package media.filters;

import media.Media;

public class GenreFilter implements Filter{
	
	private String genre;
	
	public GenreFilter(String genre) {
		setGenre(genre);
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@SuppressWarnings("preview")
	public boolean filter(Media media) {
		if(media instanceof HasGenre hg) {
			return (this.genre.equals(" ") || this.genre.equals(hg.getGenre()));	
		}
		return false;
	}
}
