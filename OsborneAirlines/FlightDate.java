package OsborneAirlines;

import java.util.Date;

public class FlightDate {
    Date _gmtdeparturetime;
    Date _gmtarrivaltime;

    public Date getDepartureGMT() {
        return _gmtdeparturetime;
    }

    public Date getArrivalGMT() {
        return _gmtarrivaltime;
    }

}
