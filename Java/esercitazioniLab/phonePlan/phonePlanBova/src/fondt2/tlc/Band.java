package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import fondt2.tlc.util.DayOfWeekHelper;

public class Band {
	private DayOfWeek[] combinedDays;
	private double costPerInterval;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public Band(LocalTime start, LocalTime end, DayOfWeek[] combinedDays, double costPerInterval) {
		this.startTime = start;
		this.endTime = end;
		this.combinedDays = combinedDays.clone();
		this.costPerInterval = costPerInterval;
	}

	public DayOfWeek[] getCombinedDays() {
		return combinedDays;
	}

	public double getCostPerInterval() {
		return costPerInterval;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}
	
	public boolean isInBand(LocalDateTime time) {
		DayOfWeek d = time.getDayOfWeek();
		if(DayOfWeekHelper.isDayIn(d, this.combinedDays)) {
			LocalTime t = LocalTime.of(time.getHour(), time.getMinute());
			if((t.isAfter(this.startTime) && t.isBefore(this.endTime)) || (t.equals(this.startTime)) || (t.equals(this.endTime))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isValid() {
		if((this.startTime.isBefore(this.endTime)) && (this.combinedDays != null && this.combinedDays.length != 0) && (this.costPerInterval > 0)) {
			for (int i = 0; i < this.combinedDays.length; i++) {
				if(this.combinedDays[i] == null) return false;
			}
			return true;
		}
		return false;
	}
	
}
