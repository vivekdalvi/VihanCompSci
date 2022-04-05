package OsborneAirlines;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightDate {
    // This a zone time in UTC. This is used along with zoneid of airport to
    // calculate local time
    ZonedDateTime _utcdeparturetime;
    ZonedDateTime _utcarrivaltime;
    // This is localtime in UTC used to write files when users chooses save option
    LocalDateTime _localdeparturetime;
    LocalDateTime _localarrivaltime;

    // consutrctor takes time & date in YYYY-MM-DDTHH:MM format
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
