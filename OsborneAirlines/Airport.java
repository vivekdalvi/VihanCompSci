package OsborneAirlines;

import java.util.TimeZone;

public class Airport {
    private String _name;
    private String _city;
    private String _state;
    // need to use Java.util.TimeZone to calculate GMT offset
    private TimeZone _gmtoffset;
    private String _timezoneid;

    public Airport(String name, String city, String state, String timezoneid) {
        _name = name;
        _city = city;
        _state = state;
        _timezoneid = timezoneid;
        _gmtoffset = TimeZone.getTimeZone(_timezoneid);
        System.out.println(_gmtoffset.getRawOffset() / 3600000);
    }

    public String getName() {
        return _name;
    }

    public String getCity() {
        return _city;
    }

    public String getState() {
        return _state;
    }

    public TimeZone getGMTOffser() {
        return _gmtoffset;
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
}
