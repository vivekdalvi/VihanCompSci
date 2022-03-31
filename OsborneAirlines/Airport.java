package OsborneAirlines;

public class Airport {
    private String _name;
    private String _city;
    private String _state;
    // need to use Java.util.TimeZone to calculate GMT offset
    private String _timezoneid;

    public Airport(String timezoneid) {
        _timezoneid = timezoneid;
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

    public String getTimeZoneId() {
        return _timezoneid;
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
