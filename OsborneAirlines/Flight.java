package OsborneAirlines;

public class Flight {
    Airport _departureairport;
    Airport _arrivalairport;
    FlightDate _departuredate;
    FlightDate _arrivaldate;
    String _flightnumber;
    int _capacity;

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
