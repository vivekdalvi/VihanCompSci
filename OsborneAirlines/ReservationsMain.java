package OsborneAirlines;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationsMain {
    final static String airportfilepath1 = ".\\OsborneAirlines\\airports.txt";
    final static String flightfilepath1 = ".\\OsborneAirlines\\flights.txt";
    final static String passengerfilepath1 = ".\\OsborneAirlines\\passengers.txt";

    public static void main(String[] args) throws FileNotFoundException {
        // reading airport data
        ArrayList<Airport> airports = new ArrayList<Airport>();
        File airportfile = new File(airportfilepath1);
        System.out.println("File exists is: " + airportfile.exists());
        if (airportfile.exists()) {
            System.out.println("file has " + airportfile.length() + " bytes");
            Scanner fileReader = new Scanner(airportfile);
            while (fileReader.hasNextLine()) {
                String[] airportdata = fileReader.nextLine().split(",");
                Airport a = new Airport(airportdata[0], airportdata[1], airportdata[2], airportdata[3]);
                airports.add(a);
            }
            fileReader.close();
        }

        // TODO: read from a file list of flights including filght date
        // TODO: read from a file lsit of reservations along with passenger

    }
}
