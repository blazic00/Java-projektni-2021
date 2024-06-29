package mapa;

import vozilo.Vozilo;
import zeljeznickaKompozicija.Voz;

public class Mapa {
    private Object[][] mapa;

    public Object getObjekatSaMape(int y,int x) {
        return mapa[y][x];
    }

    public void setObjekatNaMapu(int y,int x,Object objekat) {
        this.mapa[y][x] = objekat;
    }

    public Mapa()
    {
        mapa=new Object[30][30];
        this.kompletKreiranjePrelaza();
        this.kompletKreiranjePruga();
        this.kompletkreiranjePuteva();
        this.kreiranjeStanica();
    }

    private void kompletkreiranjePuteva()
    {
        this.kreiranjePuta(12,false,13,0);
        this.kreiranjePuta(46,false,13,7);
        this.kreiranjePuta(20,false,7,20);
        this.kreiranjePuta(4,true,20,0);
        this.kreiranjePuta(12,true,20,3);
        this.kreiranjePuta(10,true,20,21);
        this.kreiranjePuta(6,true,20,27);
        this.kreiranjePuta(20,false,21,20);

    }
    private void kompletKreiranjePrelaza()
    {
        Integer br=0;
        this.kreiranjePrelaza(br,2,false,2,20,true);
        this.kreiranjePrelaza(br,2,false,26,20,true);
        this.kreiranjePrelaza(br,2,true,6,13,true);
    }
    public static boolean daLiJePrelaz(int y,int x)
    {
        if((x==2||x==26)&&(y==21||y==20))
            return true;
        if(y==6&&(x==13||x==14))
            return true;

        return false;

    }
    public boolean daLiJeVoziloIspred(int y,int x)
    {
        if(mapa[y][x] instanceof Vozilo)
            return true;
        return false;
    }

    private void kompletKreiranjePruga()
    {
        Integer br=0;

        this.kreiranjePruge(br,1,false,2,29,false);
        this.kreiranjePruge(br,5,false,2,26,false);
        this.kreiranjePruge(br,4,false,2,19,false);
        this.kreiranjePruge(br,4,true,16,2,true);
        this.kreiranjePruge(br,11,false,5,16,false);
        this.kreiranjePruge(br,5,true,6,8,true);
        this.kreiranjePruge(br,5,true,6,15,true);
        this.kreiranjePruge(br,6,false,19,6,true);
        this.kreiranjePruge(br,6,true,12,21,true);
        this.kreiranjePruge(br,4,false,26,12,false);
        this.kreiranjePruge(br,3,true,9,26,true);
        this.kreiranjePruge(br,5,false,28,9,false);
        this.kreiranjePruge(br,6,true,5,28,false);
        this.kreiranjePruge(br,3,false,23,5,false);
        this.kreiranjePruge(br,2,true,3,23,false);
        this.kreiranjePruge(br,3,false,22,3,false);
        this.kreiranjePruge(br,4,true,1,22,true);
        this.kreiranjePruge(br,5,false,20,14,true);
        this.kreiranjePruge(br,7,true,18,20,true);
        this.kreiranjePruge(br,2,false,26,18,true);
        this.kreiranjePruge(br,3,false,26,22,true);
        this.kreiranjePruge(br,3,true,25,27,true);
    }
    private void kreiranjePuta(int br,boolean daLiJeRedKonst,int konstanta,int pocetak)
    {
        for (int i = 0; i < br; i += 2) {


            if(daLiJeRedKonst==true) {
                mapa [konstanta] [i / 2 + pocetak]=new Put();
                mapa [konstanta+1][i / 2 + pocetak]=new Put();
            }
            else
            {
               mapa[i / 2 + pocetak][konstanta+1]=new Put();
               mapa[i / 2 + pocetak][konstanta]=new Put();
            }

        }
    }
    private void kreiranjePruge(Integer br,int kolicina,boolean redKonstanta,int konstanta,int pocetak,boolean predZnak)
    {
        for(int i=br;i<br+kolicina;i++)
        {

            if(redKonstanta==true) {
                if(predZnak==true)
                   mapa[konstanta][pocetak + (i - br)]=new Pruga();
                else
                    mapa [konstanta][pocetak - (i - br)]=new Pruga();

            }
            else{
                if(predZnak==true)
                    mapa[pocetak +(i - br)][konstanta]=new Pruga();
                else
                    mapa[pocetak -(i - br)][konstanta]=new Pruga();
            }
        }
        br+=kolicina;
    }
    private void kreiranjePrelaza(Integer br,int kolicina,boolean redKonstanta,int konstanta,int pocetak,boolean predZnak){
        for(int i=br;i<br+kolicina;i++)
        {

            if(redKonstanta==true) {
                if(predZnak==true)
                    mapa[konstanta][pocetak + (i - br)]=new Prelaz();
                else
                    mapa [konstanta][pocetak - (i - br)]=new Prelaz();

            }
            else{
                if(predZnak==true)
                    mapa[pocetak +(i - br)][konstanta]=new Prelaz();
                else
                    mapa[pocetak -(i - br)][konstanta]=new Prelaz();
            }
        }
        br+=kolicina;

    }
    private void kreiranjeStanica()
    {
        Stanica stanicaA=new Stanica(this);
        Stanica stanicaB=new Stanica(this);
        Stanica stanicaC=new Stanica(this);
        Stanica stanicaD=new Stanica(this);
        Stanica stanicaE=new Stanica(this);

        stanicaA.setOznaka("A");
        stanicaB.setOznaka("B");
        stanicaC.setOznaka("C");
        stanicaD.setOznaka("D");
        stanicaE.setOznaka("E");



        mapa[27][2]=stanicaA;
        mapa[28][2]=stanicaA;

        mapa[6][6]=stanicaB;
        mapa[6][7]=stanicaB;

        mapa[1][26]=stanicaD;
        mapa[1][27]=stanicaD;

        mapa[12][19]=stanicaC;
        mapa[12][20]=stanicaC;
        mapa[13][20]=stanicaC;

        mapa[25][26]=stanicaE;

    }


}
