package vozilo;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import mapa.Mapa;

public class Kamion extends Vozilo{
    private int nosivost;

    public Kamion(String marka, String model, int godiste, int nosivost, Mapa mapa, GridPane mapaGUI,float brzina) {
        super(marka, model, godiste,mapa,mapaGUI,brzina);
        this.nosivost = nosivost;
    }
    @Override
    protected TextField kreacijaGUIVozila(){
        TextField voziloGUI=new TextField();
        voziloGUI.setText("K");
        voziloGUI.setStyle("-fx-background-color: #20e617");
        return voziloGUI;
    }
}
