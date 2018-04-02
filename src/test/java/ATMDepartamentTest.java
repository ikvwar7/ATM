import domain.ATM;
import domain.ATMDepartament;
import domain.CashOut.LargeFirstCashOutStrategy;
import domain.CashOut.SmallFirstCashOutStrategy;
import domain.Cell;
import domain.Exception.NotEnoughMoneyException;
import org.junit.Test;

import static org.junit.Assert.*;

import static java.util.Arrays.asList;

public class ATMDepartamentTest {

    @Test
    public void totalRemainForTwoAtm() {
        ATMDepartament atmDepartament = new ATMDepartament(asList(
                new ATM(1, asList(
                        new Cell(100L, 1),
                        new Cell(500L, 2)
                )),
                new ATM(2, asList(
                        new Cell(1000L, 2),
                        new Cell(5000L, 1)
                ))

        ));
        assertTrue(atmDepartament.totalRemain() == 8100);
    }

    @Test
    public void totalRestoreAfterCashOutAndCashIn() throws NotEnoughMoneyException {
        ATMDepartament atmDepartament = new ATMDepartament(asList(
                new ATM(1, asList(
                        new Cell(100L, 1),
                        new Cell(500L, 2)
                )),
                new ATM(2, asList(
                        new Cell(1000L, 2),
                        new Cell(5000L, 1)
                ))

        ));
        atmDepartament.getAtms().get(0).cashOut(1000, new LargeFirstCashOutStrategy(atmDepartament.getAtms().get(0).getCells()));
        assertTrue(atmDepartament.totalRemain() == 7100);
        atmDepartament.getAtms().get(0).cashIn(400);
        assertTrue(atmDepartament.totalRemain() == 7500);
        atmDepartament.totalRestore();
        assertTrue(atmDepartament.totalRemain() == 8100);
    }

}
