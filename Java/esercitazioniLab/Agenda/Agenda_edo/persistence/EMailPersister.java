package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.EMail;
import agenda.model.Phone;

public class EMailPersister implements DetailPersister {

private final String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		if(source.countTokens()!=2) throw new BadFileFormatException();
		EMail e = (EMail) DetailFactory.of("Email");
		String descr = source.nextToken(SEPARATOR).trim();
		String email = source.nextToken(SEPARATOR).trim();
		e.setDescription(descr);
		e.setValue(email);
		return e;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		if(d instanceof EMail) {
			EMail p = (EMail) d;
			sb.append(p.getName()+ SEPARATOR);
			sb.append(p.getDescription() + SEPARATOR);
			sb.append(p.getValue()+ FileUtils.NEWLINE);
		}else throw new IllegalArgumentException();
	}

}
