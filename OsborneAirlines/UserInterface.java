package OsborneAirlines;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private ArrayList<Airport> _airports = null;
    private ArrayList<Flight> _flights = null;
    private ArrayList<Reservation> _reservations = null;
    private Scanner _kbreader = null;
    private Util _utility = null;

    public static enum AirportType {
        ARRIVAL, DEPARTURE, GENERAL
    };

    public UserInterface(ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations,
            Scanner kbreader, Util utility) {
        _airports = airports;
        _flights = flights;
        _reservations = reservations;
        _kbreader = kbreader;
        _utility = utility;
    }

    protected Passenger Ux_CreatePassenger() {
        System.out.println("\nDetails for Passenger ");
        System.out.print(TextColor.ANSI_BLUE + "Enter First Name: " + TextColor.ANSI_RESET);
        String firstname = _kbreader.nextLine().toUpperCase();
        System.out.print(TextColor.ANSI_BLUE + "Enter Last Name: " + TextColor.ANSI_RESET);
        String lastname = _kbreader.nextLine().toUpperCase();
        Passenger p = _utility.FindPassenger(firstname, lastname);
        if (p == null) {
            p = new Passenger(firstname, lastname);
            System.out.println(TextColor.ANSI_BLUE + "New Passenger added" + TextColor.ANSI_RESET);
        }
        return p;
    }

    protected Flight Ux_CreateFlight(Airport departureairport, Airport arrivalairport, String flightnumber) {
        System.out.print(TextColor.ANSI_BLUE + "\nPlease Enter Departure Date [YYYY-MM-DDTHH:MM]: "
                + TextColor.ANSI_RESET);
        String ad = _kbreader.nextLine().toUpperCase();
        System.out.print(TextColor.ANSI_BLUE + "\nPlease Enter Arrival Date [YYYY-MM-DDTHH:MM]: "
                + TextColor.ANSI_RESET);
        String dd = _kbreader.nextLine().toUpperCase();
        FlightDate flightdate = new FlightDate(ad, dd);
        System.out.print(
                TextColor.ANSI_BLUE + "\nPlease Enter Flight Capacity: " + TextColor.ANSI_RESET);
        Flight f = new Flight(departureairport, arrivalairport, flightdate, flightnumber,
                Integer.parseInt(_kbreader.nextLine().toUpperCase()));
        _flights.add(f);
        return f;
    }

    protected Reservation Ux_CreateReservation(Flight f) {
        int customerinput = 9;
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        Reservation r = new Reservation(_reservations.size() + 1, f, passengers);
        while (customerinput != 0) {
            Passenger p = Ux_CreatePassenger();
            r.getPassengerList().add(p);
            System.out.print(TextColor.ANSI_BLUE + "Do you want to add another customer? [yes: 1 /No: 0]"
                    + TextColor.ANSI_RESET);
            if (_kbreader.hasNextInt()) {
                customerinput = Integer.parseInt(_kbreader.nextLine());
            }
        }
        _reservations.add(r);
        return r;
    }

    protected Airport Ux_CreateAirport() {
        System.out.print(TextColor.ANSI_BLUE + "\nAirport Name: " + TextColor.ANSI_RESET);
        String name = _kbreader.nextLine().toUpperCase();
        Airport airport = _utility.FindAirport(name);
        if (airport == null) {
            System.out.print(TextColor.ANSI_BLUE + "\nCity: " + TextColor.ANSI_RESET);
            String city = _kbreader.nextLine().toUpperCase();
            System.out.print(TextColor.ANSI_BLUE + "\nState: " + TextColor.ANSI_RESET);
            String state = _kbreader.nextLine().toUpperCase();
            System.out.print(TextColor.ANSI_BLUE + "\nTimeZone: " + TextColor.ANSI_RESET);
            String timezoneid = _kbreader.nextLine().toUpperCase();
            airport = new Airport(name, city, state, timezoneid);
            _airports.add(airport);
        } else {
            System.out.println(TextColor.ANSI_BLUE + "Airport Already Exists" + TextColor.ANSI_RESET);
        }
        return airport;
    }

}
