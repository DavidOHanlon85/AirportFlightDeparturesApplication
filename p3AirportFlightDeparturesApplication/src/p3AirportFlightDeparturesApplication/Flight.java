/**
 * 
 */
package p3AirportFlightDeparturesApplication;

import java.time.LocalTime;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public abstract class Flight {
	
	// Instance Variables
	
	private String flightNumber;
	private String airline;
	private LocalTime departureTime; 
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	Flight(){
		
	}

	/**
	 * @param flightNumber
	 * @param airline
	 * @param departureTime
	 */
	public Flight(String flightNumber, String airline, LocalTime departureTime) {
		this.setFlightNumber(flightNumber);
		this.setAirline(airline);
		this.setDepartureTime(departureTime);
	} 
	
	// Getters and Setters

	/**
	 * @return the flightNumber
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * @param airline the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * @return the departureTime
	 */
	public LocalTime getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	public abstract void Cancel();

}
