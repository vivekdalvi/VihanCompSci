package OsborneAirlines;

public class Passenger extends UniqueObjectwithId {
    String _firstname;
    String _lastname;

    public String getName() {
        return _firstname + " " + _lastname;
    }

}
