package zeljeznickaKompozicija;

public class TeretniVagon extends Vagon{
        private int maksimalnaNosivost;

    public TeretniVagon(int duzina, String oznaka, int maksimalnaNosivost) {
        super(duzina, oznaka);
        this.maksimalnaNosivost = maksimalnaNosivost;
    }

    public int getMaksimalnaNosivost() {
        return maksimalnaNosivost;
    }

    public void setMaksimalnaNosivost(int maksimalnaNosivost) {
        this.maksimalnaNosivost = maksimalnaNosivost;
    }


}
