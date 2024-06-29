package zeljeznickaKompozicija;



abstract public class Lokomotiva {

    private Pogon pogon;
    private int snaga;
    private String oznaka;

    public Lokomotiva(Pogon pogon, int snaga, String oznaka) {
        this.pogon = pogon;
        this.snaga = snaga;
        this.oznaka = oznaka;
    }

    public int getSnaga() {
        return snaga;
    }

    public void setSnaga(int snaga) {
        this.snaga = snaga;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public Pogon getPogon() {
        return pogon;
    }
}
