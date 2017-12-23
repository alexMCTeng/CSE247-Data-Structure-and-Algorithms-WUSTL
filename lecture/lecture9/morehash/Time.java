package lecture9.morehash;

public class Time {
	
	private int hour, minutes;
	boolean isAM;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + (isAM ? 1231 : 1237);
		result = prime * result + minutes;
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		if (isAM != other.isAM)
			return false;
		if (minutes != other.minutes)
			return false;
		return true;
	}
	
	

}
