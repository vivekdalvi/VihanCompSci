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
    private final static Scanner kbreader = new Scanner(System.in);
    // private final static UserInterface _ux = new UserInterface(_airports,
    // _flights, _reservations, kbreader);

    public static enum DataType {
        AIRPORT, FLIGHT, RESERVATION
    };

    // Reads file at the startup
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
                    Airport departureairport = Airport.FindAirport(flightdata[0], _airports);
                    Airport arrivalairport = Airport.FindAirport(flightdata[3], _airports);
                    if (departureairport != null && arrivalairport != null) {
                        FlightDate flightdate = new FlightDate(
                                // arrival time
                                flightdata[1].toUpperCase(),
                                // departure time
                                flightdata[2].toUpperCase());
                        Flight f = new Flight(departureairport, arrivalairport, flightdate,
                                // flight number
                                flightdata[4].toUpperCase(),
                                // capacity
                                Integer.parseInt(flightdata[5]));
                        _flights.add(f);
                    }
                } else if (datatype == DataType.RESERVATION) {
                    String[] reservationdata = fileReader.nextLine().split(",");
                    // reservation number, flightnumber, passenger list
                    // int reservationnumber = Integer.parseInt(reservationdata[0]);
                    Flight f = Flight.FindFlight(reservationdata[1], _flights);
                    if (f != null) {
                        String[] passengerdata = fileReader.nextLine().split(",");
                        Reservation r = new Reservation(reservationdata[0], f, passengerdata);
                        _reservations.add(r);
                    }

                }

            }
            fileReader.close();
        }
    }

    // Writes files when user selects safe menu
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

        int input = -5;
        // main input loop
        do {
            MainMenu(kbreader);
            input = ReadMenuInput();
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

    // This handles Main menu
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

    // This handles Print menu
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
            input = ReadMenuInput();
            if (input == 1) {
                System.out.println(
                        TextColor.ANSI_BLUE + "************RESERVATION LIST************" + TextColor.ANSI_RESET);
                PrintList(_reservations);
            } else if (input == 2) {
                System.out.println(TextColor.ANSI_BLUE + "************FLIGHT LIST************" + TextColor.ANSI_RESET);
                PrintList(_flights);
            } else if (input == 3) {
                System.out.println(TextColor.ANSI_BLUE + "************AIRPORT LIST************" + TextColor.ANSI_RESET);
                PrintList(_airports);
            } else if (input == 4) {
                for (Reservation reservation : _reservations) {
                    System.out.println(TextColor.ANSI_RED + "Passenger List for "
                            + reservation.getFlight().getFlightNumber() + TextColor.ANSI_RESET + "\n");
                    PrintList(reservation.getPassengerList());
                }
            } else if (input == 5) {
                System.out.print(TextColor.ANSI_RED + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Flight.FindFlight(kbreader.nextLine().toUpperCase(), _flights);
                if (f != null) {
                    System.out.println(
                            TextColor.ANSI_RED + "Passengers for " + f.getFlightNumber() + TextColor.ANSI_RESET);
                    ArrayList<Passenger> passengers = Passenger.FindPassengersForFlight(f.getFlightNumber(),
                            _reservations);
                    PrintList(passengers);
                } else {
                    System.out.println(TextColor.ANSI_RED + "Flight Does not exist" + TextColor.ANSI_RESET);
                }

            } else {
                input = 0;
            }
        } while (input != 0);
        // MainMenu(kbreader);
    }

    // This handles Save menu
    private static void SaveMenu(Scanner kbreader) throws FileNotFoundException {
        // WriteFile(".\\OsborneAirlines\\airports1.txt", DataType.AIRPORT);
        // WriteFile(".\\OsborneAirlines\\flights1.txt", DataType.FLIGHT);
        // WriteFile(".\\OsborneAirlines\\reservations1.txt", DataType.RESERVATION);

        WriteFile(_airportfilepath, DataType.AIRPORT);
        WriteFile(_flightfilepath, DataType.FLIGHT);
        WriteFile(_reservationfilepath, DataType.RESERVATION);
        System.out.println(TextColor.ANSI_BLUE + "All Flight, Airport & Reservation Details are Written to Files"
                + TextColor.ANSI_RESET);
    }

    // This handles Reservation menu
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
            input = ReadMenuInput();
            if (input == 1) {
                Collections.sort(_reservations);
                PrintList(_reservations);
            } else if (input == 2) {
                // search by reservation number
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Reservation Number: " + TextColor.ANSI_RESET);
                Reservation r = Reservation.FindReservation(kbreader.nextLine(), _reservations);
                if (r != null) {
                    System.out.println(r);
                } else {
                    System.out.println(TextColor.ANSI_BLUE + "Reservation Does not exist" + TextColor.ANSI_RESET);
                }
            } else if (input == 3) {
                // search by customer name
                Passenger p = Passenger.Ux_CreatePassenger(kbreader, _reservations);
                ArrayList<Reservation> customerreservations = Passenger.FindReservationForPassenger(p.getFirstName(),
                        p.getLastName(), _reservations);
                if (customerreservations.size() == 0) {
                    System.out.println(TextColor.ANSI_RED + "No customer with that name found!" + TextColor.ANSI_RESET);
                }
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation + "\n");
                }
            } else if (input == 4) {
                // Create reservation
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Flight.FindFlight(kbreader.nextLine().toUpperCase(), _flights);
                if (f != null) {
                    Reservation.Ux_CreateReservation(kbreader, _reservations, f);
                } else {
                    System.out.println(TextColor.ANSI_RED + "Flight does not exist!" + TextColor.ANSI_RESET);
                }
            } else if (input == 5) {
                // delete reservation
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Reservation Number: " + TextColor.ANSI_RESET);
                Reservation r = Reservation.FindReservation(kbreader.nextLine(), _reservations);
                if (r != null) {
                    _reservations.remove(r);
                    System.out.println(TextColor.ANSI_BLUE + "Below Reservation is Removed....");
                    System.out.println(r + TextColor.ANSI_RESET);
                } else {
                    System.out.println(TextColor.ANSI_BLUE + "Reservation Does not exist" + TextColor.ANSI_RESET);
                }
            } else {
                input = 0;
            }
        } while (input != 0);

    }

    // This handles Fight menu
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
            input = ReadMenuInput();
            if (input == 1) {
                Collections.sort(_flights);
                PrintList(_flights);

            } else if (input == 2) {
                // search by flight number
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = Flight.FindFlight(kbreader.nextLine().toUpperCase(), _flights);
                if (f != null) {
                    System.out.println(f);
                } else {
                    System.out.println(TextColor.ANSI_BLUE + "Flight Does not exist" + TextColor.ANSI_RESET);
                }
            } else if (input == 3) {
                // search by customer name
                Passenger p = Passenger.Ux_CreatePassenger(kbreader, _reservations);
                ArrayList<Reservation> customerreservations = Passenger.FindReservationForPassenger(p.getFirstName(),
                        p.getLastName(), _reservations);
                if (customerreservations.size() == 0) {
                    System.out.println(TextColor.ANSI_RED + "No customer with that name found!" + TextColor.ANSI_RESET);
                }
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation.getFlight() + "\n");
                }
            } else if (input == 4) {
                // create flight
                System.out.print(TextColor.ANSI_BLUE + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                String flightnumber = kbreader.nextLine().toUpperCase();
                Flight f = Flight.FindFlight(flightnumber, _flights);
                if (f == null) {
                    // departure airport
                    System.out.print(TextColor.ANSI_BLUE + "\nDeparture Airport: " + TextColor.ANSI_RESET);
                    Airport departureairport = Airport.Ux_CreateAirport(kbreader, _airports);
                    if (departureairport != null) {
                        // arrival airport
                        System.out.print(TextColor.ANSI_BLUE + "\nArrival Airport" + TextColor.ANSI_RESET);
                        Airport arrivalairport = Airport.Ux_CreateAirport(kbreader, _airports);
                        if (arrivalairport != null) {
                            // create new flight
                            Flight.Ux_CreateFlight(kbreader, departureairport, arrivalairport, flightnumber, _flights);
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
                Flight f = Flight.FindFlight(flightnumber, _flights);
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

    // This handles Passenger menu
    private static void PassengerMenu(Scanner kbreader) {
        int input = 7;
        do {
            System.out.println(TextColor.ANSI_BLUE + "\n\nMAIN > PASSENGERS:" + TextColor.ANSI_GREEN);
            System.out.println("1: Passengers by Alphabetical Order");
            System.out.println("2: Search by Name");
            System.out.println("3: Delete Passenger");
            System.out.println("ANYTHING ELSE: <-- Go Back to Main Menu");
            System.out.print("Please Enter Menu Number: " + TextColor.ANSI_RESET);
            input = ReadMenuInput();
            if (input == 1) {
                // TODO: Passenger List sorted
                System.out.println(TextColor.ANSI_BLUE + "NOT IMPLEMENTED" + TextColor.ANSI_RESET);
            } else if (input == 2) {
                Passenger p = Passenger.Ux_CreatePassenger(kbreader, _reservations);
                ArrayList<Reservation> customerreservations = Passenger.FindReservationForPassenger(p.getFirstName(),
                        p.getLastName(), _reservations);
                if (customerreservations.size() == 0) {
                    System.out.println(TextColor.ANSI_RED + "No customer with that name found!" + TextColor.ANSI_RESET);
                }
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation + "\n");
                }
            } else if (input == 3) {
                Passenger p = Passenger.Ux_CreatePassenger(kbreader, _reservations);
                ArrayList<Reservation> customerreservations = Passenger.FindReservationForPassenger(p.getFirstName(),
                        p.getLastName(), _reservations);
                if (customerreservations.size() == 0) {
                    System.out.println(TextColor.ANSI_RED + "No customer with that name found!" + TextColor.ANSI_RESET);
                }
                for (Reservation reservation : customerreservations) {
                    System.out.println(TextColor.ANSI_RED + "Above Passenger deleted from Reservation "
                            + reservation.getReservationNumber() + TextColor.ANSI_RESET);
                    reservation.getPassengerList()
                            .remove(Passenger.FindPassenger(p.getFirstName(), p.getLastName(), _reservations));
                }
            } else {
                input = 0;
            }
        } while (input != 0);
    }

    // This handles Airport menu
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
            input = ReadMenuInput();
            if (input == 1) {
                // print flight by alhpabetical order
                Collections.sort(_airports);
                PrintList(_airports);
            } else if (input == 2) {
                // search by name
                Airport.Ux_FindAirport(kbreader, _airports);
            } else if (input == 3) {
                // Create New Airport
                Airport.Ux_CreateAirport(kbreader, _airports);
            } else if (input == 4) {
                // delete airport
                Airport.Ux_DeleteAirport(kbreader, _airports);
            } else {
                input = 0;
            }
        } while (input != 0);
    }

    // This is a common function to handle wrong input in menu (such as return key
    // or letters instead of numbers)
    private static int ReadMenuInput() {
        if (kbreader.hasNextInt()) {
            String inputstring = kbreader.nextLine();
            if (!inputstring.equals("")) {
                return Integer.parseInt(inputstring);
            }
        }
        return 0;
    }

    protected static void PrintList(Iterable list) {
        System.out.println();
        if (list == null) {
            System.out.println(TextColor.ANSI_RED + "List is empty" + TextColor.ANSI_RESET);
            return;
        }
        for (Object object : list) {
            System.out.println(object + "\n");
        }
    }

}