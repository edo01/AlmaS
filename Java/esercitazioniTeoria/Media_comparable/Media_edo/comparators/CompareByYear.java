package comparators;

import java.util.Comparator;

import media.Media;



public class CompareByYear implements Comparator<Media> {

	@Override
	public int compare(Media o1, Media o2) {
		if(o1 == o2) return 0;
		else if(o1 == null) return 1;
		else if(o2 == null) return -1;
		return o1.getYear()-o2.getYear();
	}

}
