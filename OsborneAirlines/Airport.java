package OsborneAirlines;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;

public class Airport implements Comparable<Airport> {
    private String _name;
    private String _city;
    private String _state;
    private String _timezoneid;

    public Airport(String name, String city, String state, String timezoneid) {
        _name = name;
        _city = city;
        _state = state;
        _timezoneid = timezoneid;
    }

    public String getName() {
        return _name;
    }

    public String getCity() {
        return _city;
    }

    public String getTimeZoneId() {
        return _timezoneid;
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

    public String toString() {
        String airportstring = "";
        // Name, City, State, Timezone
        airportstring += "\tAirport Name: " + TextColor.ANSI_BLUE + _name + TextColor.ANSI_RESET;
        airportstring += "\tAirport City: " + TextColor.ANSI_BLUE + _city + ", " + _state + TextColor.ANSI_RESET;
        airportstring += "\t\tAirport TimeZone: " + TextColor.ANSI_BLUE
                + TimeZone.getTimeZone(_timezoneid).getDisplayName() + TextColor.ANSI_RESET;
        return airportstring;
    }

    public int compareTo(Airport airport) {
        return this.getName().compareTo(airport.getName());
    }

    protected static Airport FindAirport(String airportname, ArrayList<Airport> airports) {
        Airport a = airports.stream().filter(airport -> airportname.equals(airport.getName())).findAny().orElse(null);
        return a;
    }

    protected static Airport Ux_FindAirport(Scanner kbreader, ArrayList<Airport> airports) {
        System.out.print(TextColor.ANSI_BLUE + "\nEnter Airport Name: " + TextColor.ANSI_RESET);
        Airport a = Airport.FindAirport(kbreader.nextLine().toUpperCase(), airports);
        if (a != null) {
            System.out.println(a);
        } else {
            System.out.println(TextColor.ANSI_BLUE + "Airport Does not exist" + TextColor.ANSI_RESET);
        }
        return a;
    }

    protected static Airport Ux_CreateAirport(Scanner kbreader, ArrayList<Airport> airports) {
        System.out.print(TextColor.ANSI_BLUE + "\nAirport Name: " + TextColor.ANSI_RESET);
        String name = kbreader.nextLine().toUpperCase();
        Airport airport = Airport.FindAirport(name, airports);
        if (airport == null) {
            System.out.print(TextColor.ANSI_BLUE + "\nCity: " + TextColor.ANSI_RESET);
            String city = kbreader.nextLine().toUpperCase();
            System.out.print(TextColor.ANSI_BLUE + "\nState: " + TextColor.ANSI_RESET);
            String state = kbreader.nextLine().toUpperCase();
            System.out.print(TextColor.ANSI_BLUE + "\nTimeZone: " + TextColor.ANSI_RESET);
            String timezoneid = kbreader.nextLine();
            airport = new Airport(name, city, state, timezoneid);
            airports.add(airport);
        } else {
            System.out.println(TextColor.ANSI_BLUE + "Airport Already Exists" + TextColor.ANSI_RESET);
        }
        return airport;
    }

    protected static Airport Ux_DeleteAirport(Scanner kbreader, ArrayList<Airport> airports) {
        System.out.print(TextColor.ANSI_BLUE + "\nEnter Airport Name: " + TextColor.ANSI_RESET);
        Airport a = Airport.FindAirport(kbreader.nextLine().toUpperCase(), airports);
        if (a != null) {
            System.out
                    .println(TextColor.ANSI_RED
                            + "Deleting below Airport....flights/reservations from this airport are not valid"
                            + TextColor.ANSI_RESET);
            System.out.println(a);
            airports.remove(a);
        } else {
            System.out.println(TextColor.ANSI_BLUE + "Airport Does not exist" + TextColor.ANSI_RESET);
        }
        return a;
    }

}
