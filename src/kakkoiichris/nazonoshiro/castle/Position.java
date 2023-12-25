package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetValue;

public class Position extends ResetGroup {
    private final ResetValue<Integer> floor, row, column;

    public Position(int floor, int row, int column) {
        this.floor = new ResetValue<>(floor);
        this.row = new ResetValue<>(row);
        this.column = new ResetValue<>(column);

        add(this.floor);
        add(this.row);
        add(this.column);
    }

    public int getFloor() {
        return floor.get();
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public int getRow() {
        return row.get();
    }

    public void setRow(int row) {
        this.row.set(row);
    }

    public int getColumn() {
        return column.get();
    }

    public void setColumn(int column) {
        this.column.set(column);
    }

    public void set(int floor, int row, int column) {
        this.floor.set(floor);
        this.row.set(row);
        this.column.set(column);
    }

    public void set(Position that) {
        floor.set(that.getFloor());
        row.set(that.getRow());
        column.set(that.getColumn());
    }

    public Position plus(Direction direction) {
        return new Position(
                getFloor() + direction.getDeltaFloor(),
                getRow() + direction.getDeltaRow(),
                getColumn() + direction.getDeltaColumn()
        );
    }

    public void add(Direction direction) {
        setFloor(getFloor() + direction.getDeltaFloor());
        setRow(getRow() + direction.getDeltaRow());
        setColumn(getColumn() + direction.getDeltaColumn());
    }
}
