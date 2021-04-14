package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

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
	
	public boolean isApplicableTo(String destinationNumber) {
		return destinationNumber.startsWith(numberRoot);
	}

	public Band[] getBands() {
		return bands;
	}

	public String getName() {
		return name;
	}
	
	public boolean isValid() {
		for (int i = 0; i < this.bands.length; i++) {
			if(!this.bands[i].isValid()) return false;
		}
		for(DayOfWeek d : DayOfWeek.values()) {
			if (!validateDay(d)) return false;
		}
		return true;
	}
	
	private Band[] selectBandsInDay(DayOfWeek day) {
		Band[] dayBands = new Band[this.bands.length];
		int numBands = 0;
		for (int i = 0; i < this.bands.length; i++) {
			for (int j = 0; j < this.bands[i].getCombinedDays().length; j++) {
				if (day.getValue() == this.bands[i].getCombinedDays()[j].getValue()) {
					dayBands[numBands] = this.bands[i];
					numBands++;
				}
			}
		}
		Band[] finalDayBands = new Band[numBands];
		for (int i = 0; i < finalDayBands.length; i++) {
			finalDayBands[i] = dayBands[i];
		}
 		return finalDayBands;
	}
	
	
	private void sortBandsByStartTime(Band[] bands){
		System.out.println("Array iniziale:");
		for (int i = 0; i < bands.length; i++) {
			System.out.println(bands[i].getStartTime() +" - "+ bands[i].getEndTime());
		}
        for (int i = 0; i < bands.length - 1; i++) {
            int minIndex = i; 
            for (int j = i+1; j < bands.length; j++) {
                if (bands[j].getStartTime().isBefore(bands[minIndex].getStartTime())) minIndex = j;
            }
            Band temp = bands[minIndex];
            bands[minIndex] = bands[i];
            bands[i] = temp;
            System.out.println();
            for (int g = 0; g < bands.length; g++) {
				System.out.println(bands[g].getStartTime() +" - "+ bands[g].getEndTime());
			}
        } 
    }
	
	public double getCallCost(PhoneCall call) {
		long callTime = Duration.between(call.getStart(), call.getEnd()).toMillis();
		double nIntervals = Math.ceil((double)callTime / this.intervalInMillis);
		for (int i = 0; i < this.bands.length; i++) {
			if (this.bands[i].isInBand(call.getStart())) {
				return this.startCallCost + (nIntervals * this.bands[i].getCostPerInterval());
			}
		}
		return -1;
	}
	
	private boolean validateBandsInDay(Band[] bands) {
		sortBandsByStartTime(bands);
		if(!((bands[0].getStartTime().equals(LocalTime.MIN)) && (bands[bands.length - 1].getEndTime().equals(LocalTime.MAX)))) return false;
		for (int i = 1; i < bands.length - 2; i++) {
			if(!(bands[i].getEndTime().plusNanos(1).equals(bands[i + 1].getStartTime()))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean validateDay(DayOfWeek day) {
		return validateBandsInDay(selectBandsInDay(day));
	}
	
}
