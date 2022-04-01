package OsborneAirlines;

public class Flight extends UniqueObjectwithId {
    private Airport _departureairport;
    private Airport _arrivalairport;
    private FlightDate _departuredate;
    private FlightDate _arrivaldate;
    private String _flightnumber;
    private int _capacity;

    public Flight(Airport departureairport, Airport arrivalairport, FlightDate departuredate, FlightDate arrivaldate,
            String flightnumber, int capacity) {
        _departureairport = departureairport;
        _arrivalairport = arrivalairport;
        _departuredate = departuredate;
        _arrivaldate = arrivaldate;
        _flightnumber = flightnumber;
        _capacity = capacity;
    }

    public Airport getDepartureAirport() {
        return _departureairport;
    }

    public Airport getArrivalAirport() {
        return _arrivalairport;
    }

    public FlightDate getDepartureDate() {
        return _departuredate;
    }

    public FlightDate getArrivalDate() {
        return _arrivaldate;
    }

    public String getFlightNumber() {
        return _flightnumber;
    }

    public int getCapacity() {
        return _capacity;
    }

}
