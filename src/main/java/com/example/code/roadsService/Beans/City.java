package com.example.code.roadsService.Beans;

public class City {

	private String origin;
	private String destination;

	public City(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", origin, destination);
	}

}
