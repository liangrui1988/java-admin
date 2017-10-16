package com.huiwan.gdata.vail.v;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.huiwan.gdata.vail.BeanVail;

//@ApplicationScoped
public class RentalStation {

	@Valid
	public RentalStation() {
		// ...
	}

	@NotNull
	@Valid
	public BeanVail rentCar(@NotNull BeanVail customer,
			@NotNull @Future Date startDate, @Min(1) int durationInDays) {
		return customer;
	}

	@NotNull
	List<BeanVail> getAvailableCars() {
		return null;
	}
}