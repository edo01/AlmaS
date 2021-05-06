package media.filters;

import media.Media;

public class DurationFilter implements Filter{

	private int duration = -1;
	
	public DurationFilter(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public boolean filter(Media media) {
		if (media instanceof HasDuration ) {
			if(duration == 0) return true;
			HasDuration new_name = (HasDuration) media;
			if(new_name.getDuration()<=duration) return true;
			else return false;
		}else return false;
	}

}
