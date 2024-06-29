package zeljeznickaKompozicija;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import mapa.*;
import sample.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Voz extends Thread  {
    private ArrayList<Lokomotiva> lokomotive;
    private ArrayList<Vagon> vagoni;
    private Mapa mapa;
    private GridPane mapaGUI;
    private float brzina;
    private Stanica polaznaStanica;
    private Stanica odredisnaStanica;
    private Stanica sljedecaStanica;
    private int duzina;
    private int pocetakX;
    private int pocetakY;
    private boolean daLiMozeKrenuti;
    private boolean daLijeCijelaKompozicijaIzaslaIzStanice=false;
    private boolean daLiJeVozProsaoPrekoPrelaza=false;
    private int brojacPoljaPrelaza=0;
    private String putovanje;
    private long vrijemePutovanja=0;
    private String nazivVoza="";
    private LinkedList<Integer> istorijaKretanjaX=new LinkedList<Integer>();
    private LinkedList<Integer> istorijaKretanjaY=new LinkedList<Integer>();


    public Voz() {
        super();
    }

    public Voz(ArrayList<Lokomotiva> lokomotive, ArrayList<Vagon> vagoni,Mapa mapa,float brzina,String polaznaStanica,String odredisnaStanica,
               GridPane mapaGUI) {
        this.lokomotive = lokomotive;
        this.vagoni = vagoni;
        this.mapa=mapa;
        this.brzina=brzina;
        this.mapaGUI=mapaGUI;
        this.duzina=lokomotive.size()+vagoni.size();
        if(polaznaStanica.equals("29-2")) {
            pocetakY = 29;
            pocetakX = 2;
        }
        if(polaznaStanica.equals("25-29")){
            pocetakY=25;
            pocetakX=29;
        }
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 30; j++) {
                    Object objekat = this.mapa.getObjekatSaMape(i, j);
                    if (objekat != null && objekat instanceof Stanica) {
                        Stanica stanica = (Stanica) objekat;
                        if(!polaznaStanica.equals("29-2")&& !polaznaStanica.equals("25-29")) {
                            if (stanica.getOznaka().equals(polaznaStanica))
                                this.polaznaStanica = stanica;
                        }
                        if (stanica.getOznaka().equals(odredisnaStanica))
                            this.odredisnaStanica = stanica;
                    }
                }

    }
    private ArrayList<TextField> kreacijaGUIVoza(boolean napon){

        TextField[] naponPolje=null;
        ArrayList<TextField> vozGUI = new ArrayList<>();
        if(napon)
        {
            naponPolje=new TextField[2];
            for(int i=0;i<2;i++) {
                naponPolje[i] = new TextField();
                naponPolje[i].setText("#");
                naponPolje[i].setStyle("-fx-background-color: #4747f5");
            }
            vozGUI.add(naponPolje[0]);
        }
        for (int i = 0; i < this.lokomotive.size(); i++) {
            TextField poljaVoza[] = new TextField[this.lokomotive.size()];
            poljaVoza[i] = new TextField();
            poljaVoza[i].setText("L" + i);
            poljaVoza[i].setStyle("-fx-background-color: #f45895");
            vozGUI.add(poljaVoza[i]);
        }
        for (int i = 0; i < this.vagoni.size(); i++) {
            TextField poljaVoza[] = new TextField[this.vagoni.size()];
            poljaVoza[i] = new TextField();
            poljaVoza[i].setText("V" + i);
            poljaVoza[i].setStyle("-fx-background-color: #e6f84c");
            vozGUI.add(poljaVoza[i]);
        }
        if(napon)
        {
            vozGUI.add(naponPolje[1]);
        }
        return vozGUI;
    }
    public void serijalizacijaIstorijeKretanja()
    {
        File fajl=new File("C:\\Users\\mlade\\Desktop\\ProjektniZadatak\\IstorijaKretanja");
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fajl + File.separator + this.nazivVoza,true)));
            pw.println("Putovanje: "+putovanje);
            pw.println("Vrijeme putovanja: "+vrijemePutovanja/1000f+" sekundi");
            pw.println("Istorija kretanja: ");
            for(int i=0;i<istorijaKretanjaX.size();i++)
            {
                if(i!=istorijaKretanjaX.size()-1)
                     pw.print("("+istorijaKretanjaX.get(i)+","+istorijaKretanjaY.get(i)+")->");
                else
                    pw.print("("+istorijaKretanjaX.get(i)+","+istorijaKretanjaY.get(i)+")\n");
            }
          pw.close();
        }
        catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            return;
        }
    }


    public String getNazivVoza() {
        return nazivVoza;
    }

    public void setNazivVoza(String nazivVoza) {
        this.nazivVoza = nazivVoza;
    }

    public boolean isDaLiJeVozProsaoPrekoPrelaza() {
        return daLiJeVozProsaoPrekoPrelaza;
    }

    public boolean isDaLijeCijelaKompozicijaIzaslaIzStanice() {
        return daLijeCijelaKompozicijaIzaslaIzStanice;
    }

    public float getBrzina() {
        return brzina;
    }

    public void setBrzina(float brzina) {
        this.brzina = brzina;
    }

    public void setDaLijeCijelaKompozicijaIzaslaIzStanice(boolean daLijeCijelaKompozicijaIzaslaIzStanice) {
        this.daLijeCijelaKompozicijaIzaslaIzStanice = daLijeCijelaKompozicijaIzaslaIzStanice;
    }

    public Stanica getPolaznaStanica() {
        return polaznaStanica;
    }

    public Stanica getSljedecaStanica() {
        return sljedecaStanica;
    }

    public void setSljedecaStanica(Stanica sljedecaStanica) {
        this.sljedecaStanica = sljedecaStanica;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public boolean isDaLiMozeKrenuti() {
        return daLiMozeKrenuti;
    }

    public void setDaLiMozeKrenuti(boolean daLiMozeKrenuti) {
        this.daLiMozeKrenuti = daLiMozeKrenuti;
    }

    public int getPocetakX() {
        return pocetakX;
    }

    public void setPocetakX(int pocetakX) {
        this.pocetakX = pocetakX;
    }

    public int getPocetakY() {
        return pocetakY;
    }

    public void setPocetakY(int pocetakY) {
        this.pocetakY = pocetakY;
    }

    public Stanica getOdredisnaStanica() {
        return odredisnaStanica;
    }

    public ArrayList<Lokomotiva> getLokomotive() {
        return lokomotive;
    }

    public void setLokomotive(ArrayList<Lokomotiva> lokomotive) {
        this.lokomotive = lokomotive;
    }

    public ArrayList<Vagon> getVagoni() {
        return vagoni;
    }

    public void setVagoni(ArrayList<Vagon> vagoni) {
        this.vagoni = vagoni;
    }

    @Override
    public void run() {

        boolean daLijePotrebanNapon=this.daLiJePotrebanNapon();
        boolean daLiJeVozStigaoNaOdrediste = false;
        long brzinaUMiliSec = (long) (brzina * 1000);

        ArrayList<TextField> vozGUI = this.kreacijaGUIVoza(daLijePotrebanNapon);


        if(pocetakY==0) {
            polaznaStanica.pocetneKordinateVoza(this);
        }
        if(polaznaStanica!=null) {
            putovanje = polaznaStanica.getOznaka();
        }
        else{
            putovanje="("+pocetakX+","+pocetakY+")";
        }
        float standardnaBrzinaVoza = this.getBrzina();
        istorijaKretanjaX.add(pocetakX);
        istorijaKretanjaY.add(pocetakY);

        if(daLijePotrebanNapon==true)
        {
            duzina+=1;
        }
        boolean vozNeKreceizStanice;
        if(polaznaStanica==null)
            vozNeKreceizStanice=true;
        else
            vozNeKreceizStanice=false;

        while (daLiJeVozStigaoNaOdrediste != true) {
            boolean medjuStanica = false;
            int trenutniX[] = new int[duzina];
            int trenutniY[] = new int[duzina];
            int prethodniX[] = new int[duzina];
            int prethodniY[] = new int[duzina];
            trenutniX[0] = pocetakX;
            trenutniY[0] = pocetakY;

            int prugaIliPrelazX = -1;
            int prugaIliPrelazY = -1;

            int prugaIliPrelazXB=-1;
            int prugaIliPrelazYB=-1;


                synchronized (mapa) {
                    daLiMozeKrenuti = Stanica.daLiVozMozeKrenuti(this);
                    brzinaUMiliSec = (long) (brzina * 1000);

                }

                if (daLiMozeKrenuti == true) {
                    long vrijemePocetkaKretanja=System.currentTimeMillis();

                    for (int i = 0, p=0; medjuStanica != true; i++,p++) {

                        synchronized (this) {
                            if(daLijePotrebanNapon) {
                                if (p >= duzina+1)
                                    daLijeCijelaKompozicijaIzaslaIzStanice = true;
                                else
                                    daLijeCijelaKompozicijaIzaslaIzStanice = false;
                            }
                            else{
                                if (i == duzina)
                                    daLijeCijelaKompozicijaIzaslaIzStanice = true;
                                else
                                    daLijeCijelaKompozicijaIzaslaIzStanice = false;
                            }
                        }

                        try {
                            sleep(brzinaUMiliSec);
                        } catch (InterruptedException e) {
                            Logger.getLogger(Main.class.getName()).log(Level.INFO,e.fillInStackTrace().toString());
                            return;
                        }

                        Object desno = null;
                        Object lijevo=null;
                        Object gore=null;
                        Object dole=null;

                        if(vozNeKreceizStanice==false) {
                            if( trenutniX[0] + 1<30) {
                                desno=this.mapa.getObjekatSaMape(trenutniY[0], trenutniX[0] + 1);
                            }
                            if (trenutniX[0] - 1 >= 0) {
                                lijevo = this.mapa.getObjekatSaMape(trenutniY[0], trenutniX[0] - 1);
                            }
                            if (trenutniY[0] - 1 >= 0) {
                                gore = this.mapa.getObjekatSaMape(trenutniY[0] - 1, trenutniX[0]);
                            }
                            if (trenutniY[0] + 1 < 30) {
                                dole = this.mapa.getObjekatSaMape(trenutniY[0] + 1, trenutniX[0]);
                            }
                        }
                        vozNeKreceizStanice=false;
                        ObjekatMapeNapon prugaIliPrelaz = null;
                        ObjekatMapeNapon prugaIliPrelazB = null;

                        boolean daLiDesno = false;
                        boolean daLiLijevo = false;
                        boolean daLiGore = false;
                        boolean daLiDole = false;

                        if (!(this.mapa.getObjekatSaMape(trenutniY[0], trenutniX[0]) instanceof Stanica) || (i == 0)) {
                            if (gore instanceof ObjekatMape && !(gore instanceof Put) && !(gore == polaznaStanica) && trenutniY[0] - 1 != prethodniY[0]) {
                                daLiGore = true;
                            } else if (lijevo instanceof ObjekatMape && !(lijevo instanceof Put) && !(lijevo == polaznaStanica) && trenutniX[0] - 1 != prethodniX[0]) {
                                daLiLijevo = true;
                            } else if (desno instanceof ObjekatMape && !(desno instanceof Put) && !(desno == polaznaStanica) && trenutniX[0] + 1 != prethodniX[0]) {
                                    daLiDesno = true;
                            } else if (dole instanceof ObjekatMape && !(dole instanceof Put) && !(dole == polaznaStanica) && trenutniY[0] + 1 != prethodniY[0]) {
                                daLiDole = true;
                            }
                        }

                        if (i == duzina) {
                            i--;
                            if (Mapa.daLiJePrelaz(trenutniY[duzina - 1], trenutniX[duzina - 1]))
                                prugaIliPrelaz = new Prelaz();
                            else
                                prugaIliPrelaz = new Pruga();
                            prugaIliPrelazX = trenutniX[duzina - 1];
                            prugaIliPrelazY = trenutniY[duzina - 1];
                        }

                        if (p >= duzina + 1) {
                            if (daLijePotrebanNapon) {
                                if (Mapa.daLiJePrelaz(prethodniY[duzina - 1], prethodniX[duzina - 1]))
                                    prugaIliPrelazB = new Prelaz();
                                else
                                    prugaIliPrelazB = new Pruga();
                                prugaIliPrelazXB = prethodniX[duzina - 1];
                                prugaIliPrelazYB = prethodniY[duzina - 1];
                            }

                        }


                        for (int j = 0; j <= i; j++) {
                            prethodniX[i - j] = trenutniX[i - j];
                            prethodniY[i - j] = trenutniY[i - j];
                        }
                        for (int j = 0; j < i; j++) {
                            trenutniX[i - j] = trenutniX[i - j - 1];
                            trenutniY[i - j] = trenutniY[i - j - 1];
                        }


                        if (daLiDesno)
                            trenutniX[0]++;
                        if (daLiLijevo)
                            trenutniX[0]--;
                        if (daLiGore)
                            trenutniY[0]--;
                        if (daLiDole)
                            trenutniY[0]++;

                        if(daLiDesno||daLiDole||daLiGore||daLiLijevo) {
                            istorijaKretanjaX.add(trenutniX[0]);
                            istorijaKretanjaY.add(trenutniY[0]);
                        }


                        for (int j = 0; j < duzina; j++) {
                            medjuStanica = true;
                            Object trenutno = this.mapa.getObjekatSaMape(trenutniY[j], trenutniX[j]);
                            if (!(trenutno instanceof Stanica) ) {
                                medjuStanica = false;
                                break;
                            }
                        }

                        synchronized (this.mapa) {

                            for (int j = 0; j <= i; j++) {

                                if (!(this.mapa.getObjekatSaMape(trenutniY[j], trenutniX[j]) instanceof Stanica) ) {

                                    if (daLijePotrebanNapon && j == 0) {
                                        Object objekat = this.mapa.getObjekatSaMape(trenutniY[j], trenutniX[j]);
                                        ObjekatMapeNapon objekatNapon = (ObjekatMapeNapon) objekat;
                                        objekatNapon.setPodNaponom(true);

                                    } else {
                                        this.mapa.setObjekatNaMapu(trenutniY[j], trenutniX[j], this);
                                    }
                                }
                            }
                            if(daLijePotrebanNapon)
                            {
                                if(p>=duzina+1)
                                {
                                    this.mapa.setObjekatNaMapu(prugaIliPrelazYB,prugaIliPrelazXB,prugaIliPrelazB);
                                }

                            }
                            if (i == duzina - 1 && prugaIliPrelazX != -1) {
                                if(daLijePotrebanNapon && medjuStanica==false)
                                     prugaIliPrelaz.setPodNaponom(true);
                                this.mapa.setObjekatNaMapu(prugaIliPrelazY, prugaIliPrelazX, prugaIliPrelaz);
                                if(!daLijePotrebanNapon) {
                                    if (Mapa.daLiJePrelaz(prugaIliPrelazY,prugaIliPrelazX)) {
                                        brojacPoljaPrelaza++;
                                        if (brojacPoljaPrelaza == 2) {
                                            synchronized (this) {
                                                daLiJeVozProsaoPrekoPrelaza = true;
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    if (Mapa.daLiJePrelaz(prugaIliPrelazYB,prugaIliPrelazXB)) {
                                        brojacPoljaPrelaza++;
                                        if (brojacPoljaPrelaza == 2) {
                                            synchronized (this) {
                                                daLiJeVozProsaoPrekoPrelaza = true;
                                            }
                                        }
                                    }
                                }
                            }
                            final int k;
                            final int[] a;
                            final int[] b;
                            if(daLijePotrebanNapon && p>=duzina) {
                                k = i + 1;
                                a=new int[duzina+1];
                                b=new int[duzina+1];
                                for (int j = 0; j < duzina+1; j++) {
                                    if (j < duzina) {
                                        a[j] = trenutniY[j];
                                        b[j] = trenutniX[j];
                                    } else {
                                        if(!(this.mapa.getObjekatSaMape(trenutniY[duzina-1],trenutniX[duzina-1])instanceof Stanica)) {
                                            a[j] = prethodniY[duzina - 1];
                                            b[j] = prethodniX[duzina - 1];
                                        }
                                        else
                                        {
                                            a[j] = trenutniY[duzina - 1];
                                            b[j] = trenutniX[duzina - 1];
                                        }
                                    }
                                }
                            }
                            else {
                                k = i;
                                a=new int[duzina];
                                b=new int[duzina];
                            for (int j = 0; j < duzina; j++) {

                                    a[j] = trenutniY[j];
                                    b[j] = trenutniX[j];
                                }
                            }


                            Platform.runLater(() -> {


                                for (int j = 0; j <= k; j++) {
                                    this.mapaGUI.getChildren().remove(vozGUI.get(j));

                                    if (!(this.mapa.getObjekatSaMape(a[j], b[j]) instanceof Stanica))
                                        this.mapaGUI.add(vozGUI.get(j), b[j], a[j]);

                                }


                            });

                        }


                    }
                    long vrijemeDolaska=System.currentTimeMillis();
                    vrijemePutovanja+=vrijemeDolaska-vrijemePocetkaKretanja;
                    brzina = standardnaBrzinaVoza;
                    Stanica stanica = (Stanica) this.mapa.getObjekatSaMape(trenutniY[0], trenutniX[0]);
                    putovanje+="->"+stanica.getOznaka();
                    synchronized (mapa) {
                        Stanica.stanjeNaPrugama(stanica, this);
                    }

                    if (this.mapa.getObjekatSaMape(trenutniY[0], trenutniX[0]) == odredisnaStanica) {
                        daLiJeVozStigaoNaOdrediste = true;

                    } else {
                        polaznaStanica = stanica;
                        polaznaStanica.pocetneKordinateVoza(this);
                        synchronized (this) {
                            brojacPoljaPrelaza = 0;
                            daLiJeVozProsaoPrekoPrelaza = false;
                        }
                    }

                } else {
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.INFO,e.fillInStackTrace().toString());
                        return;
                    }
                }

            }
            this.serijalizacijaIstorijeKretanja();

        }

        private boolean daLiJePotrebanNapon()
        {
            for(int i=0;i<lokomotive.size();i++)
            {
                if(lokomotive.get(i).getPogon().equals(Pogon.ELEKTRICNI))
                    return true;
            }
            return false;
        }
}

/*
synchronized(mapa){
        System.out.println();
        for(int i=0;i<30;i++){
        for(int j=0;j<30;j++){
        Object objekat=mapa.getObjekatSaMape(i,j);
        if(objekat instanceof Stanica){
        System.out.print("S ");
        }
        else if(objekat instanceof Voz){
            System.out.print("V ");
        }
        else if(objekat instanceof Vozilo){
            System.out.print("A ");
        }
        else if(objekat instanceof Put){
            System.out.print("Z ");
        }
        else if(objekat instanceof ObjekatMapeNapon)
        {
            if(((ObjekatMapeNapon)objekat).isPodNaponom()){
        System.out.print("Q ");
        }
            else if(objekat instanceof Prelaz){
        System.out.print("C ");
        }
                else if(objekat instanceof Pruga){
                    System.out.print("P ");

        }

        }
        else{
            System.out.print("* ");
        }


        }
        System.out.println();

        }
        }
        */