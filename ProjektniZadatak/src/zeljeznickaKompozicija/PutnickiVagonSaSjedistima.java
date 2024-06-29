package zeljeznickaKompozicija;

public class PutnickiVagonSaSjedistima extends PutnickiVagon {

    private int brojMjesta;

    public PutnickiVagonSaSjedistima(int duzina, String oznaka, int brojMjesta) {
        super(duzina, oznaka);
        this.brojMjesta = brojMjesta;
    }

    public int getBrojMjesta() {
        return brojMjesta;
    }

    public void setBrojMjesta(int brojMjesta) {
        this.brojMjesta = brojMjesta;
    }
}
