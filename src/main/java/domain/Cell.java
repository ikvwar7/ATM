package domain;

public class Cell {
    private Long cellNominal;
    private int count;

    public Cell(Long nominal, int count) {
        this.cellNominal = nominal;
        this.count = count;
    }

    public Cell(Cell cell) {
        this.cellNominal = cell.cellNominal;
        this.count = cell.count;
    }

    public Long remain() {
        return cellNominal * count;
    }

    //выдать банкноту из ячейки
    public long banknoteOut(){
        count--;
        return cellNominal;
    }

    //принять банкноту в ячейку
    public long banknoteIn(){
        count++;
        return cellNominal;
    }

    public long getBanknoteNominal() {
        return cellNominal;
    }

    public int getBanknoteCount() {
        return count;
    }
}
