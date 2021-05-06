package controller;

import media.filters.Filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


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
	
	public boolean saveAll(String path) {
		try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(path))){
			System.out.println("ciao");
			objOut.writeObject(allMedia);
		} catch (FileNotFoundException e) {
			System.out.println("impossibile trovare il file");
			return false;
		} catch (IOException e) {
			System.out.println("impossibile aprire il file");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public MediaCollection readAll(String path) {
		MediaCollection mediaCollection = null;
		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(path))){
			mediaCollection = (MediaCollection) objIn.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("impossibile trovare il file");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("impossibile aprire il file");
			System.exit(2);
		} catch (ClassNotFoundException e) {
			System.out.println("oggetti incompatibili");
			System.exit(3);
		}
		return mediaCollection;
	}
}
