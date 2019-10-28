package com.threetee.formationSpring.model.entity;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Named("myAddessAnnotation")
@Service("myAddessAnnotation")
public class Address extends AbstractEntity {
	private String road;
	private String cityName;
	private String postalCode;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String road, String city, String postalCode) {
		super();
		this.road = road;
		this.cityName = city;
		this.postalCode = postalCode;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return cityName;
	}

	public void setCity(String city) {
		this.cityName = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [road=" + road + ", city=" + cityName + ", postalCode=" + postalCode + "]";
	}

}
