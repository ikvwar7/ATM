package domain.CashOut;

import domain.Cell;

import java.util.Comparator;
import java.util.List;

//стратегия выдачи, сначала мелкими купюрами
public class SmallFirstCashOutStrategy extends CashOutStrategy {

    public SmallFirstCashOutStrategy(List<Cell> cells) {
        super(cells);
    }

    @Override
    public List<Cell> sortCell(List<Cell> cells) {
        cells.sort(Comparator.comparing(Cell::getBanknoteNominal));
        return cells;
    }
}
