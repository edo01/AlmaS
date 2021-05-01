package media;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Song extends Media implements HasGenre, HasDuration {
	private int duration = -1;
	private String genre;
	private String singer;
	
	public Song (String title, int year, String singer, int duration, String genre) {
		super(title, year);
		this.duration= duration;
		this.genre = genre;
		this.singer = singer;
	}
	
	public Type getType() {
		return Type.SONG;
	}
	
	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) && obj instanceof Song s) {
			return (this.duration == s.duration && this.genre.equals(s.genre) && this.singer.equals(s.singer));
		}
		return false;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Cantante: " + singer + "\nGenere: " + genre + "\nDurata: " + duration + "\n";
	}
}
