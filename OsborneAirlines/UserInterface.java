package OsborneAirlines;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // private ArrayList<Airport> _airports = null;
    // private ArrayList<Flight> _flights = null;
    // private ArrayList<Reservation> _reservations = null;
    // private Scanner _kbreader = null;

    // public static enum AirportType {
    // ARRIVAL, DEPARTURE, GENERAL
    // };

    // public UserInterface(ArrayList<Airport> airports, ArrayList<Flight> flights,
    // ArrayList<Reservation> reservations,
    // Scanner kbreader) {
    // // _airports = airports;
    // // _flights = flights;
    // // _reservations = reservations;
    // // _kbreader = kbreader;
    // }

    // protected Passenger Ux_CreatePassenger() {
    // System.out.println("\nDetails for Passenger ");
    // System.out.print(TextColor.ANSI_BLUE + "Enter First Name: " +
    // TextColor.ANSI_RESET);
    // String firstname = _kbreader.nextLine().toUpperCase();
    // System.out.print(TextColor.ANSI_BLUE + "Enter Last Name: " +
    // TextColor.ANSI_RESET);
    // String lastname = _kbreader.nextLine().toUpperCase();
    // Passenger p = Passenger.FindPassenger(firstname, lastname, _reservations);
    // if (p == null) {
    // p = new Passenger(firstname, lastname);
    // }
    // return p;
    // }

    // protected Flight Ux_CreateFlight(Airport departureairport, Airport
    // arrivalairport, String flightnumber) {
    // System.out.print(TextColor.ANSI_BLUE + "\nPlease Enter Departure Date
    // [YYYY-MM-DDTHH:MM]: "
    // + TextColor.ANSI_RESET);
    // String ad = _kbreader.nextLine().toUpperCase();
    // System.out.print(TextColor.ANSI_BLUE + "\nPlease Enter Arrival Date
    // [YYYY-MM-DDTHH:MM]: "
    // + TextColor.ANSI_RESET);
    // String dd = _kbreader.nextLine().toUpperCase();
    // FlightDate flightdate = new FlightDate(ad, dd);
    // System.out.print(
    // TextColor.ANSI_BLUE + "\nPlease Enter Flight Capacity: " +
    // TextColor.ANSI_RESET);
    // Flight f = new Flight(departureairport, arrivalairport, flightdate,
    // flightnumber,
    // Integer.parseInt(_kbreader.nextLine().toUpperCase()));
    // _flights.add(f);
    // return f;
    // }

    // protected Reservation Ux_CreateReservation(Flight f) {
    // int customerinput = 9;
    // ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    // System.out.print(TextColor.ANSI_BLUE + "Please Enter Reservation Number: " +
    // TextColor.ANSI_RESET);
    // Reservation r = new Reservation(_kbreader.nextLine(), f, passengers);
    // while (customerinput != 0) {
    // Passenger p = Passenger.Ux_CreatePassenger(_kbreader, _reservations);
    // r.getPassengerList().add(p);
    // System.out.print(TextColor.ANSI_BLUE + "Do you want to add another customer?
    // [yes: 1 /No: 0]"
    // + TextColor.ANSI_RESET);
    // if (_kbreader.hasNextInt()) {
    // String inputstring = _kbreader.nextLine();
    // customerinput = Integer.parseInt(inputstring);
    // }
    // }
    // _reservations.add(r);
    // return r;
    // }

}
