package OsborneAirlines;

import java.time.*;

public class Flight {
    private Airport _departureairport;
    private Airport _arrivalairport;
    private FlightDate _flightdate;
    private String _flightnumber;
    private int _capacity;

    public Flight(Airport departureairport, Airport arrivalairport, FlightDate flightdate,
            String flightnumber, int capacity) {
        _departureairport = departureairport;
        _arrivalairport = arrivalairport;
        _flightdate = flightdate;
        _flightnumber = flightnumber;
        _capacity = capacity;
    }

    public Airport getDepartureAirport() {
        return _departureairport;
    }

    public Airport getArrivalAirport() {
        return _arrivalairport;
    }

    public String getFlightNumber() {
        return _flightnumber;
    }

    public int getCapacity() {
        return _capacity;
    }

    public String GetLocalArrivalDate() {

        LocalDateTime arrivaltime = LocalDateTime.parse(_flightdate.getArrivalGMT().toString());
        ZonedDateTime zarrivaltime = ZonedDateTime.of(arrivaltime, ZoneId.of("UTC"));

        ZoneId arrivalzone = ZoneId.of(_arrivalairport.getTimeZoneId());
        ZonedDateTime localarrivaltime = zarrivaltime.withZoneSameInstant(arrivalzone);

        String arrivalstring = "";
        arrivalstring += "Arriving at local time:"
                + localarrivaltime.getDayOfMonth()
                + " " + localarrivaltime.getMonth()
                + " " + localarrivaltime.getYear()
                + "at " + localarrivaltime.getHour()
                + ":" + localarrivaltime.getMinute();

        return arrivalstring;

    }

    public String GetLocalDepartureDate() {
        LocalDateTime arrivaltime = LocalDateTime.parse(_flightdate.getArrivalGMT().toString());
        ZonedDateTime zarrivaltime = ZonedDateTime.of(arrivaltime, ZoneId.of("UTC"));

        ZoneId arrivalzone = ZoneId.of(_arrivalairport.getTimeZoneId());
        ZonedDateTime localarrivaltime = zarrivaltime.withZoneSameInstant(arrivalzone);

        String arrivalstring = "";
        arrivalstring += "Arriving at local time:"
                + localarrivaltime.getDayOfMonth()
                + " " + localarrivaltime.getMonth()
                + " " + localarrivaltime.getYear()
                + "at " + localarrivaltime.getHour()
                + ":" + localarrivaltime.getMinute();

        return arrivalstring;

    }

    public String toString() {
        String flightstring = "";
        // Name, City, State, Timezone
        flightstring += "Flight Number: " + _flightnumber + "\n";
        flightstring += "Flight Capacity: " + _capacity + "\n";
        flightstring += "ARRIVAL: " + "\n";
        flightstring += _arrivalairport.toString() + "\n\t";
        flightstring += GetLocalArrivalDate() + "\n";
        flightstring += "DEPARTURE: " + "\n";
        flightstring += _departureairport.toString() + "\n\t";
        flightstring += GetLocalDepartureDate();
        return flightstring;
    }

}
