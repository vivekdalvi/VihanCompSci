import javax.naming.event.NamespaceChangeListener;

public class Secretary extends Employee {
    boolean cantakedict;

    public Secretary() {
        cantakedict = true;
    }

    public boolean getCanDictate() {
        return cantakedict;

    }

    public int getSalary() {
        return 50000;
    }

}
