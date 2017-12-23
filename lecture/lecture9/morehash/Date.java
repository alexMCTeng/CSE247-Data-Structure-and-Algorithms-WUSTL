package lecture9.morehash;

public class Date {
	
	private String month;
	private int date, year;
	
	public Date(String month, int date, int year) {
		this.month = month;
		this.date = date;
		this.year = year;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + date;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + year;
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
		Date other = (Date) obj;
		if (date != other.date)
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year != other.year)
			return false;
		return true;
	}



	public static void main(String[] args) {
		Date d = new Date("Nov", 19, 1989);
		System.out.println(d.hashCode());
	}
	

}
