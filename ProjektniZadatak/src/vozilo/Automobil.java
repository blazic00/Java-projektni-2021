package vozilo;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import mapa.Mapa;

public class Automobil extends Vozilo {
    private int brojVrata;

    public Automobil(String marka, String model, int godiste, int brojVrata, Mapa mapa, GridPane mapaGUI,float brzina) {
        super(marka, model, godiste,mapa,mapaGUI,brzina);
        this.brojVrata = brojVrata;
    }
    @Override
    protected TextField kreacijaGUIVozila(){
        TextField voziloGUI=new TextField();
        voziloGUI.setText("A");
        voziloGUI.setStyle("-fx-background-color: #ed3415");
        return voziloGUI;
    }

}
