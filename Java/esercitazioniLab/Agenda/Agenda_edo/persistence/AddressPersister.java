package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.Phone;

public class AddressPersister implements DetailPersister {

	private final String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		if(source.countTokens()!=6) throw new BadFileFormatException();
		Address a = (Address) DetailFactory.of("Address");
		String descr = source.nextToken(SEPARATOR).trim();
		String  street = source.nextToken(SEPARATOR).trim();
		String  number = source.nextToken(SEPARATOR).trim();
		String  zipCode = source.nextToken(SEPARATOR).trim();
		String  city = source.nextToken(SEPARATOR).trim();
		String  state = source.nextToken(SEPARATOR).trim();
		a.setDescription(descr);
		a.setCity(city);
		a.setNumber(number);
		a.setState(state);
		a.setStreet(street);
		a.setZipCode(zipCode);
		return a;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		if(d instanceof Address) {
			Address a = (Address) d;
			sb.append(a.getName()+ SEPARATOR);
			sb.append(a.getDescription() + SEPARATOR);
			sb.append(a.getStreet() + SEPARATOR);
			sb.append(a.getNumber() + SEPARATOR);
			sb.append(a.getZipCode() + SEPARATOR);
			sb.append(a.getCity() + SEPARATOR);
			sb.append(a.getState() + FileUtils.NEWLINE);
		}else throw new IllegalArgumentException();
	}

}
