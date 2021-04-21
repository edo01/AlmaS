package media.filters;

import media.Media;

public class GenreFilter implements Filter{

	private String genre;
	
	public GenreFilter(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean filter(Media media) {
		if (media instanceof HasGenre ) {
			if(getGenre().equals(" ")) return true;
			HasGenre new_media = (HasGenre) media;
			if(new_media.getGenre().equals(getGenre())) return true;
			else return false;
		}else return false;
	}
	
	

}
