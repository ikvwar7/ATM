package domain;

import java.util.List;

//снимок состояния банкомата
public class ATMSnapshot {
    private List<Cell> cells;

    public ATMSnapshot(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
