package OsborneAirlines;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightDate {
    ZonedDateTime _utcdeparturetime;
    ZonedDateTime _utcarrivaltime;
    LocalDateTime _localdeparturetime;
    LocalDateTime _localarrivaltime;

    public FlightDate(String departuretime, String arrivaltime) {
        _localarrivaltime = LocalDateTime.parse(arrivaltime);
        _localdeparturetime = LocalDateTime.parse(departuretime);
        _utcarrivaltime = _localarrivaltime.atZone(ZoneId.of("UTC"));
        _utcdeparturetime = _localdeparturetime.atZone(ZoneId.of("UTC"));
    }

    public ZonedDateTime getDepartureUTC() {
        return _utcdeparturetime;
    }

    public ZonedDateTime getArrivalUTC() {
        return _utcarrivaltime;
    }

    public LocalDateTime getDeparture() {
        return _localdeparturetime;
    }

    public LocalDateTime getArrival() {
        return _localarrivaltime;
    }

}
