package media.filters;

import media.Media;

public class DurationFilter implements Filter{
	
	private int duration = -1;
	
	public DurationFilter(int duration) {
		this.setDuration(duration); 
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
		
	@SuppressWarnings("preview")
	public boolean filter(Media media) {
		if(media instanceof HasDuration hd) {
			return (this.duration == 0 || hd.getDuration() <= this.duration);
		}
		return false;
	}
}
