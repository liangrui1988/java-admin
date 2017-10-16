package com.huiwan.gdata.vail.v;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.huiwan.gdata.vail.BeanVail;

public class Car {

	@NotNull
	private String manufacturer;

	@NotNull
	@Size(min = 2, max = 14)
	private String licensePlate;

	@Min(2)
	private int seatCount;

	
	public Car() {
		
	}
	
	public Car(String manufacturer, String licencePlate, int seatCount) {
		this.manufacturer = manufacturer;
		this.licensePlate = licencePlate;
		this.seatCount = seatCount;
	}
	
	public void drive(@Min(2)int driver) { 
		
		Object [] s;
		System.out.println("driver:"+driver);
		this.seatCount=driver;
	}
	
	
	public void drive(@Min(2)int driver,@NotNull @Size(min = 2, max = 14) String name ,String a,String b,@Valid BeanVail vail) { 
		System.out.println("driver:"+driver);
		this.seatCount=driver;
	}
	
	public Car(String manufacturer) {
		this.manufacturer = manufacturer;
		
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	
	
}