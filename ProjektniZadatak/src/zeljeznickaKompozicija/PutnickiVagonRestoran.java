package zeljeznickaKompozicija;

public class PutnickiVagonRestoran extends PutnickiVagon {

    private String opis;

    public PutnickiVagonRestoran(int duzina, String oznaka, String opis) {
        super(duzina, oznaka);
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
