package OsborneAirlines;

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
        airportstring += "\tAirport Name: " + TextColor.ANSI_BLUE + _name + TextColor.ANSI_RESET;
        airportstring += "\tAirport City: " + TextColor.ANSI_BLUE + _city + ", " + _state + TextColor.ANSI_RESET;
        airportstring += "\t\tAirport TimeZone: " + TextColor.ANSI_BLUE
                + TimeZone.getTimeZone(_timezoneid).getDisplayName() + TextColor.ANSI_RESET;
        return airportstring;
    }
}
