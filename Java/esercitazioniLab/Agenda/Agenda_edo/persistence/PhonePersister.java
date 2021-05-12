package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.Phone;

public class PhonePersister implements DetailPersister {
	private final String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		if(source.countTokens()!=2) throw new BadFileFormatException();
		Phone p = (Phone) DetailFactory.of("Phone");
		String descr = source.nextToken(SEPARATOR).trim();
		String phoneNumber = source.nextToken(SEPARATOR).trim();
		p.setDescription(descr);
		p.setValue(phoneNumber);
		return p;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		if(d instanceof Phone) {
			Phone p = (Phone) d;
			sb.append(p.getName()+ SEPARATOR);
			sb.append(p.getDescription() + SEPARATOR);
			sb.append(p.getValue()+ FileUtils.NEWLINE);
		}else throw new IllegalArgumentException();
	}

}
