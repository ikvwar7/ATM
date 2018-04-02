import domain.ATM;
import domain.CashOut.LargeFirstCashOutStrategy;
import domain.CashOut.SmallFirstCashOutStrategy;
import domain.Cell;
import domain.Exception.NotEnoughMoneyException;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class ATMTest {
    private ATM atm;

    @Before
    public void setUp() {
        atm = new ATM(1, asList(
                new Cell(500L, 1),
                new Cell(100L, 7)
        ));
    }

    @Test
    public void take600RublesForSmallFirstStrategy() throws NotEnoughMoneyException {
        atm.cashOut(600, new SmallFirstCashOutStrategy(atm.getCells()));
        assertTrue(countOfNominal100(atm) == 1 && countOfNominal500(atm) == 1);
    }


    @Test
    public void take600RublesForLargeFirstStrategy() throws NotEnoughMoneyException {
        atm.cashOut(600, new LargeFirstCashOutStrategy(atm.getCells()));
        assertTrue(countOfNominal100(atm) == 6 && countOfNominal500(atm) == 0);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void takeVeryLargeSum() throws NotEnoughMoneyException {
        System.out.println(atm.cashOut(40000, new LargeFirstCashOutStrategy(atm.getCells())));
        atm.cashOut(40000, new LargeFirstCashOutStrategy(atm.getCells()));
    }


    private int countOfNominal100(ATM atm) {
        Optional<Cell> cell1 = atm.getCells().stream()
                .filter(cell -> cell.getBanknoteNominal() == 100)
                .findFirst();
        return cell1.get().getBanknoteCount();
    }

    private int countOfNominal500(ATM atm) {
        Optional<Cell> cell1 = atm.getCells().stream()
                .filter(cell -> cell.getBanknoteNominal() == 500)
                .findFirst();
        return cell1.get().getBanknoteCount();
    }
}