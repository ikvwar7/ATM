package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMDepartament {
    private List<ATM> atms;
    private Map<Integer, ATMSnapshot> atmsState;

    public ATMDepartament(List<ATM> atms) {
        this.atms = atms;
        atmsState = new HashMap<>();
        saveState(atms);
    }

    //остаток средств во всех банкоматах
    public Long totalRemain() {
        Long totalRemain = 0L;
        for (ATM atm : atms)
            totalRemain += atm.remain();
        return totalRemain;
    }

    //сбросить все банкоматы
    public void totalRestore() {
        for (ATM atm : atms)
            atm.restore(atmsState.get(atm.getId()));
    }

    private void saveState(List<ATM> atms) {
        for (ATM atm : atms)
            atmsState.put(atm.getId(), atm.saveState());
    }

    public List<ATM> getAtms() {
        return atms;
    }


}
