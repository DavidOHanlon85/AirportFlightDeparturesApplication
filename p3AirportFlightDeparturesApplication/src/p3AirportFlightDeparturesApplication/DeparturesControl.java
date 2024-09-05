/**
 * 
 */
package p3AirportFlightDeparturesApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class DeparturesControl {

	public static List<Flight> allFlights = new LinkedList<Flight>();

	public static void main(String[] args) {

		readData();

		displayMenu();

	}

	private static void displayMenu() {
		int input;
		do {

			System.out.println("1. Display daily schedule departures (order by Time) - time relative");
			System.out.println("2. Delay flight - STU904 until 22:15");
			System.out.println("3. Destination Country Analysis");
			System.out.println("4. Add flight: BAA1234, British Airways, 23:30, London, LHR, UK, B12, Not Stated, 231");
			System.out.println("5. Write to file (all flights with CLOSED boarding status");
			System.out.println("6. Quit");
			System.out.println("Enter Option ");

			Scanner sc = new Scanner(System.in);
			input = sc.nextInt();
			WriteToFile w = new WriteToFile();
			Thread t = new Thread(w);

			switch (input) {
			case 1:
				System.out.println("All departures - ordered by departure time");
				Collections.sort(allFlights, new CompareByDepartTime().reversed());
				displayAllDetailsInList(allFlights);
				break;
			case 2:
				String flightNumber = "STU904";
				LocalTime delayedTime = LocalTime.of(22, 15);
				BoardingStatus changeOfStatus = BoardingStatus.DELAYED;

				changeFlight(allFlights, flightNumber, delayedTime, changeOfStatus);

				break;
			case 3:
				TreeMap<String, Integer> myMap = mapCountryToFreq(allFlights);
				displayMapDetails(myMap);
				break;
			case 4:
				String flightCode = "BAA1234";
				String airline = "British Airways";
				LocalTime newTime = LocalTime.of(23, 30);
				String destination = "London";
				String gate = "B12";
				String airportCode = "LHR";
				String country = "UK";
				BoardingStatus boardingStat = BoardingStatus.NOT_STARTED;
				int passengerNum = 231;
				addFlight(flightCode, airline, newTime, destination, airportCode, country, gate, boardingStat,
						passengerNum);
				break;
			case 5:
				t.start();
				break;
			case 6:
				System.out.println("Quitting - have a nice day");
				t.interrupt();
				break;
			default:
				System.out.println("Invalid choice - try again");
			}

		} while (input != 6);

	}

	/**
	 * This method creates a new flight based on the input of object details
	 * @param flightCode
	 * @param airline
	 * @param newTime
	 * @param destination
	 * @param airportCode
	 * @param gate
	 * @param country
	 * @param boardingStat
	 * @param passengerNum
	 */
	private static void addFlight(String flightCode, String airline, LocalTime newTime, String destination,
			String airportCode, String gate, String country, BoardingStatus boardingStat, int passengerNum) {

		CommercialFlight c = new CommercialFlight(flightCode, airline, newTime, destination, airportCode, country, gate,
				boardingStat, passengerNum);
		
		allFlights.add(c);
	}

	private static void displayMapDetails(TreeMap<String, Integer> myMap) {

		if (myMap == null || myMap.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		for (String k : myMap.keySet()) {
			System.out.println(k + "\t\t\t:" + myMap.get(k));
		}
	}

	private static TreeMap<String, Integer> mapCountryToFreq(List<Flight> allFlights2) throws IllegalArgumentException {

		if (allFlights2 == null || allFlights2.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		TreeMap<String, Integer> myMap = new TreeMap<String, Integer>();

		for (Flight f : allFlights2) {
			if (f instanceof CommercialFlight) {
				CommercialFlight c = (CommercialFlight) f;
				if (myMap.containsKey(c.getCountryGate())) {
					int runningTotal = myMap.get(c.getCountryGate());
					myMap.put(c.getCountryGate(), runningTotal + 1);
				} else {
					myMap.put(c.getCountryGate(), 1);
				}

			}
		}

		return myMap;
	}

	private static void changeFlight(List<Flight> allFlights2, String flightNumber, LocalTime delayedTime,
			BoardingStatus changeOfStatus) throws IllegalArgumentException {

		if (allFlights2 == null || allFlights2.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		for (Flight f : allFlights2) {
			if (f instanceof CommercialFlight) {
				CommercialFlight c = (CommercialFlight) f;
				if (c.getFlightNumber().equalsIgnoreCase(flightNumber)) {
					c.setDepartureTime(delayedTime);
					c.setBoardingStatus(changeOfStatus);
				}
			}
		}

	}

	/**
	 * This method displays all details in the list to screen
	 * 
	 * @param allFlights2
	 * @throws IllegalArgumentException
	 */
	private static void displayAllDetailsInList(List<Flight> allFlights2) throws IllegalArgumentException {

		if (allFlights2 == null || allFlights2.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		for (Flight f : allFlights2) {
			if (f instanceof CommercialFlight) {
				CommercialFlight c = (CommercialFlight) f;
				c.displayAll();
			}
		}

	}

	/**
	 * This method will read data from a csv into a JCF container
	 */
	private static void readData() {

		File file = new File("Departures.csv");
		int attemptedReads = 0;

		String line;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			line = br.readLine(); // remove header
			line = br.readLine(); // read first line

			while (line != null) {

				try {
					attemptedReads++;

					String[] splitDetails = line.split(",");

					CommercialFlight c = new CommercialFlight();

					if (splitDetails[0].charAt(0) == 'M' || splitDetails[0].charAt(0) == 'm') {
						throw new Exception("Military Flight - Not included");
					} else {
						c.setFlightNumber(splitDetails[0]);
					}

					c.setAirline(splitDetails[1]);

					String[] splitTime = splitDetails[2].split(":");
					LocalTime flightTime = (LocalTime.of(Integer.parseInt(splitTime[0]),
							Integer.parseInt(splitTime[1])));
					c.setDepartureTime(flightTime);
					
					c.setDestination(splitDetails[3]);

					c.setAirportCode(splitDetails[4]);
					c.setCountryGate(splitDetails[5]);
					c.setGate(splitDetails[6]);

					if (flightTime.isBefore(LocalTime.now())) {
						c.setBoardingStatus(BoardingStatus.CLOSED);
					} else if (flightTime.minusHours(1).isBefore(LocalTime.now())) {
						c.setBoardingStatus(BoardingStatus.BOARDING);
					} else {
						c.setBoardingStatus(BoardingStatus.NOT_STARTED);
					}

					c.setPassengerNumber(Integer.parseInt(splitDetails[8]));

					allFlights.add(c);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				line = br.readLine();

			}

			System.out.println("Loading data...");

			displayAllLoadedFlights(allFlights);

			System.out.println();
			System.out.println("Attempted Read: " + attemptedReads);
			System.out.println("Total Reads: " + allFlights.size());
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method displays all items in a list to console
	 */
	public static void displayAllLoadedFlights(List<Flight> allFlights2) throws IllegalArgumentException {

		if (allFlights2 == null || allFlights2.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		for (Flight f : allFlights2) {
			if (f instanceof CommercialFlight) {
				CommercialFlight temp = (CommercialFlight) f;
				temp.displaySuccessfulRead();
			}
		}
	}

}
