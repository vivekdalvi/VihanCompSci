package OsborneAirlines;

import java.time.LocalDateTime;

public class FlightDate {
    LocalDateTime _gmtdeparturetime;
    LocalDateTime _gmtarrivaltime;

    public FlightDate(String arrivaltime, String departuretime) {
        _gmtarrivaltime = LocalDateTime.parse(arrivaltime);
        _gmtdeparturetime = LocalDateTime.parse(departuretime);
    }

    public LocalDateTime getDepartureGMT() {
        return _gmtdeparturetime;
    }

    public LocalDateTime getArrivalGMT() {
        return _gmtarrivaltime;
    }

}
