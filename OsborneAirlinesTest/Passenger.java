package OsborneAirlinesTest;

public class Passenger implements Comparable<Passenger> {
    // internal fields
    String _firstname;
    String _lastname;

    // only constructor is allowed to set values for private fields
    public Passenger(String firstname, String lastname) {
        _firstname = firstname;
        _lastname = lastname;
    }

    // first name + last name
    public String getName() {
        return _firstname + " " + _lastname;
    }

    public String getFirstName() {
        return _firstname;
    }

    public String getLastName() {
        return _lastname;
    }

    public String toString() {
        return TextColor.ANSI_BLUE + _firstname + " " + _lastname + TextColor.ANSI_RESET;
    }

    public int compareTo(Passenger passenger) {
        return this.getName().compareTo(passenger.getName());
    }
}
