package Object;

public class Organization extends ParentObject {
	private String headquarter; //tru so chinh
	private int founded_year ; // nam thanh lap
	
	public String getHeadquarter() {
		return headquarter;
	}
	public void setHeadquarter(String headquarter) {
		this.headquarter = headquarter;
	}
	public int getFounded_year() {
		return founded_year;
	}
	public void setFounded_year(int founded_year) {
		this.founded_year = founded_year;
	}
}
