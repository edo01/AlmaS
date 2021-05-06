package main;

import ui.MediaView;
import utils.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int option;
		MediaView myMedia = new MediaView();
		do {
			String[] menuItems = new String[] { "Aggiungi Media", "Elimina Media", "Vedi Tutti", "Cerca", "Salva", "Leggi da file" };
			// , "Cerca per Tipo", "Cerca per Durata", "Cerca per Genere" };
			
			Menu menu = new Menu("My Media", menuItems);
			option = menu.showAndGetOption();

			String path = "miao.dat";
			
			switch (option) {
			case 1:
				myMedia.addMedia();
				break;
			case 2:
				myMedia.removeMedia();
				break;
			case 3:
				myMedia.showAll();
				break;
			case 4:
				myMedia.find();
				break;
			case 5:
				myMedia.save(path);
			case 6:
				myMedia.readFromFile(path);
			// case 5:
			// myMedia.findByType();
			// break;
			// case 6:
			// myMedia.findByDuration();
			// break;
			// case 7:
			// myMedia.findByGenre();
			// break;
			}
		} while (option != 0);
	}
}
