package media.filters;

import media.Media;
import media.Type;

public class TypeFilter implements Filter {

	private Type type;
	
	public TypeFilter(Type type) {
		this.type =type;
	}

	
	
	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean filter(Media media) {
		if (media instanceof HasType) {
			HasType new_media = (HasType) media;
			if(new_media.getType().equals(getType())) return true;
			else return false;
		}else return false;
	}
}
