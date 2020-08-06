package ie.nuig.cs.ct545rest;

import java.util.List;

//this serves as a wrapper class that holds a list of vehicle bookings inside the List data structure
public class BookingList {
	private List<Booking> bookingList;//declaring the object without instantiating it. Instantiation is handled in BookingService.java
	
    public List<Booking> getBookingList() {
        return bookingList;
    }
 
    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
