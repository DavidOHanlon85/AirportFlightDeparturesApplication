package p3AirportFlightDeparturesApplication;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCommercialFlight {

	// data input

	String validStringLow, validStringHigh;

	LocalTime validTimeEarly, validTimeLate;

	int validNumberLow, validNumberHigh;

	BoardingStatus closed, boarding, delayed, cancelled, notStarted;

	CommercialFlight c;

	@BeforeEach
	void setUp() throws Exception {

		validStringLow = "x";
		validStringHigh = "x".repeat(30);

		validTimeEarly = LocalTime.of(06, 00);
		validTimeLate = LocalTime.of(22, 33);

		validNumberLow = 0;
		validNumberHigh = 1000;

		closed = BoardingStatus.CLOSED;
		boarding = BoardingStatus.BOARDING;
		delayed = BoardingStatus.DELAYED;
		cancelled = BoardingStatus.CANCELLED;
		notStarted = BoardingStatus.NOT_STARTED;

		c = new CommercialFlight(validStringLow, validStringLow, validTimeEarly, validStringLow, validStringLow,
				validStringLow, validStringLow, boarding, validNumberLow);

	}

	@Test
	void testCommercialFlightDefaultConstructorValid() {
		assertNotNull(c);
	}

	@Test
	void testCommercialFlightConstructorWithArgsValid() {
		assertEquals(validStringLow, c.getFlightNumber());
		assertEquals(validStringLow, c.getAirline());
		assertEquals(validTimeEarly, c.getDepartureTime());
		assertEquals(validStringLow, c.getDestination());
		assertEquals(validStringLow, c.getAirportCode());
		assertEquals(validStringLow, c.getCountryGate());
		assertEquals(validStringLow, c.getGate());
		assertEquals(boarding, c.getBoardingStatus());
		assertEquals(validNumberLow, c.getPassengerNumber());
	}

	@Test
	void testSetGetFlightNumberValid() {
		c.setFlightNumber(validStringLow);
		assertEquals(validStringLow, c.getFlightNumber());
		
		c.setFlightNumber(validStringHigh);
		assertEquals(validStringHigh, c.getFlightNumber());
	}

	@Test
	void testSetGetFlightNumberInvalid() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testSetGetDestinationValid() {
		c.setDestination(validStringLow);
		assertEquals(validStringLow, c.getDestination());
		
		c.setDestination(validStringHigh);
		assertEquals(validStringHigh, c.getDestination());
		
		
		
	}

	@Test
	void testSetGetDestinationInvalid() {
		fail("Not yet implemented");
	}
	
	@Test
	void testSetGetDepartureTimeValid() {
		c.setDepartureTime(validTimeEarly);
		assertEquals(validTimeEarly, c.getDepartureTime());
		
		c.setDepartureTime(validTimeLate);
		assertEquals(validTimeLate, c.getDepartureTime());
	}

	@Test
	void testSetGetDepartureTimeInvalid() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetAirportCodeValid() {
		c.setAirportCode(validStringLow);
		assertEquals(validStringLow, c.getAirportCode());
		
		
		c.setAirportCode(validStringHigh);
		assertEquals(validStringHigh, c.getAirportCode());
	}

	@Test
	void testSetGetAirportCodeInvalid() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetCountryGateValid() {
		c.setCountryGate(validStringLow);
		assertEquals(validStringLow, c.getCountryGate());
		
		c.setCountryGate(validStringHigh);
		assertEquals(validStringHigh, c.getCountryGate());
	}

	@Test
	void testSetGetCountryGateInvalid() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetGateValid() {
		c.setGate(validStringLow);
		assertEquals(validStringLow, c.getGate());
		
		c.setGate(validStringLow);
		assertEquals(validStringLow, c.getGate());
	}

	@Test
	void testSetGetGateInvalid() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetBoardingStatusValid() {
		c.setBoardingStatus(boarding);
		assertEquals(boarding, c.getBoardingStatus());
		
		c.setBoardingStatus(cancelled);
		assertEquals(cancelled, c.getBoardingStatus());
		
		c.setBoardingStatus(closed);
		assertEquals(closed, c.getBoardingStatus());
		
		c.setBoardingStatus(notStarted);
		assertEquals(notStarted, c.getBoardingStatus());
		
		c.setBoardingStatus(delayed);
		assertEquals(delayed, c.getBoardingStatus());
		
	}

	@Test
	void testSetGetBoardingStatusInvalid() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSetPassengerNumber() {
		c.setPassengerNumber(validNumberLow);
		assertEquals(validNumberLow, c.getPassengerNumber());
		
		c.setPassengerNumber(validNumberHigh);
		assertEquals(validNumberHigh, c.getPassengerNumber());
	}

	@Test
	void testSetGetPassengerNumberInvalid() {
		fail("Not yet implemented");
	}

}
