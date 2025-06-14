// Christian Alexander, 12/3/2022
package kakkoiichris.nazonoshiro;

public class ResetValue<X> implements Resettable {
    private X current;
    private X previous;

    public ResetValue(X value) {
        current = value;
        previous = current;
    }

    public X get() {
        return current;
    }

    public void set(X value) {
        current = value;
    }

    public boolean hasChanged() {
        return current != previous;
    }

    @Override
    public void storeState() {
        previous = current;
    }

    @Override
    public void resetState() {
        current = previous;
    }
}
