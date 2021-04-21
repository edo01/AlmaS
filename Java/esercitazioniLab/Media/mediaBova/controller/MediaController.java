package controller;

import media.Media;
import media.collection.MediaCollection;
import media.filters.Filter;

public class MediaController {
	private MediaCollection allMedias;
	
	public MediaController() {
		this.allMedias = new MediaCollection();
	}
	
	public boolean add(Media m) {
		int oldSize = this.allMedias.size();
		for (int i = 0; i < this.allMedias.size(); i++) {
			if(allMedias.get(i).equals(m)) return false;
		}
		this.allMedias.add(m);
		return (oldSize == (this.allMedias.size() - 1));
	}
	
	public MediaCollection find(Filter f) {
		MediaCollection result = new MediaCollection();
		for (int i = 0; i < this.allMedias.size(); i++) {
			if(f.filter(this.allMedias.get(i))) result.add(this.allMedias.get(i));
		}
		return result;
	}
	
	public MediaCollection getAll() {
		MediaCollection m = new MediaCollection();
		for (int i = 0; i < this.allMedias.size(); i++) {
			m.add(this.allMedias.get(i));
		}
		return m;
	}
	
	public boolean remove(Media media) {
		int oldSize = this.allMedias.size();
		for (int i = 0; i < this.allMedias.size(); i++) {
			if(allMedias.get(i).equals(media)) this.allMedias.remove(i);
		}
		return (oldSize == (this.allMedias.size() + 1));
	}
}
