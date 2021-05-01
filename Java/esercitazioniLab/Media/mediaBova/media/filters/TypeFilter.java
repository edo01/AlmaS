package media.filters;

import media.Media;
import media.Type;

public class TypeFilter implements Filter{
	
	private Type type;
	
	public TypeFilter(Type type) {
		setType(type);
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	@SuppressWarnings("preview")
	public boolean filter (Media media) {
		if(media instanceof HasType ht) {
			return (this.type.compareTo(ht.getType()) == 0);
		}
		return false;
	}
	
}
