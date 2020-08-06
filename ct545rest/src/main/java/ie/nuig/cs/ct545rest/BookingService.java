package ie.nuig.cs.ct545rest;

import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/bookingservice")//setting the path at which the methods can be reached: http://localhost:8080/ct545rest/webapi/bookingservice/
@Singleton//using the Singleton pattern to ensure only one instance of the BookingService class can be created
public class BookingService {
	BookingList list;//declaring the BookingList object without instantiating it - this is handled in the constructor
	
	public BookingService() {
		list = new BookingList();//instantiation of the BookingList object
		list.setBookingList(new ArrayList<Booking>());//using the setter, which is declared in BookingList.java
		//creating 3 sample Booking objects and adding them to the BookingList when BookingService.java is instantiated
		list.getBookingList().add(new Booking(new Customer("John", "Main", "123 Fake Street"),//using Customer constructor within Booking constructor
												new Vehicle("10-C-12345", "Renault", "Black"),//using Vehicle constructor within Booking constructor
												"01/02/2020", "02/02/2020"));//also passing a startDate and endDate to Booking constructor
		list.getBookingList().add(new Booking(new Customer("Mary", "Second", "456 Main Road"), 
												new Vehicle("15-D-67891", "Ford", "White"), 
												"03/02/2020", "04/02/2020"));
		list.getBookingList().add(new Booking(new Customer("Abby", "Lynn", "789 Court Drive"), 
												new Vehicle("12-G-23456", "Ferrari", "Red"), 
												"05/02/2020", "06/02/2020"));
	}
	
	/*
	 * Note: in each of the methods below, there are repeated uses of code, e.g. @GET, Status.NOT_FOUND, etc. These will only be explained once
	 * 	and not multiple times. Only new uses of code will be explained
	 */
	//These methods are numbered as per the specification/assignment document
	//method 1 - GET request that returns the list of details of all bookings in the system in JSON format
	@GET
	@Produces(MediaType.APPLICATION_JSON)//this ensures JSON format is returned
	public BookingList getAllBookings() {
		return list;
	}
	
	//method 2 - GET request that returns details of the booking with the specified id in JSON format
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")//this is for the path, e.g.: http://localhost:8080/ct545rest/webapi/bookingservice/1 for getting details of Booking with id 1
	public Response getBooking(@PathParam("id") int id) {
		for(Booking b : list.getBookingList()) {//iterating through the BookingList
			if(b.getId() == id) {//if the id's match, return a 200 OK response and the Booking object at the specified id
				return Response.ok(b).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();//if there is no Booking object at the specified id, return a 404 Not Found error
	}
	
	/*
	 * method 3 - POST request that creates a new instance of Booking with the details in the POST request, 
	 * and adds it to the BookingList. This method returns details of the Booking that was just added in JSON format
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)//this ensures the POST method takes in data in JSON format
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBooking(Booking b) {
		list.getBookingList().add(b);//adds the given object to the BookingList via the specified JSON data
		return Response.ok(b).status(201).build();//returns a 201 Created response when the POST method is executed
	}
	
	/*
	 * method 4 - PUT request that updates the details of the booking with the specified id using the JSON representation
	 *  in the body of the PUT request. This method returns details of the Booking that was just updated in JSON format
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response updateBooking(@PathParam("id") int id, Booking b) {//the Booking object b is also passed in. This contains the updated information 
		for(Booking book : list.getBookingList()) {
			if(book.getId() == id) {//book here represents the existing Booking object, which will now be updated with the information from b
				/*
				 * in the next line, b.getCustomer().getAddress() takes the address passed in with the PUT request
				 * this data is then used to update the existing address with book.getCustomer().setAddress()
				 * the same format is followed for all other lines below
				 */
				book.getCustomer().setAddress(b.getCustomer().getAddress());
				book.getCustomer().setFirstName(b.getCustomer().getFirstName());
				book.getCustomer().setLastName(b.getCustomer().getLastName());
				book.getVehicle().setColour(b.getVehicle().getColour());
				book.getVehicle().setManufacturer(b.getVehicle().getManufacturer());
				book.getVehicle().setRegistration(b.getVehicle().getRegistration());
				book.setEndDate(b.getEndDate());
				book.setStartDate(b.getStartDate());
				return Response.ok().entity(book).build();//source for .entity(): https://howtodoinjava.com/jersey/jersey-restful-client-examples/#put
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	//method 5 - DELETE request that deletes the Booking with the specified id
	@DELETE
	@Path("/{id}")
	public Response deteleBooking(@PathParam("id") int id) {
		for(Booking b : list.getBookingList()) {
			if(b.getId() == id) {
				list.getBookingList().remove(b);//this removes the Booking from the BookingList
				//SHOULD THE FOLLOWING LINE BE INCLUDED. WITHOUT IT, THE JSON DATA IS RETURNED. WITH IT, NOT JSON DATA
				//EITHER WAY, 200 SENT
				//ALSO, IS THE COMMENT ACCURATE IN TERMS OF WHAT ASSIGNING NULL DOES? - CHECK PREVIOUS ASSIGNMENTS FOR SIMILAR COMMENT
//				b = null;//this removes the Booking object and prepares it for garbage collection
				return Response.ok().build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}