package OsborneAirlines;

import java.util.ArrayList;

public class Util {

    protected static Airport FindAirport(String airportname,
            ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        Airport a = airports.stream().filter(airport -> airportname.equals(airport.getName())).findAny().orElse(null);
        return a;
    }

    protected static Reservation FindReservation(int reservationnumber, ArrayList<Airport> airports,
            ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        Reservation a = reservations.stream()
                .filter(reservation -> reservationnumber == (reservation.getReservationNumber())).findAny()
                .orElse(null);
        return a;
    }

    protected static Flight FindFlight(String flightnumber,
            ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        Flight f = flights.stream().filter(flight -> flightnumber.equals(flight.getFlightNumber())).findAny()
                .orElse(null);
        return f;
    }

    protected static Passenger FindPassenger(String firstname, String lastname,
            ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        for (Reservation r : reservations) {
            Passenger p = r.getPassengerList().stream()
                    .filter(passenger -> (firstname + " " + lastname).equals(passenger.getName())).findAny()
                    .orElse(null);
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    protected static ArrayList<Reservation> FindReservationForPassenger(String firstname, String lastname,
            ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        ArrayList<Reservation> reservationsforcustomer = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            Passenger p = r.getPassengerList().stream()
                    .filter(passenger -> (firstname + " " + lastname).equals(passenger.getName())).findAny()
                    .orElse(null);
            if (p != null) {
                reservationsforcustomer.add(r);
            }
        }
        return reservationsforcustomer;
    }

    protected static ArrayList<Passenger> FindPassengersForFlight(String flightnumber,
            ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        Reservation r = reservations.stream()
                .filter(reservation -> flightnumber.equals(reservation.getFlight().getFlightNumber())).findAny()
                .orElse(null);
        if (r != null) {
            return r.getPassengerList();
        } else {
            return null;
        }
    }

    protected static void PrintList(Iterable list) {
        System.out.println();
        for (Object object : list) {
            System.out.println(object + "\n");
        }
    }
}
