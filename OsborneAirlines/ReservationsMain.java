package OsborneAirlines;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReservationsMain {
    private final static String _airportfilepath = ".\\OsborneAirlines\\airports.txt";
    private final static String _flightfilepath = ".\\OsborneAirlines\\flights.txt";
    private final static String _reservationfilepath = ".\\OsborneAirlines\\reservations.txt";
    private final static ArrayList<Flight> _flights = new ArrayList<Flight>();
    private final static ArrayList<Airport> _airports = new ArrayList<Airport>();
    private final static ArrayList<Reservation> _reservations = new ArrayList<Reservation>();

    public static enum DataType {
        AIRPORT, FLIGHT, RESERVATION
    };

    public static void ReadFile(String filepath, DataType datatype) throws FileNotFoundException {
        File datafile = new File(filepath);
        // System.out.println("File exists is: " + datafile.exists());
        if (datafile.exists()) {
            // System.out.println("file has " + datafile.length() + " bytes");
            Scanner fileReader = new Scanner(datafile);
            fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                if (datatype == DataType.AIRPORT) {
                    String[] airportdata = fileReader.nextLine().split(",");
                    // Name, City, State, Timezone
                    Airport a = new Airport(airportdata[0], airportdata[1], airportdata[2], airportdata[3]);
                    _airports.add(a);
                } else if (datatype == DataType.FLIGHT) {
                    String[] flightdata = fileReader.nextLine().split(",");
                    // departure airport, departure time, arrival time, arrival airport,
                    // flightnumber, capacity
                    Airport departureairport = FindAirport(flightdata[0]);
                    FlightDate flightdate = new FlightDate(flightdata[1], flightdata[2]);
                    Airport arrivalairport = FindAirport(flightdata[3]);
                    Flight f = new Flight(departureairport, arrivalairport, flightdate, flightdata[4],
                            Integer.parseInt(flightdata[5]));
                    _flights.add(f);
                } else if (datatype == DataType.RESERVATION) {
                    String[] reservationdata = fileReader.nextLine().split(",");
                    // reservation number, flightnumber, passenger list
                    int reservationnumber = Integer.parseInt(reservationdata[0]);
                    Flight f = FindFlight(reservationdata[1]);
                    String[] passengerdata = fileReader.nextLine().split(",");
                    Reservation r = new Reservation(reservationnumber, f, passengerdata);
                    _reservations.add(r);
                }

            }
            fileReader.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // reading data
        ReadFile(_airportfilepath, DataType.AIRPORT);
        ReadFile(_flightfilepath, DataType.FLIGHT);
        ReadFile(_reservationfilepath, DataType.RESERVATION);

        int input = 9;
        Scanner kbreader = new Scanner(System.in);

        // main input loop
        do {
            MainMenu(kbreader);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                PrintMenu(kbreader);
                input = 9;
            } else if (input == 2) {
                SaveMenu(kbreader);
                input = 9;
            } else if (input == 3) {
                ReservationMenu(kbreader);
                input = 9;
            } else if (input == 4) {
                FlightMenu(kbreader);
                input = 9;
            } else {
                break;
            }
        } while (input != 0);

        kbreader.close();
    }

    private static void PrintMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_GREEN + "\n\nMAIN > PRINT:");
            System.out.println("1: Reservation List ");
            System.out.println("2: Flight List");
            System.out.println("3: Airports");
            System.out.println("4: Passengers");
            System.out.println("5: Passengers for a Flight");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                PrintList(_reservations);
            } else if (input == 2) {
                PrintList(_flights);
            } else if (input == 3) {
                PrintList(_airports);
            } else if (input == 4) {
                // print passenger list
            } else if (input == 5) {
                // print passtners for a flight
            } else {
                input = 0;
            }
        } while (input != 0);
        // MainMenu(kbreader);
    }

    private static void PrintList(Iterable list) {
        System.out.println(TextColor.ANSI_YELLOW + "\n\n");
        for (Object object : list) {
            System.out.println(object + "\n");
        }
        System.out.println("End of List" + TextColor.ANSI_RESET);
    }

    private static void SaveMenu(Scanner kbreader) {
        MainMenu(kbreader);
    }

    private static void ReservationMenu(Scanner kbreader) {
        MainMenu(kbreader);
    }

    private static void FlightMenu(Scanner kbreader) {
        MainMenu(kbreader);
    }

    private static void MainMenu(Scanner kbreader) {
        System.out.println(TextColor.ANSI_GREEN + "Please select one of the options below...");
        System.out.println("1: Print");
        System.out.println("2: Save All Files");
        System.out.println("3: Manage Reservations");
        System.out.println("4: Manage Flights");
        System.out.println("ANYTHING ELSE: Exit");
        System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
    }

    private static Airport FindAirport(String airportname) {
        Airport a = _airports.stream().filter(airport -> airportname.equals(airport.getName())).findAny().orElse(null);
        return a;
    }

    private static Flight FindFlight(String flightnumber) {
        Flight f = _flights.stream().filter(flight -> flightnumber.equals(flight.getFlightNumber())).findAny()
                .orElse(null);
        return f;
    }
}
