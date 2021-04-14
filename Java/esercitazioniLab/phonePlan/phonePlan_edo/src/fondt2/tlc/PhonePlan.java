package fondt2.tlc;

public class PhonePlan {

	private String name;
	private Rate[] rates;
	
	public String getName() {
		return name;
	}
	
	public PhonePlan(String name, Rate[] rates) {
		this.name = name;
		this.rates = rates.clone();
	}
	
	private Rate[] getRates() {
		return rates;
	}
	
	public double getCallCost(PhoneCall p) {
		double tot = -1;
		for(Rate rate: getRates()) {
			if(rate.isApplicableTo(p.getDestNumber())) {
				tot = rate.getCallCost(p);
			}
		}
		return tot;
	}
	
	public boolean isValid() {
		for(Rate rate: getRates()) {
			if(!rate.isValid())return false;
		}
		return true;
	}
	
}
