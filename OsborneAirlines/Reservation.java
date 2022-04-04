package OsborneAirlines;

import java.util.ArrayList;

public class Reservation implements Comparable<Reservation> {

    private ArrayList<Passenger> _passengerlist = new ArrayList<Passenger>();
    Flight _flight;
    String _reservationnumber;

    public Reservation(String reservationnumber, Flight flight, String[] passengerlist) {
        _reservationnumber = reservationnumber;
        _flight = flight;
        for (String string : passengerlist) {
            String[] namesplit = string.split(" ", 2);
            Passenger p = new Passenger(
                    // first name
                    namesplit[1].toUpperCase(),
                    // last name
                    namesplit[0].toUpperCase());
            _passengerlist.add(p);
        }
    }

    public Reservation(String reservationnumber, Flight flight, ArrayList<Passenger> passengerlist) {
        _reservationnumber = reservationnumber;
        _flight = flight;
        _passengerlist = passengerlist;
    }

    public ArrayList<Passenger> getPassengerList() {
        return _passengerlist;
    }

    public Flight getFlight() {
        return _flight;
    }

    public String getReservationNumber() {
        return _reservationnumber;
    }

    public String toString() {
        // reservation number, flightnumber, passenger list

        String reservationstring = "";
        reservationstring += "Reservation Number: " + TextColor.ANSI_BLUE + _reservationnumber + "\n"
                + TextColor.ANSI_RESET
                + _flight.toString() + "\n"
                + "Passenger List: " + "\n\t"
                + _passengerlist.toString();
        return reservationstring;
    }

    public int compareTo(Reservation reservation) {
        return this.getReservationNumber().compareTo(reservation.getReservationNumber());
    }
}
