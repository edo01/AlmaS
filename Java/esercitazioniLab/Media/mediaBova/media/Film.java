package media;

import java.util.StringJoiner;

import media.filters.HasDuration;
import media.filters.HasGenre;
import utils.StringUtils;

public class Film extends Media implements HasGenre, HasDuration{
	private String[] actors;
	private String director;
	private int duration = -1;
	private String genre;
	
	public Film(String title, int year, String director, int duration, String[] actors, String genre) {
		super(title, year);
		this.actors = actors;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
	}
	
	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) && obj instanceof Film f) {
			return (StringUtils.areEquivalent(this.actors, f.actors) && 
					this.genre.equals(f.genre) && this.duration == f.duration &&
					this.director.equals(f.director));
		}
		return false;
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
		StringJoiner s = new StringJoiner(", ");
		for (int i = 0; i < this.actors.length; i++) {
			s.add(this.actors[i]);
		}
		return super.toString() + "Attori: " + s +"\n Regista: " + director + "\n Durata: " + duration + "\n Genere: " + genre + "\n";
	}
}
