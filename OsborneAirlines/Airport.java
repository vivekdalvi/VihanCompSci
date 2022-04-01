package OsborneAirlines;

import java.time.*;

public class Airport extends UniqueObjectwithId {
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

        System.out.println(t);
        System.out.println(z1);
        System.out.println(y1);
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
