package domain;

import domain.CashOut.CashOutStrategy;
import domain.Exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ATM {
    private int id;
    private List<Cell> cells;

    public ATM(int id, List<Cell> cells) {
        this.id = id;
        this.cells = cells;
    }

    //сохраняет состояние банкомата и возвращает его
    public ATMSnapshot saveState() {
        List<Cell> snapshotCell = new ArrayList<>();
        for (Cell i : cells)
            snapshotCell.add(new Cell(i));
        return new ATMSnapshot(snapshotCell);
    }

    //восстановить состояние банкомата
    public void restore(ATMSnapshot snapshot) {
        this.cells = snapshot.getCells();
    }

    //выдача наличных
    //возвращает выданную сумму
    public Long cashOut(long desiredSum, CashOutStrategy cashOutStrategy) throws NotEnoughMoneyException {
        long sumOut = 0;
        while (desiredSum != 0) {
            Cell cell = cashOutStrategy.nextCell(desiredSum);
            sumOut += cell.banknoteOut();
            desiredSum -= cell.getBanknoteNominal();
        }
        return sumOut;
    }

    //выдаёт остаток средств в банкомате
    public long remain() {
        long remain = 0L;
        for (Cell i : cells)
            remain += i.remain();
        return remain;
    }

    //приём наличных
    //возвращает принятую сумму
    public long cashIn(long desiredSum) {
        long sumIn = 0;
        while (desiredSum != 0) {
            Optional<Cell> cell = findCell(desiredSum);
            sumIn += cell.get().banknoteIn();
            desiredSum -= cell.get().getBanknoteNominal();
        }
        return sumIn;
    }

    public int getId() {
        return id;
    }

    public List<Cell> getCells() {
        return cells;
    }

    //ищет ячейку для приёма наличных
    private Optional<Cell> findCell(long desiredSum) {
        Optional<Cell> cell = cells.stream()
                .filter(cel -> cel.getBanknoteNominal() <= desiredSum)
                .findFirst();
        return cell;
    }
}
