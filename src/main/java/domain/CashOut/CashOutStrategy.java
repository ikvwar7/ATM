package domain.CashOut;

import domain.Cell;
import domain.Exception.NotEnoughMoneyException;

import java.util.List;

/*
* Стратегия выдачи денег банкоматом
*/
public abstract class CashOutStrategy {
    private final List<Cell> cells;

    public CashOutStrategy(List<Cell> cells) {
        this.cells = cells;
    }

    /*
    * Метод возвращает ячейку, из которой надо взять очередную купюру
    */
    public Cell nextCell(long desiredSum) throws NotEnoughMoneyException {
        return sortCell(cells).stream()
                .filter(cell -> cell.remain() >= desiredSum)
                .filter(cell -> cell.getBanknoteNominal() <= desiredSum)
                .findFirst()
                .orElseThrow(() -> new NotEnoughMoneyException("Not enough money. Check other sum"));
    }

    public abstract List<Cell> sortCell(List<Cell> cells);
}
