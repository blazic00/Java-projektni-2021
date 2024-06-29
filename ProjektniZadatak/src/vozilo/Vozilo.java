package vozilo;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import mapa.*;
import sample.Main;
import sample.MapaGUI;
import javafx.scene.control.TextField;
import zeljeznickaKompozicija.Voz;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Vozilo extends Thread {
    private String marka;
    private String model;
    private int godiste;
    private float brzina;
    private int pocetniX;
    private int pocetniY;
    private int trenutniX=-1;
    private int trenutniY=-1;
    private int putanjaX[];
    private int putanjaY[];
    private Mapa mapa;
    private GridPane mapaGUI;


    public Vozilo(String marka, String model, int godiste,Mapa mapa,GridPane mapaGUI,float brzina) {
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.mapa=mapa;
        this.mapaGUI=mapaGUI;
        this.brzina=brzina;
    }

   abstract protected TextField kreacijaGUIVozila();

    public int getPocetniX() {
        return pocetniX;
    }

    public void setPocetniX(int pocetniX) {
        this.pocetniX = pocetniX;
    }

    public int getPocetniY() {
        return pocetniY;
    }

    public void setPocetniY(int pocetniY) {
        this.pocetniY = pocetniY;
    }

    public int[] getPutanjaX() {
        return putanjaX;
    }

    public void setPutanjaX(int[] putanjaX) {
        this.putanjaX = putanjaX;
    }

    public int[] getPutanjaY() {
        return putanjaY;
    }

    public void setPutanjaY(int[] putanjaY) {
        this.putanjaY = putanjaY;
    }

    public float getBrzina() {
        return brzina;
    }

    public void setBrzina(float brzina) {
        this.brzina = brzina;
    }

    public static Vozilo kreacijaVozila( Mapa mapa, GridPane root,int brCeste,float brzina)
    {
        Random generator=new Random();
        brzina=brzina+generator.nextFloat();
        Vozilo vozilo=null;
        if(generator.nextBoolean()) {
            vozilo = new Automobil("BMW", "X5", 2015, 5, mapa, root,brzina);
        }
        else
        {
            vozilo=new Kamion("Volvo","KamionKamioncina",2005,3500,mapa,root,brzina);
        }
        switch(brCeste){
            case 1:
                vozilo.setPocetniX(8);
                vozilo.setPocetniY(29);
                break;
            case 3:
                vozilo.setPocetniX(22);
                vozilo.setPocetniY(29);
                break;
            case 2:
                vozilo.setPocetniX(14);
                vozilo.setPocetniY(29);
                break;

        }
        return vozilo;
    }

    public void run()
    {
        boolean mozeDalje=true;
        TextField voziloGUI=this.kreacijaGUIVozila();
        PostavkaVozila.postavljanjeVozilaNaPocetakPuta(this);
        PostavkaVozila.odredjivanjePuta(this);

        ObjekatMape putIliPrelaz=null;
        ObjekatMape putIliPrelazIspred=null;
        long brzinaUMili=(long)(brzina*1000);
        for(int i=0;i<putanjaX.length+1;i++)
        {
            try {
                sleep(brzinaUMili);
            }
            catch(InterruptedException e)
            {
                Logger.getLogger(Main.class.getName()).log(Level.INFO,e.fillInStackTrace().toString());
                return;
            }
            if(i!=putanjaX.length) {

                if (Mapa.daLiJePrelaz(trenutniY, trenutniX))
                    putIliPrelaz = new Prelaz();
                else {
                    putIliPrelaz= new Put();
                }

                synchronized (mapa) {
                    if (this.mapa.daLiJeVoziloIspred(putanjaY[i], putanjaX[i])) {
                        mozeDalje = false;
                    }
                    else
                        mozeDalje=true;
                }

                    if (Mapa.daLiJePrelaz(putanjaY[i], putanjaX[i]))
                        putIliPrelazIspred = new Prelaz();
                    else {
                        putIliPrelazIspred = new Put();
                    }


                synchronized (mapa) {

                        if(mozeDalje==true){
                    if (putIliPrelazIspred instanceof Prelaz) {
                        mozeDalje = Stanica.daLiAutoMozePreciPrekoPrelaza(pocetniY, pocetniX);
                    }

                    }
                    if (mozeDalje == true) {
                        if (trenutniX != -1)
                            mapa.setObjekatNaMapu(trenutniY,trenutniX, putIliPrelaz);
                        trenutniX = putanjaX[i];
                        trenutniY = putanjaY[i];
                        mapa.setObjekatNaMapu(trenutniY, trenutniX, this);

                        final int y = trenutniY;
                        final int x = trenutniX;
                        Platform.runLater(() -> {

                            this.mapaGUI.getChildren().remove(voziloGUI);
                            this.mapaGUI.add(voziloGUI, x, y);


                        });

                    }

                else {
                    i--;
                }}
            }
            else
            {
                    synchronized (mapa) {
                        mapa.setObjekatNaMapu(trenutniY, trenutniX, new Put());

                        Platform.runLater(() -> {

                            this.mapaGUI.getChildren().remove(voziloGUI);

                        });
                    }


            }
        }

    }

}
