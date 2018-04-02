package domain.CashOut;

import domain.Cell;

import java.util.Comparator;
import java.util.List;

//Стратегия выдачи, сначала крупными купюрами
public class LargeFirstCashOutStrategy extends CashOutStrategy {
    public LargeFirstCashOutStrategy(List<Cell> cells) {
        super(cells);
    }

    @Override
    public List<Cell> sortCell(List<Cell> cells) {
        cells.sort(Comparator.comparing(Cell::getBanknoteNominal).reversed());
        return cells;
    }
}
