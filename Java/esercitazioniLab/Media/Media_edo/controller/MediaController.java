package controller;

import media.filters.Filter;

import media.Media;
import media.collection.MediaCollection;

public class MediaController {
	
	private MediaCollection allMedia;
	
	public MediaController() {
		allMedia = new MediaCollection();
	}
	
	public boolean add(Media m) {
		if(allMedia.indexOf(m) == -1) {
			allMedia.add(m);
			return true;
		}
		else return false;
	}

	public boolean remove(Media m) {
		int index = allMedia.indexOf(m);
		if( index != -1) {
			allMedia.remove(index);
			return true;
		}
		else return false;
	}
	
	public MediaCollection getAll() {
		MediaCollection mediaCollection = new MediaCollection(allMedia.size());
		for(int i = 0; i<allMedia.size(); i++) {
			mediaCollection.add(allMedia.get(i));
		}
		return mediaCollection;
	}
	
	public MediaCollection find(Filter f) {
		MediaCollection mediaCollection = new MediaCollection();
		for(int i = 0; i < allMedia.size(); i++) {
			if(f.filter(allMedia.get(i))) mediaCollection.add(allMedia.get(i));
		}
		return mediaCollection;
	}
}
