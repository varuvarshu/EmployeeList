package com.example.employeeinfo.model;

public class Address{
	private String zipcode;
	private Geo geo;
	private String suite;
	private String city;
	private String street;


	public String getZipcode(){
		return zipcode;
	}

	public Geo getGeo(){
		return geo;
	}

	public String getSuite(){
		return suite;
	}

	public String getCity(){
		return city;
	}

	public String getStreet(){
		return street;
	}
}
