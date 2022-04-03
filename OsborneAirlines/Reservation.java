package OsborneAirlines;

import java.util.ArrayList;

public class Reservation {

    private ArrayList<Passenger> _passengerlist = new ArrayList<Passenger>();
    Flight _flight;
    int _reservationnumber;

    public Reservation(int reservationnumber, Flight flight, String[] passengerlist) {
        _reservationnumber = reservationnumber;
        _flight = flight;
        for (String string : passengerlist) {
            String[] namesplit = string.split(" ", 2);
            Passenger p = new Passenger(namesplit[1], namesplit[0]);
            _passengerlist.add(p);
        }
    }

    public Reservation(int reservationnumber, Flight flight, ArrayList<Passenger> passengerlist) {
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

    public int getReservationNumber() {
        return _reservationnumber;
    }

    public String toString() {
        // reservation number, flightnumber, passenger list

        String reservationstring = "";
        reservationstring += "Reservation Number: " + _reservationnumber + "\n"
                + _flight.toString() + "\n"
                + "Passenger List: " + "\n\t"
                + _passengerlist.toString();
        return reservationstring;
    }
}
