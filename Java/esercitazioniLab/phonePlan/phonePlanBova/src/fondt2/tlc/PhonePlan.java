package fondt2.tlc;

public class PhonePlan {
	private String name;
	private Rate[] rates;
	
	public PhonePlan(String name, Rate[] rates) {
		this.name = name;
		this.rates = rates.clone();
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getCallCost(PhoneCall call) {
		for (int i = 0; i < this.rates.length; i++) {
			if(this.rates[i].isApplicableTo(call.getDestNumber())) {
				return this.rates[i].getCallCost(call);
			}
		}
		return -1;
	}
	
	public boolean isValid() {
		for (int i = 0; i < this.rates.length; i++) {
			if(!this.rates[i].isValid()) return false;
		}
		return true;
	}
	
}
