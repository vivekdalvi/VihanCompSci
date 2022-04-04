package OsborneAirlines;

import java.util.ArrayList;

public class Util {

    ArrayList<Airport> _airports = null;
    ArrayList<Flight> _flights = null;
    ArrayList<Reservation> _reservations = null;

    public Util(ArrayList<Airport> airports, ArrayList<Flight> flights, ArrayList<Reservation> reservations) {
        _airports = airports;
        _flights = flights;
        _reservations = reservations;
    }

    protected Airport FindAirport(String airportname) {
        Airport a = _airports.stream().filter(airport -> airportname.equals(airport.getName())).findAny().orElse(null);
        return a;
    }

    protected Reservation FindReservation(int reservationnumber) {
        Reservation a = _reservations.stream()
                .filter(reservation -> reservationnumber == (reservation.getReservationNumber())).findAny()
                .orElse(null);
        return a;
    }

    protected Flight FindFlight(String flightnumber) {
        Flight f = _flights.stream().filter(flight -> flightnumber.equals(flight.getFlightNumber())).findAny()
                .orElse(null);
        return f;
    }

    protected Passenger FindPassenger(String firstname, String lastname) {
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

    protected ArrayList<Reservation> FindReservationForPassenger(String firstname, String lastname) {
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

    protected ArrayList<Passenger> FindPassengersForFlight(String flightnumber) {
        Reservation r = _reservations.stream()
                .filter(reservation -> flightnumber.equals(reservation.getFlight().getFlightNumber())).findAny()
                .orElse(null);
        if (r != null) {
            return r.getPassengerList();
        } else {
            return null;
        }
    }

    protected void PrintList(Iterable list) {
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
