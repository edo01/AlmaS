package media;

import java.io.Serializable;
import java.util.Objects;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Song extends Media implements HasGenre, HasDuration{

	private int duration = -1;
	private String genre;
	private String singer;
	
	
	
	public Song(String title, int year, String singer, int duration, String genre) {
		super(title, year);
		this.duration = duration;
		this.genre = genre;
		this.singer = singer;
	}

	@Override
	public Type getType() {
		return Type.SONG;
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
		return super.toString() + "duration:" + duration + "\ngenre:" + genre + "\nsinger:" + singer;
	}


	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Song other))
			return false;
		return duration == other.duration && Objects.equals(genre, other.genre) && Objects.equals(singer, other.singer);
	}

	
}
