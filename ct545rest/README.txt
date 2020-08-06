This file explains the screenshots for part 3 (API testing in Postman):
They are found here: ct545rest\Screenshots
Method1Status200.PNG: this shows a successful HTTP response 200 OK to the client.
Method2Status200.PNG: a successful HTTP response 200 OK to the client for Booking id 3. This is successful because
	Booking with id 3 is pre-written in BookingService.java.
Method2Status404.PNG: an HTTP 404 not found response to the client because no Booking yet exists with id 4.
Method3Status201: a successful response 201 returned to client.
Method3-TestingUsingMethod2.PNG: this shows that the Booking object has been successfully created in Method3Status201.
Method4Status200.PNG: a successful 200 returned to client - typos in Method3Status201.PNG now updated.
Method4-TestingUsingMethod2.PNG: this that the Booking object has been successfully updated in Method4Status200.PNG.
Method4-TestingMethod1Again.PNG: checking method 1 again to check that all has been updated successfully. The new Booking,
	which was created by method 3 and updated by method 4, now appears in the BookingList.
Method4Status404.PNG: 404 not found response since no Booking with id 5 exists.
Method5Status200.PNG: successful 200 response - Booking with id 4 is now deleted.
Method5Status404.PNG: attempted again to delete Booking with id 4. 404 not found returned because Method5Status200.PNG
	was successful.
Method5-TestingMethod1Again.PNG: checking method 1 again to check that all has been updated successfully. Booking with id 4
	is now gone and no longer appears in the BookingList.