package zeljeznickaKompozicija;

public class PutnickiVagonSaLezajima extends PutnickiVagon {

    private int brojMjesta;

    public PutnickiVagonSaLezajima(int duzina, String oznaka, int brojMjesta) {
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
