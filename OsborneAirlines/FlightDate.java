package OsborneAirlines;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightDate {
    ZonedDateTime _utcdeparturetime;
    ZonedDateTime _utcarrivaltime;

    public FlightDate(String departuretime, String arrivaltime) {

        _utcarrivaltime = LocalDateTime.parse(arrivaltime).atZone(ZoneId.of("UTC"));
        _utcdeparturetime = LocalDateTime.parse(departuretime).atZone(ZoneId.of("UTC"));
    }

    public ZonedDateTime getDepartureGMT() {
        return _utcdeparturetime;
    }

    public ZonedDateTime getArrivalGMT() {
        return _utcarrivaltime;
    }

}
