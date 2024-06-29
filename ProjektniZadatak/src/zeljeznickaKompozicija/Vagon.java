package zeljeznickaKompozicija;

abstract public class Vagon {
    private int duzina;
    private String oznaka;

    public Vagon(int duzina, String oznaka) {
        this.duzina = duzina;
        this.oznaka = oznaka;
    }

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }
}
