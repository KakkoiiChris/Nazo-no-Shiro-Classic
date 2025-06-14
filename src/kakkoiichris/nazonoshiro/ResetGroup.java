// Christian Alexander, 12/3/2022
package kakkoiichris.nazonoshiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResetGroup implements Resettable {
    private final List<Resettable> resettables = new ArrayList<>();

    public static ResetGroup of(Resettable... resettables) {
        var group = new ResetGroup();

        group.addAll(List.of(resettables));

        return group;
    }

    public boolean add(Resettable resettable) {
        return resettables.add(resettable);
    }

    public void addAll(Collection<? extends Resettable> resettable) {
        resettables.addAll(resettable);
    }

    public void clear() {
        resettables.clear();
    }

    @Override
    public void storeState() {
        resettables.forEach(Resettable::storeState);
    }

    @Override
    public void resetState() {
        resettables.forEach(Resettable::resetState);
    }
}
