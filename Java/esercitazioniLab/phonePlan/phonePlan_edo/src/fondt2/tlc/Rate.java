package fondt2.tlc;

import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;



import fondt2.tlc.util.DayOfWeekHelper;

public class Rate {

	private Band[] bands;
	private int intervalInMillis;
	private String name;
	private String numberRoot;
	private double startCallCost;
	
	
	public Rate(String name, Band[] bands, int intervalInMillis, double startCallCost, String numberRoot) {
		this.name = name;
		this.bands = bands.clone();
		this.intervalInMillis = intervalInMillis;
		this.startCallCost = startCallCost;
		this.numberRoot = numberRoot;
	}
	
	public Band[] getBands() {
		return bands;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isApplicableTo(String destinationNumber) {
		return destinationNumber.startsWith(numberRoot);
	}
	
	private double calcolaIntervalli(PhoneCall p) {
		long durInMillis = Duration.between(p.getStart(), p.getEnd()).toMillis();
		double tot = (double)durInMillis/this.intervalInMillis;
		return Math.ceil(tot);
	}
	
	public double getCallCost(PhoneCall p) {
		double tot = -1;
		for(Band band : getBands()) {
			if(band.isInBand(p.getStart())) {
				tot = this.calcolaIntervalli(p) * band.getCostPerInterval()+ this.startCallCost;
				return tot;
			}
		}
		return tot;
	}
	
	private Band[] selectBandsInDay(DayOfWeek d) {
		int count = 0;
		for(Band band : getBands()) {
			if(DayOfWeekHelper.isDayIn(d,band.getCombinedDays())) count++;
		}
		
		Band[] b = new Band[count];
		count = 0;
		
		for(int i = 0; i<getBands().length; i++) {
			if(DayOfWeekHelper.isDayIn(d,getBands()[i].getCombinedDays())) b[count++] = getBands()[i];
		}
		return b;
	}
	
	private class SortByStartTime implements Comparator<Band>
	{
	    // Used for sorting in ascending order of
	    // roll number
	    public int compare(Band a, Band b)
	    {
	    	if(a.getStartTime().isAfter(b.getStartTime())) return 1;
	    	else if( a.getStartTime().isBefore(b.getStartTime())) return -1;
	        else return 0;
	    }
	}
	

	private void sortBandsByStartTime(Band[] bands) {
		Arrays.<Band>sort(bands, new SortByStartTime());
	}
	
	private boolean validateBandsInDay(Band[] bands) {
		bands[bands.length-1].getEndTime().equals(LocalTime.MAX);
		if(!bands[0].getStartTime().equals(LocalTime.MIN) ||
		   !bands[bands.length-1].getEndTime().equals(LocalTime.MAX)) return false;
		for(int i = 1; i<bands.length-1; i++) {
			if(!bands[i-1].getEndTime().plusNanos(1).equals(bands[i].getStartTime())) return false;
		}
		return true;
	}
	
	public boolean isValid() {
		for(Band band : getBands()) {
			if(!band.isValid()) return false;
		}
		for(DayOfWeek d : DayOfWeek.values()) {

			Band[] dayBands = selectBandsInDay(d);
			sortBandsByStartTime(dayBands);
			if(!validateBandsInDay(dayBands)) return false;
		}
		
		return true;
	}
	
	
}
