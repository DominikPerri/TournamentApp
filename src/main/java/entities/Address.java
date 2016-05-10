package entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String streetName;
	
	private long houseNumber;
	
	private long zipCode;
	
	private String cityName;

	public Address(String streetName, long houseNumber, long zipCode, String cityName) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.cityName = cityName;
	}
	
	public Address(){
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public long getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(long houseNumber) {
		this.houseNumber = houseNumber;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
	

}
