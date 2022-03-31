package OsborneAirlines;

import java.util.ArrayList;

public class Reservation {

    ArrayList<Passenger> _passengerlist;
    Flight _flight;
    int _reservationnumber;

    public ArrayList<Passenger> getPassengerList() {
        return _passengerlist;
    }

    public Flight getFlight() {
        return _flight;
    }

    public int getReservationNumber() {
        return _reservationnumber;
    }

}
