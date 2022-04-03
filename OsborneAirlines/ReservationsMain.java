package OsborneAirlines;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
                    Airport a = new Airport(
                            // name
                            airportdata[0].toUpperCase(),
                            // city
                            airportdata[1].toUpperCase(),
                            // state
                            airportdata[2].toUpperCase(),
                            // timezone: can not convert to uppercase
                            airportdata[3]);
                    _airports.add(a);
                } else if (datatype == DataType.FLIGHT) {
                    String[] flightdata = fileReader.nextLine().split(",");
                    // departure airport, departure time, arrival time, arrival airport,
                    // flightnumber, capacity
                    Airport departureairport = Util.FindAirport(flightdata[0], _airports, _flights, _reservations);
                    FlightDate flightdate = new FlightDate(
                            // arrival time
                            flightdata[1].toUpperCase(),
                            // departure time
                            flightdata[2].toUpperCase());
                    Airport arrivalairport = Util.FindAirport(flightdata[3], _airports, _flights, _reservations);
                    Flight f = new Flight(departureairport, arrivalairport, flightdate,
                            // flight number
                            flightdata[4].toUpperCase(),
                            // capacity
                            Integer.parseInt(flightdata[5]));
                    _flights.add(f);
                } else if (datatype == DataType.RESERVATION) {
                    String[] reservationdata = fileReader.nextLine().split(",");
                    // reservation number, flightnumber, passenger list
                    int reservationnumber = Integer.parseInt(reservationdata[0]);
                    Flight f = Util.FindFlight(reservationdata[1], _airports, _flights, _reservations);
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
            } else if (input == 5) {
                PassengerMenu(kbreader);
                input = 9;
            } else if (input == 6) {
                AirportMenu(kbreader);
                input = 9;
            } else {
                break;
            }
        } while (input != 0);
        kbreader.close();
    }

    private static void MainMenu(Scanner kbreader) {
        System.out.println(TextColor.ANSI_GREEN + "\n\nPlease select one of the options below...");
        System.out.println("1: Print");
        System.out.println("2: Save All Files");
        System.out.println("3: Manage Reservations");
        System.out.println("4: Manage Flights");
        System.out.println("5: Manage Passengers");
        System.out.println("6: Manage Airports");
        System.out.println("ANYTHING ELSE: Exit");
        System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
    }

    private static void PrintMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_RED + "\n\nMAIN > PRINT:" + TextColor.ANSI_GREEN);
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
                Util.PrintList(_reservations);
            } else if (input == 2) {
                Util.PrintList(_flights);
            } else if (input == 3) {
                Util.PrintList(_airports);
            } else if (input == 4) {
                for (Reservation reservation : _reservations) {
                    System.out.println(TextColor.ANSI_RED + "Passenger List for "
                            + reservation.getFlight().getFlightNumber() + TextColor.ANSI_RESET + "\n");
                    Util.PrintList(reservation.getPassengerList());
                }
            } else if (input == 5) {
                System.out.print(TextColor.ANSI_RED + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Util.FindFlight(kbreader.nextLine().toUpperCase(), _airports, _flights, _reservations);
                if (f != null) {
                    System.out.println(
                            TextColor.ANSI_RED + "Passengers for " + f.getFlightNumber() + TextColor.ANSI_RESET);
                    ArrayList<Passenger> passengers = Util.FindPassengersForFlight(f.getFlightNumber(), _airports,
                            _flights, _reservations);
                    Util.PrintList(passengers);
                } else {
                    System.out.println(TextColor.ANSI_RED + "Flight Does not exist" + TextColor.ANSI_RESET);
                }

            } else {
                input = 0;
            }
        } while (input != 0);
        // MainMenu(kbreader);
    }

    private static void SaveMenu(Scanner kbreader) {
        System.out.println(TextColor.ANSI_RED + "Not Implemented" + TextColor.ANSI_RESET);
        MainMenu(kbreader);
    }

    private static void ReservationMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_RED + "\n\nMAIN > RESERVATION:" + TextColor.ANSI_GREEN);
            System.out.println("1: Reservations by Alphabetical Order");
            System.out.println("2: Search by Reservation Number");
            System.out.println("3: Search by Customer Name");
            System.out.println("4: Create New Reservation");
            System.out.println("5: Delete Reservation");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                // print reservation by alhpabetical order
                // dont understand this
            } else if (input == 2) {
                // search by reservation number
                System.out.print(TextColor.ANSI_RED + "\nEnter Reservation Number: " + TextColor.ANSI_RESET);
                if (kbreader.hasNextInt()) {
                    Reservation r = Util.FindReservation(Integer.parseInt(kbreader.nextLine()), _airports, _flights,
                            _reservations);
                    if (r != null) {
                        System.out.println(r);
                    } else {
                        System.out.println(TextColor.ANSI_RED + "Reservation Does not exist" + TextColor.ANSI_RESET);
                    }
                }
            } else if (input == 3) {
                // search by customer name
                System.out.println("\nDetails for Passenger ");
                System.out.print(TextColor.ANSI_RED + "Enter First Name: " + TextColor.ANSI_RESET);
                String firstname = kbreader.nextLine().toUpperCase();
                System.out.print(TextColor.ANSI_RED + "Enter Last Name: " + TextColor.ANSI_RESET);
                String lastname = kbreader.nextLine().toUpperCase();
                ArrayList<Reservation> customerreservations = Util.FindReservationForPassenger(firstname, lastname,
                        _airports, _flights, _reservations);
                System.out.print(TextColor.ANSI_YELLOW);
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation + "\n");
                }
                System.out.print(TextColor.ANSI_RESET);
            } else if (input == 4) {
                // create new reservation
                System.out.print(TextColor.ANSI_RED + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Util.FindFlight(kbreader.nextLine().toUpperCase(), _airports, _flights, _reservations);
                if (f != null) {
                    int customerinput = 9;
                    ArrayList<Passenger> passengers = new ArrayList<Passenger>();
                    Reservation r = new Reservation(_reservations.size() + 1, f, passengers);
                    while (customerinput != 0) {
                        System.out.println("\nDetails for Passenger " + (passengers.size() + 1));
                        System.out.print(TextColor.ANSI_RED + "Enter First Name: " + TextColor.ANSI_RESET);
                        String firstname = kbreader.nextLine().toUpperCase();
                        System.out.print(TextColor.ANSI_RED + "Enter Last Name: " + TextColor.ANSI_RESET);
                        String lastname = kbreader.nextLine().toUpperCase();
                        Passenger p = Util.FindPassenger(firstname, lastname, _airports, _flights, _reservations);
                        if (p == null) {
                            r.getPassengerList().add(new Passenger(firstname, lastname));
                            System.out.println("New Passenger added");
                        } else {
                            r.getPassengerList().add(p);
                            System.out.println("Existing Passenger added");
                        }
                        System.out.print("Do you want to add another customer? [yes: 1 /No: 0]");
                        if (kbreader.hasNextInt()) {
                            customerinput = Integer.parseInt(kbreader.nextLine());
                        }
                    }
                    _reservations.add(r);
                }

            } else if (input == 5) {
                System.out.print(TextColor.ANSI_RED + "\nEnter Reservation Number: " + TextColor.ANSI_RESET);
                if (kbreader.hasNextInt()) {
                    Reservation r = Util.FindReservation(Integer.parseInt(kbreader.nextLine()), _airports, _flights,
                            _reservations);
                    if (r != null) {
                        _reservations.remove(r);
                        System.out.println(TextColor.ANSI_RED + "Below Reservation is Removed....");
                        System.out.println(r + TextColor.ANSI_RESET);
                    } else {
                        System.out.println(TextColor.ANSI_RED + "Reservation Does not exist" + TextColor.ANSI_RESET);
                    }
                }

            } else {
                input = 0;
            }
        } while (input != 0);

    }

    private static void FlightMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_BLUE + "\n\nMAIN > FLIGHTS:" + TextColor.ANSI_GREEN);
            System.out.println("1: Flights by Alphabetical Order");
            System.out.println("2: Search by Flight Number");
            System.out.println("3: Search by Customer Name");
            System.out.println("4: Create New Flight");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                // print flight by alhpabetical order
                // dont understand this
            } else if (input == 2) {
                // search by flight number
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Util.FindFlight(kbreader.nextLine().toUpperCase(), _airports, _flights, _reservations);
                if (f != null) {
                    System.out.println(f);
                }
            } else if (input == 3) {
                // search by customer name
                System.out.println("\nDetails for Passenger ");
                System.out.print(TextColor.ANSI_BLUE + "Enter First Name: " + TextColor.ANSI_RESET);
                String firstname = kbreader.nextLine().toUpperCase();
                System.out.print(TextColor.ANSI_BLUE + "Enter Last Name: " + TextColor.ANSI_RESET);
                String lastname = kbreader.nextLine().toUpperCase();
                ArrayList<Reservation> customerreservations = Util.FindReservationForPassenger(firstname, lastname,
                        _airports, _flights, _reservations);
                System.out.print(TextColor.ANSI_YELLOW);
                for (Reservation reservation : customerreservations) {
                    // System.out.println(reservation + "\n");
                    System.out.println(reservation.getFlight() + "\n");
                }
                System.out.print(TextColor.ANSI_RESET);
            } else if (input == 4) {
                // create new flight
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Util.FindFlight(kbreader.nextLine().toUpperCase(), _airports, _flights, _reservations);
                if (f == null) {
                    // TODO: New flight
                } else {
                    System.out.println(TextColor.ANSI_RED + "FLIGHT ALREADY EXISTS!" + TextColor.ANSI_RESET);
                }

            } else if (input == 5) {
                // delete flight

            } else {
                input = 0;
            }
        } while (input != 0);
    }

    private static void PassengerMenu(Scanner kbreader) {
        System.out.println(TextColor.ANSI_RED + "Not Implemented" + TextColor.ANSI_RESET);
        MainMenu(kbreader);
    }

    private static void AirportMenu(Scanner kbreader) {
        System.out.println(TextColor.ANSI_RED + "Not Implemented" + TextColor.ANSI_RESET);
        MainMenu(kbreader);
    }

}
