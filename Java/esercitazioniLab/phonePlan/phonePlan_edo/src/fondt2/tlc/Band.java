package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import fondt2.tlc.util.DayOfWeekHelper;

public class Band {

	private DayOfWeek[] combinedDays;
	private double costPerInterval;
	private LocalTime start, end;
	
	public DayOfWeek[] getCombinedDays() {
		return combinedDays;
	}

	public double getCostPerInterval() {
		return costPerInterval;
	}

	public LocalTime getStartTime() {
		return start;
	}

	public LocalTime getEndTime() {
		return end;
	}

	public Band(LocalTime start, LocalTime end, DayOfWeek[] combinedDays, double costPerInterval) {
		this.start = start;
		this.end = end;
		this.combinedDays = combinedDays.clone();
		this.costPerInterval = costPerInterval;
	}

	public boolean isInBand(LocalDateTime dateTime) {
		DayOfWeek day = dateTime.getDayOfWeek();
		LocalTime time = dateTime.toLocalTime();
		if(DayOfWeekHelper.isDayIn(day, combinedDays)) {
			return time.isAfter(start) && time.isBefore(end); 
		}else return false;
	}
	
	public boolean isValid() {
		return start.isBefore(end) && costPerInterval>=0 && combinedDays.length !=0;
	}
}
