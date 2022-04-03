package OsborneAirlines;

import java.util.ArrayList;

public class Reservation {

    private final static ArrayList<Passenger> _passengerlist = new ArrayList<Passenger>();
    Flight _flight;
    int _reservationnumber;

    public Reservation(int reservationnumber, Flight flight, String[] passengerlist) {
        _reservationnumber = reservationnumber;
        _flight = flight;
        for (String string : passengerlist) {
            String[] namesplit = string.split(" ", 2);
            Passenger p = new Passenger(namesplit[0], namesplit[1]);
            _passengerlist.add(p);
        }
    }

    public ArrayList<Passenger> getPassengerList() {
        return _passengerlist;
    }

    public Flight getFlight() {
        return _flight;
    }
}
