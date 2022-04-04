package OsborneAirlines;

public class Passenger implements Comparable<Passenger> {
    String _firstname;
    String _lastname;

    public Passenger(String firstname, String lastname) {
        _firstname = firstname;
        _lastname = lastname;
    }

    public String getName() {
        return _firstname + " " + _lastname;
    }

    public String toString() {
        return TextColor.ANSI_BLUE + _firstname + " " + _lastname + TextColor.ANSI_RESET;
    }

    public int compareTo(Passenger passenger) {
        return this.getName().compareTo(passenger.getName());
    }

}
