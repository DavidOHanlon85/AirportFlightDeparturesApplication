/**
 * 
 */
package p3AirportFlightDeparturesApplication;

import java.time.LocalTime;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class CommercialFlight extends Flight implements DisplayAllInterface {

	private static final String ORANGE_TEXT = "\033[0;33m";

	private static final String GREEN_TEXT = "\033[0;32m";

	private static final String RED_TEXT = "\033[0;31m";

	private static final String BLACK_TEXT = "\033[0;30m";
	// Instance Variables

	private String destination;
	private String airportCode;
	private String country;
	private String gate;
	private BoardingStatus boardingStatus;
	private int passengerNumber;

	/**
	 * Default Constructor
	 */
	public CommercialFlight() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param destination
	 * @param airportCode
	 * @param countryGate
	 * @param gate
	 * @param boardingStatus
	 * @param passengerNumber
	 */
	public CommercialFlight(String flightNumber, String airline, LocalTime departureTime, String destination,
			String airportCode, String countryGate, String gate, BoardingStatus boardingStatus, int passengerNumber) {
		super(flightNumber, airline, departureTime);
		this.setDestination(destination);
		this.setAirportCode(airportCode);
		this.setCountryGate(countryGate);
		this.setGate(gate);
		this.setBoardingStatus(boardingStatus);
		this.setPassengerNumber(passengerNumber);
	}

	// Getters and Setters

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the airportCode
	 */
	public String getAirportCode() {
		return airportCode;
	}

	/**
	 * @param airportCode the airportCode to set
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	/**
	 * @return the countryGate
	 */
	public String getCountryGate() {
		return country;
	}

	/**
	 * @param countryGate the countryGate to set
	 */
	public void setCountryGate(String countryGate) {
		this.country = countryGate;
	}

	/**
	 * @return the gate
	 */
	public String getGate() {
		return gate;
	}

	/**
	 * @param gate the gate to set
	 */
	public void setGate(String gate) {
		this.gate = gate;
	}

	/**
	 * @return the boardingStatus
	 */
	public BoardingStatus getBoardingStatus() {
		return boardingStatus;
	}

	/**
	 * @param boardingStatus the boardingStatus to set
	 */
	public void setBoardingStatus(BoardingStatus boardingStatus) {
		this.boardingStatus = boardingStatus;
	}

	/**
	 * @return the passengerNumber
	 */
	public int getPassengerNumber() {
		return passengerNumber;
	}

	/**
	 * @param passengerNumber the passengerNumber to set
	 */
	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	@Override
	public void displayAll() {
		System.out.println("_______________________");
		System.out.println("Flight Number          : " + getFlightNumber());
		System.out.println("Airline (carrier)      : " + getAirline());
		System.out.println("Departure Time         : " + getDepartureTime());
		System.out.println("Destination            : " + getDestination());
		System.out.println("Airport Code           : " + getAirportCode());
		System.out.println("Country                : " + getCountryGate());
		System.out.println("Gate                   : " + getGate());
		System.out.printf("Boarding status        : ");
		printAppropriateColour();
		System.out.println("Passenger number       : " + getPassengerNumber());
		System.out.println("_______________________");

	}

	private void printAppropriateColour() {
		if (getBoardingStatus() == BoardingStatus.CLOSED) {
			System.out.print(RED_TEXT);
			System.out.print("CLOSED");
			System.out.println(BLACK_TEXT);
		} else if (getBoardingStatus() == BoardingStatus.BOARDING) {
			System.out.print(GREEN_TEXT);
			System.out.print("BOARDING");
			System.out.println(BLACK_TEXT);
		} else if (getBoardingStatus() == BoardingStatus.DELAYED) {
			System.out.print(ORANGE_TEXT);
			System.out.print("DELAYED");
			System.out.println(BLACK_TEXT);
		} else if (getBoardingStatus() == BoardingStatus.NOT_STARTED) {
			System.out.print(BLACK_TEXT);
			System.out.print("NOT_STARTED");
			System.out.println(BLACK_TEXT);
		} else if (getBoardingStatus() == BoardingStatus.CANCELLED){
			System.out.print(BLACK_TEXT);
			System.out.print("CANCELLED");
			System.out.println(BLACK_TEXT);
		} else {
			System.out.println("NO UPDATE");
		}
	}

	@Override
	public void Cancel() {
		// TODO Auto-generated method stub

	}

	public void displaySuccessfulRead() {

		System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %d\n", getFlightNumber(), getAirline(), getDepartureTime(),
				getDestination(), getAirportCode(), getCountryGate(), getGate(), getBoardingStatus(),
				getPassengerNumber());
		;
	}

}
