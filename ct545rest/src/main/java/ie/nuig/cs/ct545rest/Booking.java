package ie.nuig.cs.ct545rest;

import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
	private static AtomicInteger uniqueBookingNum = new AtomicInteger(1);//this is a thread safe way of ensuring there is a unique id for each booking
	private int id;//the unique booking id number will be assigned to this during Booking object creation
	private Customer customer;//used to store a reference to a customer object
	private Vehicle vehicle;//used to store a reference to a vehicle object
	private String startDate;
	private String endDate;
	
	//the default constructor, which is required to avoid errors when unmarshalling Bookings from JSON
	public Booking(){
		this.setId();//calls the id setter at creation, thus assigning a unique id to the Booking
	}
	
	public Booking(Customer customer, Vehicle vehicle, String startDate, String endDate){
		this.setId();//calls the id setter at creation, thus assigning a unique id to the Booking
		this.customer = customer;
		this.vehicle = vehicle;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}
	
	/*
	 * here, the id is assigned the value of the unique booking number, then the unique booking number is incremented
	 * in the case of the first Booking object created, the id is set to 1 and then uniqueBookingNumber is incremented to 2, and ready for the 
	 * 	Booking object
	 */
	public void setId() {
		this.id = uniqueBookingNum.getAndIncrement();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}