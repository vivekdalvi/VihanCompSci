package OsborneAirlines;

import java.time.*;
import java.util.TimeZone;

public class Airport {
    private String _name;
    private String _city;
    private String _state;
    private String _timezoneid;

    public Airport(String name, String city, String state, String timezoneid) {
        _name = name;
        _city = city;
        _state = state;
        _timezoneid = timezoneid;
        String testtime = "2022-03-31T00:00";

        LocalDateTime t = LocalDateTime.parse(testtime);
        ZonedDateTime tz = ZonedDateTime.of(t, ZoneId.of("UTC"));

        ZoneId z = ZoneId.of(_timezoneid);
        ZonedDateTime z1 = tz.withZoneSameInstant(z);

        ZoneId y = ZoneId.of("Asia/Calcutta");
        ZonedDateTime y1 = tz.withZoneSameInstant(y);

        // System.out.println(t);
        // System.out.println(z1);
        // System.out.println(y1);
    }

    public String getName() {
        return _name;
    }

    public String getCity() {
        return _city;
    }

    public String getTimeZoneId() {
        return _timezoneid;
    }

    public String getState() {
        return _state;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setCity(String city) {
        _city = city;
    }

    public void setState(String state) {
        _state = state;
    }

    public String toString() {
        String airportstring = "";
        // Name, City, State, Timezone
        airportstring += "\tAirport Name: " + _name + "\n";
        airportstring += "\tirport City: " + _city + ", " + _state + "\n";
        airportstring += "\tAirport TimeZone: " + TimeZone.getTimeZone(_timezoneid).getDisplayName();
        return airportstring;
    }
}
