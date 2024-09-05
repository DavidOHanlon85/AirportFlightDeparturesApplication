/**
 * 
 */
package p3AirportFlightDeparturesApplication;

import java.util.Comparator;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class CompareByDepartTime implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		// TODO Auto-generated method stub
		if (o1.getDepartureTime().isBefore(o2.getDepartureTime())){
			return 1;
		} else if (o1.getDepartureTime().isAfter(o2.getDepartureTime())) {
			return -1;
		} else {
			return 0;
		}
	}
	
	

}
