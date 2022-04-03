package OsborneAirlines;

public class Passenger {
    String _firstname;
    String _lastname;

    public Passenger(String firstname, String lastname) {
        _firstname = firstname;
        _lastname = lastname;
    }

    public String getName() {
        return _firstname + " " + _lastname;
    }

}
