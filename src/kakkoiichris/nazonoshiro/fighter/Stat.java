// Christian Alexander, 12/11/2022
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resettable;

public class Stat implements Resettable {
    private final String name;

    private final ResetValue<Double> min;
    private final ResetValue<Double> max;
    private final ResetValue<Double> now;

    private final ResetGroup resetGroup;

    public Stat(String name, double min, double max) {
        this.name = name;

        this.min = new ResetValue<>(min);
        this.max = new ResetValue<>(max);
        this.now = new ResetValue<>(max);

        resetGroup = ResetGroup.of(this.min, this.max, now);
    }

    public String getName() {
        return name;
    }

    public ResetValue<Double> getMin() {
        return min;
    }

    public ResetValue<Double> getMax() {
        return max;
    }

    public ResetValue<Double> getNow() {
        return now;
    }

    @Override
    public void storeState() {
        resetGroup.storeState();
    }

    @Override
    public void resetState() {
        resetGroup.resetState();
    }

    @Override
    public String toString() {
        return "%s (%f/%f/%f)".formatted(name, getMin().get(), getNow().get(), getMax().get());
    }
}
