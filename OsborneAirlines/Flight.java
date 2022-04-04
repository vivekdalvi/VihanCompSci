package OsborneAirlines;

import java.time.*;

public class Flight implements Comparable<Flight> {
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

    public FlightDate getFlightDate() {
        return _flightdate;
    }

    public String GetLocalDepartureDate() {
        ZoneId departurezone = ZoneId.of(_departureairport.getTimeZoneId());
        ZonedDateTime localdeparturetime = _flightdate.getDepartureUTC().withZoneSameInstant(departurezone);

        int hour = localdeparturetime.getHour();
        int min = localdeparturetime.getMinute();
        String hh = String.valueOf(hour);
        String mm = String.valueOf(min);
        if (hour < 10) {
            hh = "0" + hour;
        }
        if (min < 10) {
            mm = "0" + min;
        }

        String departurestring = "";
        departurestring += "Departing at local time: "
                + TextColor.ANSI_BLUE
                + localdeparturetime.getDayOfMonth()
                + " " + localdeparturetime.getMonth()
                + " " + localdeparturetime.getYear()
                + " at " + hh
                + ":" + mm
                + TextColor.ANSI_RESET;

        return departurestring;
    }

    public String GetLocalArrivalDate() {
        ZoneId arrivalzone = ZoneId.of(_arrivalairport.getTimeZoneId());
        ZonedDateTime localarrivaltime = _flightdate.getArrivalUTC().withZoneSameInstant(arrivalzone);

        String arrivalstring = "";
        int hour = localarrivaltime.getHour();
        int min = localarrivaltime.getMinute();
        String hh = String.valueOf(hour);
        String mm = String.valueOf(min);
        if (hour < 10) {
            hh = "0" + hour;
        }
        if (min < 10) {
            mm = "0" + min;
        }

        arrivalstring += "Arriving at local time: "
                + TextColor.ANSI_BLUE
                + localarrivaltime.getDayOfMonth()
                + " " + localarrivaltime.getMonth()
                + " " + localarrivaltime.getYear()
                + " at " + hh
                + ":" + mm
                + TextColor.ANSI_RESET;

        return arrivalstring;

    }

    public int compareTo(Flight flight) {
        return this.getFlightNumber().compareTo(flight.getFlightNumber());
    }

    public String toString() {
        String flightstring = "";
        // Name, City, State, Timezone
        flightstring += "Flight Number: " + TextColor.ANSI_BLUE + _flightnumber + "\n" + TextColor.ANSI_RESET;
        flightstring += "Flight Capacity: " + TextColor.ANSI_BLUE + _capacity + "\n" + TextColor.ANSI_RESET;
        flightstring += "DEPARTURE: " + "\n";
        flightstring += _departureairport.toString() + "\n\t";
        flightstring += GetLocalDepartureDate() + "\n";
        flightstring += "ARRIVAL: " + "\n";
        flightstring += _arrivalairport.toString() + "\n\t";
        flightstring += GetLocalArrivalDate();

        return flightstring;
    }

}
