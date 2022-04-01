package OsborneAirlines;

public class UniqueObjectwithId {

    private static int _objectcount = 0;
    private int _objectid;

    public UniqueObjectwithId() {
        _objectid = _objectcount++;
    }

    public int getID() {
        return _objectid;
    }

}
