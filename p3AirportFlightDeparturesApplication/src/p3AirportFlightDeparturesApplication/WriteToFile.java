/**
 * 
 */
package p3AirportFlightDeparturesApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class WriteToFile implements Runnable {

	@Override
	public void run() {

		boolean isRunning = true;

		do {

			File mydir = new File("FLIGHTS_SNAPSHOT");

			mydir.mkdir();

			String fileName = (mydir.getName() + ("/CLOSED_" + LocalTime.now() + ".csv"));

			File file = new File(fileName);

			try {
				isRunning = false;
				
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);

				StringBuilder sb = new StringBuilder();

				sb.append("Flight number, Destination, Departure Time");
				sb.append("\n");

				for (Flight f : DeparturesControl.allFlights) {
					if (f instanceof CommercialFlight) {
						CommercialFlight c = (CommercialFlight) f;
						if (c.getBoardingStatus() == BoardingStatus.CLOSED) {
							sb.append(c.getFlightNumber() + "," + c.getDestination() + "," + c.getDepartureTime());
							sb.append("\n");
						}
					}
				}

				bw.write(sb.toString());
				
				if (Thread.currentThread().isInterrupted()) {
					bw.close();
					fw.close();
				}

				bw.close();
				fw.close();

				try {
					Thread.sleep(60000);
					isRunning = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				file.delete();
				
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (isRunning == true);

	}

}
