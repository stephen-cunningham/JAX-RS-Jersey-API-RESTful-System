package ie.nuig.cs.ct545rest;

public class Vehicle {
	private String registration;
	private String manufacturer;
	private String colour;

	//the default constructor, which is required to avoid errors when unmarshalling Bookings from JSON
	public Vehicle(){
	}
	
	public Vehicle(String registration, String manufacturer, String colour){
		this.registration = registration;
		this.manufacturer = manufacturer;
		this.colour = colour;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}