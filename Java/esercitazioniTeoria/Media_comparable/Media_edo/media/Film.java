package media;

import java.util.Arrays;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Film extends Media implements HasGenre, HasDuration{

	private String[] actors;
	private String director;
	private int duration = -1;
	private String genre;
	


	public Film(String title, int year, String director,int duration, String[] actors, String genre) {
		super(title, year);
		this.actors = actors;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
	}

	@Override
	public Type getType() {
		return Type.FILM;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
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

	@Override
	public String toString() {
		return super.toString() +"\nactors:" + Arrays.toString(actors) + "\ndirector:" + director + "\nduration:" + duration
				+ "\ngenre:" + genre;
	}

	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj) || !(obj instanceof Film other))
			return false;
		return Arrays.equals(actors, other.actors) && director.equals(other.director)
				&& duration == other.duration && genre.equals(other.genre);
	}

	
	
}
