package OsborneAirlines;

import java.util.ArrayList;

public class Reservation extends UniqueObjectwithId {

    ArrayList<Passenger> _passengerlist;
    Flight _flight;

    public ArrayList<Passenger> getPassengerList() {
        return _passengerlist;
    }

    public Flight getFlight() {
        return _flight;
    }
}
