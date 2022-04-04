package OsborneAirlines;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ReservationsMain {

    private final static String _airportfilepath = ".\\OsborneAirlines\\airports.txt";
    private final static String _flightfilepath = ".\\OsborneAirlines\\flights.txt";
    private final static String _reservationfilepath = ".\\OsborneAirlines\\reservations.txt";
    private final static ArrayList<Flight> _flights = new ArrayList<Flight>();
    private final static ArrayList<Airport> _airports = new ArrayList<Airport>();
    private final static ArrayList<Reservation> _reservations = new ArrayList<Reservation>();
    private final static Util utility = new Util(_airports, _flights, _reservations);
    private final static Scanner kbreader = new Scanner(System.in);
    private final static UserInterface _ux = new UserInterface(_airports, _flights, _reservations, kbreader, utility);

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
                    Airport departureairport = utility.FindAirport(flightdata[0]);
                    FlightDate flightdate = new FlightDate(
                            // arrival time
                            flightdata[1].toUpperCase(),
                            // departure time
                            flightdata[2].toUpperCase());
                    Airport arrivalairport = utility.FindAirport(flightdata[3]);
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
                    Flight f = utility.FindFlight(reservationdata[1]);
                    String[] passengerdata = fileReader.nextLine().split(",");
                    Reservation r = new Reservation(reservationnumber, f, passengerdata);
                    _reservations.add(r);
                }

            }
            fileReader.close();
        }
    }

    public static void WriteFile(String filepath, DataType datatype) throws FileNotFoundException {
        try {
            FileWriter writer = new FileWriter(filepath);

            if (datatype == DataType.AIRPORT) {
                writer.write("//Name, City, State, Timezone\n");
                for (Airport airport : _airports) {
                    writer.write(airport.getName() + ","
                            + airport.getCity() + ","
                            + airport.getState() + ","
                            + airport.getTimeZoneId() + "\n");
                }
            } else if (datatype == DataType.FLIGHT) {
                writer.write(
                        "//departure airport, departure time, arrival time, arrival airport, flightnumber, capacity\n");
                for (Flight flight : _flights) {

                    writer.write(flight.getDepartureAirport().getName() + ","
                            + flight.getFlightDate().getDeparture().toString() + ","
                            + flight.getFlightDate().getArrival().toString() + ","
                            + flight.getArrivalAirport().getName() + ","
                            + flight.getFlightNumber() + ","
                            + flight.getCapacity() + "\n");
                }
            } else if (datatype == DataType.RESERVATION) {
                writer.write("//reservation number, flightnumber, passenger list\n");
                for (Reservation reservation : _reservations) {
                    writer.write(reservation.getReservationNumber() + ","
                            + reservation.getFlight().getFlightNumber() + "\n");
                    for (Passenger passenger : reservation.getPassengerList()) {
                        writer.write(passenger.getFileName() + ",");
                    }
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // reading data
        ReadFile(_airportfilepath, DataType.AIRPORT);
        ReadFile(_flightfilepath, DataType.FLIGHT);
        ReadFile(_reservationfilepath, DataType.RESERVATION);

        int input = 9;

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
                utility.PrintList(_reservations);
            } else if (input == 2) {
                utility.PrintList(_flights);
            } else if (input == 3) {
                utility.PrintList(_airports);
            } else if (input == 4) {
                for (Reservation reservation : _reservations) {
                    System.out.println(TextColor.ANSI_RED + "Passenger List for "
                            + reservation.getFlight().getFlightNumber() + TextColor.ANSI_RESET + "\n");
                    utility.PrintList(reservation.getPassengerList());
                }
            } else if (input == 5) {
                System.out.print(TextColor.ANSI_RED + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = utility.FindFlight(kbreader.nextLine().toUpperCase());
                if (f != null) {
                    System.out.println(
                            TextColor.ANSI_RED + "Passengers for " + f.getFlightNumber() + TextColor.ANSI_RESET);
                    ArrayList<Passenger> passengers = utility.FindPassengersForFlight(f.getFlightNumber());
                    utility.PrintList(passengers);
                } else {
                    System.out.println(TextColor.ANSI_RED + "Flight Does not exist" + TextColor.ANSI_RESET);
                }

            } else {
                input = 0;
            }
        } while (input != 0);
        // MainMenu(kbreader);
    }

    private static void SaveMenu(Scanner kbreader) throws FileNotFoundException {
        // WriteFile(".\\OsborneAirlines\\airports1.txt", DataType.AIRPORT);
        // WriteFile(".\\OsborneAirlines\\flights1.txt", DataType.FLIGHT);
        // WriteFile(".\\OsborneAirlines\\reservations1.txt", DataType.RESERVATION);

        WriteFile(_airportfilepath, DataType.AIRPORT);
        WriteFile(_flightfilepath, DataType.FLIGHT);
        WriteFile(_reservationfilepath, DataType.RESERVATION);
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
                Collections.sort(_reservations);
                utility.PrintList(_reservations);
            } else if (input == 2) {
                // search by reservation number
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Reservation Number: " + TextColor.ANSI_RESET);
                if (kbreader.hasNextInt()) {
                    Reservation r = utility.FindReservation(Integer.parseInt(kbreader.nextLine()));
                    if (r != null) {
                        System.out.println(r);
                    } else {
                        System.out.println(TextColor.ANSI_BLUE + "Reservation Does not exist" + TextColor.ANSI_RESET);
                    }
                }
            } else if (input == 3) {
                // search by customer name
                System.out.println("\nDetails for Passenger ");
                System.out.print(TextColor.ANSI_BLUE + "Enter First Name: " + TextColor.ANSI_RESET);
                String firstname = kbreader.nextLine().toUpperCase();
                System.out.print(TextColor.ANSI_BLUE + "Enter Last Name: " + TextColor.ANSI_RESET);
                String lastname = kbreader.nextLine().toUpperCase();
                ArrayList<Reservation> customerreservations = utility.FindReservationForPassenger(firstname, lastname);
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation + "\n");
                }
            } else if (input == 4) {
                // create reservation
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = utility.FindFlight(kbreader.nextLine().toUpperCase());
                if (f != null) {
                    _ux.Ux_CreateReservation(f);
                }
            } else if (input == 5) {
                // delete reservation
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Reservation Number: " + TextColor.ANSI_RESET);
                if (kbreader.hasNextInt()) {
                    Reservation r = utility.FindReservation(Integer.parseInt(kbreader.nextLine()));
                    if (r != null) {
                        _reservations.remove(r);
                        System.out.println(TextColor.ANSI_BLUE + "Below Reservation is Removed....");
                        System.out.println(r + TextColor.ANSI_RESET);
                    } else {
                        System.out.println(TextColor.ANSI_BLUE + "Reservation Does not exist" + TextColor.ANSI_RESET);
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
            System.out.println("5: Delete Flight");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                Collections.sort(_flights);
                utility.PrintList(_flights);

            } else if (input == 2) {
                // search by flight number
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = utility.FindFlight(kbreader.nextLine().toUpperCase());
                if (f != null) {
                    System.out.println(f);
                } else {
                    System.out.println(TextColor.ANSI_BLUE + "Flight Does not exist" + TextColor.ANSI_RESET);
                }
            } else if (input == 3) {
                // search by customer name
                System.out.println("\nDetails for Passenger ");
                System.out.print(TextColor.ANSI_BLUE + "Enter First Name: " + TextColor.ANSI_RESET);
                String firstname = kbreader.nextLine().toUpperCase();
                System.out.print(TextColor.ANSI_BLUE + "Enter Last Name: " + TextColor.ANSI_RESET);
                String lastname = kbreader.nextLine().toUpperCase();
                ArrayList<Reservation> customerreservations = utility.FindReservationForPassenger(firstname, lastname);
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation.getFlight() + "\n");
                }
            } else if (input == 4) {
                // create flight
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                String flightnumber = kbreader.nextLine().toUpperCase();
                Flight f = utility.FindFlight(flightnumber);
                if (f == null) {
                    // departure airport
                    System.out.print(TextColor.ANSI_BLUE + "\nDeparture Airport: " + TextColor.ANSI_RESET);
                    Airport departureairport = _ux.Ux_CreateAirport();
                    if (departureairport != null) {
                        // arrival airport
                        System.out.print(TextColor.ANSI_BLUE + "\nArrival Airport" + TextColor.ANSI_RESET);
                        Airport arrivalairport = _ux.Ux_CreateAirport();
                        if (arrivalairport != null) {
                            // create new flight
                            _ux.Ux_CreateFlight(departureairport, arrivalairport, flightnumber);
                        } else {
                            System.out.println(TextColor.ANSI_RED + "Please go to Airport menu and Create Airport\n"
                                    + TextColor.ANSI_RESET);
                        }
                    } else {
                        System.out.println(TextColor.ANSI_RED + "Please go to Airport menu and Create Airport\n"
                                + TextColor.ANSI_RESET);
                    }
                } else {
                    System.out.println(TextColor.ANSI_RED + "FLIGHT ALREADY EXISTS!" + TextColor.ANSI_RESET);
                }
            } else if (input == 5) {
                // Delete flight
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                String flightnumber = kbreader.nextLine().toUpperCase();
                Flight f = utility.FindFlight(flightnumber);
                if (f != null) {
                    _flights.remove(f);
                    System.out.println("Following Flight Deleted");
                    System.out.println(f);
                } else {
                    System.out.println(TextColor.ANSI_RED + "FLIGHT DOESN'T EXIST!" + TextColor.ANSI_RESET);
                }

            } else {
                input = 0;
            }
        } while (input != 0);
    }

    private static void PassengerMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_BLUE + "\n\nMAIN > PASSENGERS:" + TextColor.ANSI_GREEN);
            System.out.println("1: Passengers by Alphabetical Order");
            System.out.println("2: Search by Name");
            System.out.println("3: Create New Passenger");
            System.out.println("4: Delete Passenger");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                System.out.println(TextColor.ANSI_BLUE + "NOT IMPLEMENTED" + TextColor.ANSI_RESET);
            } else if (input == 2) {
                // search by name
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Airport Name: " + TextColor.ANSI_RESET);
                Airport a = utility.FindAirport(kbreader.nextLine().toUpperCase());
                if (a != null) {
                    System.out.println(a);
                } else {
                    System.out.println(TextColor.ANSI_BLUE + "Airport Does not exist" + TextColor.ANSI_RESET);
                }
            } else if (input == 3) {
                // Create New passenger
                // _ux.Ux_CreatePassenger();
                System.out.println(TextColor.ANSI_BLUE + "NOT IMPLEMENTED" + TextColor.ANSI_RESET);
            } else {
                input = 0;
            }
        } while (input != 0);
    }

    private static void AirportMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_BLUE + "\n\nMAIN > AIRPORTS:" + TextColor.ANSI_GREEN);
            System.out.println("1: Airport by Alphabetical Order");
            System.out.println("2: Search by Name");
            System.out.println("3: Create New Airport");
            System.out.println("4: Delete Airport");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            if (kbreader.hasNextInt()) {
                input = Integer.parseInt(kbreader.nextLine());
            } else {
                input = 0;
            }
            if (input == 1) {
                // print flight by alhpabetical order
                Collections.sort(_airports);
                utility.PrintList(_airports);
            } else if (input == 2) {
                // search by name
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Airport Name: " + TextColor.ANSI_RESET);
                Airport a = utility.FindAirport(kbreader.nextLine().toUpperCase());
                if (a != null) {
                    System.out.println(a);
                } else {
                    System.out.println(TextColor.ANSI_BLUE + "Airport Does not exist" + TextColor.ANSI_RESET);
                }
            } else if (input == 3) {
                // Create New Airport
                _ux.Ux_CreateAirport();
            } else if (input == 4) {
                // TODO: DELETE AIRPORT
                System.out.println(TextColor.ANSI_BLUE + "NOT IMPLEMENTED" + TextColor.ANSI_RESET);

            } else {
                input = 0;
            }
        } while (input != 0);
    }
}