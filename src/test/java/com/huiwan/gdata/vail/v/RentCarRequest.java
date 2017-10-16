package com.huiwan.gdata.vail.v;

import java.util.Date;

//@RequestScoped
public class RentCarRequest {

	//@Inject
	private RentalStation rentalStation;

	public void rentCar(String customerId, Date startDate, int duration) {
		//causes ConstraintViolationException
		rentalStation.rentCar( null, null, -1 );
	}
}