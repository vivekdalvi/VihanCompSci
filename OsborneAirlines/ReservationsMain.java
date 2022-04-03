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
                PrintList(_reservations);
            } else if (input == 2) {
                PrintList(_flights);
            } else if (input == 3) {
                PrintList(_airports);
            } else if (input == 4) {
                for (Reservation reservation : _reservations) {
                    System.out.println(TextColor.ANSI_YELLOW + "Passenger List for "
                            + reservation.getFlight().getFlightNumber() + TextColor.ANSI_RESET + "\n");
                    PrintList(reservation.getPassengerList());
                }
            } else if (input == 5) {
                System.out.print(TextColor.ANSI_RED + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = FindFlight(kbreader.nextLine());
                if (f != null) {
                    System.out.println(
                            TextColor.ANSI_YELLOW + "Passengers for " + f.getFlightNumber() + TextColor.ANSI_RESET);
                    ArrayList<Passenger> passengers = FindPassengersForFlight(f.getFlightNumber());
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

    private static void SaveMenu(Scanner kbreader) {
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
                    Reservation r = FindReservation(Integer.parseInt(kbreader.nextLine()));
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
                String firstname = kbreader.nextLine();
                System.out.print(TextColor.ANSI_RED + "Enter Last Name: " + TextColor.ANSI_RESET);
                String lastname = kbreader.nextLine();
                ArrayList<Reservation> customerreservations = FindReservationForPassenger(firstname, lastname);
                System.out.print(TextColor.ANSI_YELLOW);
                for (Reservation reservation : customerreservations) {
                    System.out.println(reservation + "\n");
                }
                System.out.print(TextColor.ANSI_RESET);
            } else if (input == 4) {
                // create new reservation
                System.out.print(TextColor.ANSI_RED + "\nEnter Flight Number: " + TextColor.ANSI_RESET);
                Flight f = FindFlight(kbreader.nextLine());
                if (f != null) {
                    int customerinput = 9;
                    ArrayList<Passenger> passengers = new ArrayList<Passenger>();
                    Reservation r = new Reservation(_reservations.size() + 1, f, passengers);
                    while (customerinput != 0) {
                        System.out.println("\nDetails for Passenger " + (passengers.size() + 1));
                        System.out.print(TextColor.ANSI_RED + "Enter First Name: " + TextColor.ANSI_RESET);
                        String firstname = kbreader.nextLine();
                        System.out.print(TextColor.ANSI_RED + "Enter Last Name: " + TextColor.ANSI_RESET);
                        String lastname = kbreader.nextLine();
                        Passenger p = FindPassenger(firstname, lastname);
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
                    Reservation r = FindReservation(Integer.parseInt(kbreader.nextLine()));
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

    private static Reservation FindReservation(int reservationnumber) {
        Reservation a = _reservations.stream()
                .filter(reservation -> reservationnumber == (reservation.getReservationNumber())).findAny()
                .orElse(null);
        return a;
    }

    private static Flight FindFlight(String flightnumber) {
        Flight f = _flights.stream().filter(flight -> flightnumber.equals(flight.getFlightNumber())).findAny()
                .orElse(null);
        return f;
    }

    private static Passenger FindPassenger(String firstname, String lastname) {
        for (Reservation r : _reservations) {
            Passenger p = r.getPassengerList().stream()
                    .filter(passenger -> (firstname + " " + lastname).equals(passenger.getName())).findAny()
                    .orElse(null);
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    private static ArrayList<Reservation> FindReservationForPassenger(String firstname, String lastname) {
        ArrayList<Reservation> reservationsforcustomer = new ArrayList<Reservation>();
        for (Reservation r : _reservations) {
            Passenger p = r.getPassengerList().stream()
                    .filter(passenger -> (firstname + " " + lastname).equals(passenger.getName())).findAny()
                    .orElse(null);
            if (p != null) {
                reservationsforcustomer.add(r);
            }
        }
        return reservationsforcustomer;
    }

    private static ArrayList<Passenger> FindPassengersForFlight(String flightnumber) {
        Reservation r = _reservations.stream()
                .filter(reservation -> flightnumber.equals(reservation.getFlight().getFlightNumber())).findAny()
                .orElse(null);
        if (r != null) {
            return r.getPassengerList();
        } else {
            return null;
        }
    }

    private static void PrintList(Iterable list) {
        System.out.println(TextColor.ANSI_YELLOW);
        for (Object object : list) {
            System.out.println(object + "\n");
        }
        System.out.print(TextColor.ANSI_RESET);
    }
}
