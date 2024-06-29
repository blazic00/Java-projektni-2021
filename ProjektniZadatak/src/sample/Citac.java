package sample;

import vozilo.BrojVozilaIBrzinaCeste;
import javafx.scene.layout.GridPane;
import mapa.Mapa;
import zeljeznickaKompozicija.*;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Citac {
    String text = "";
    int lineNumber;
   private String readLineVozilo(int br) throws  FileNotFoundException,IOException{

            FileReader readfile = new FileReader("C:\\Users\\mlade\\Desktop\\ProjektniZadatak\\KonfiguracioniFajl.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 3; lineNumber++) {
                if (lineNumber == br) {
                    text = readbuffer.readLine();
                } else
                    readbuffer.readLine();
            }
        return text;
    }
    public void brzinaCesteIBrojVozila(int brojCeste,BrojVozilaIBrzinaCeste brojVozilaIBrzinaCeste)throws  FileNotFoundException,IOException
    {
        String linija=readLineVozilo(brojCeste);
        String[] rijec=linija.split(",");
        brojVozilaIBrzinaCeste.setBrVozila(Integer.parseInt(rijec[1]));
        brojVozilaIBrzinaCeste.setBrzina(Float.parseFloat(rijec[2]));
    }
    public File[] readLineVoz(Mapa mapa, GridPane root) throws FileNotFoundException,IOException
    {

            File fajl = new File("C:\\Users\\mlade\\Desktop\\ProjektniZadatak\\Vozovi");
            File[] files=fajl.listFiles();
            Voz voz=null;
            for(int i=0;i<files.length;i++)
            {
                FileReader fileReader = new FileReader(files[i]);
                BufferedReader readbuffer = new BufferedReader(fileReader);
                String linijaVoz=readbuffer.readLine();
                try {
                    voz = this.kreacijaVoza(linijaVoz, mapa, root);
                    voz.setNazivVoza(files[i].getName());
                    voz.start();
                }
                catch(NepravilnaKonfiguracijaVozaException ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.WARNING,ex.fillInStackTrace().toString());
                }
            }
            return files;
    }
    public Voz kreacijaVoza(String linijaVoz, Mapa mapa,GridPane root)throws NepravilnaKonfiguracijaVozaException
    {
        Voz voz=null;
        ArrayList<Lokomotiva> lokomotive=new ArrayList<>();
        ArrayList<Vagon> vagoni=new ArrayList<>();
        try {
            int br;
            String[] strings = linijaVoz.split(",");

            int brojLokomotiva = Integer.parseInt(strings[0]);
            if(brojLokomotiva<1){
                throw new NepravilnaKonfiguracijaVozaException();
            }
            int brojVagona=Integer.parseInt(strings[brojLokomotiva+1]);

            String polaznaStanica=strings[brojLokomotiva+brojVagona+2];
            String odredisnaStanica=strings[brojLokomotiva+brojVagona+3];
            float brzina=Float.parseFloat(strings[brojLokomotiva+brojVagona+4]);

            if(brzina<0.5f)
            {
               throw new NepravilnaKonfiguracijaVozaException();
            }
            if(polaznaStanica.equals(odredisnaStanica))
            {
                throw new NepravilnaKonfiguracijaVozaException();
            }
            if(!(polaznaStanica.equals("A"))&&!(polaznaStanica.equals("B"))&&!(polaznaStanica.equals("C"))&&!(polaznaStanica.equals("D"))&&!(polaznaStanica.equals("E"))&&!(polaznaStanica.equals("29-2"))&&!(polaznaStanica.equals("25-29")))
            {
                throw new NepravilnaKonfiguracijaVozaException();
            }
            if(!(odredisnaStanica.equals("A"))&&!(odredisnaStanica.equals("B"))&&!(odredisnaStanica.equals("C"))&&!(odredisnaStanica.equals("D"))&&!(odredisnaStanica.equals("E")))
            {
                throw new NepravilnaKonfiguracijaVozaException();
            }

            String prvaLokomotiva=(strings[1].split("#"))[0];
            String opisKompozicije="Univerzalna";

            for(int i =0;i<brojLokomotiva;i++)
            {
                String[] opisLokomotive=strings[1+i].split("#");
                if(!(opisLokomotive[0].equals("Univerzalna lokomotiva"))&&!(opisLokomotive[0].equals(prvaLokomotiva)))
                {
                    throw new NepravilnaKonfiguracijaVozaException();
                }
                if(opisLokomotive[0].equals("Teretna lokomotiva"))
                {
                    lokomotive.add(new TeretnaLokomotiva(Pogon.valueOf(opisLokomotive[1]),Integer.parseInt(opisLokomotive[2]),opisLokomotive[3]));
                    opisKompozicije="Teretna";
                }
                else if(opisLokomotive[0].equals("Putnicka lokomotiva"))
                {
                    lokomotive.add(new PutnickaLokomotiva(Pogon.valueOf(opisLokomotive[1]),Integer.parseInt(opisLokomotive[2]),opisLokomotive[3]));
                    opisKompozicije="Putnicka";
                }
                else if(opisLokomotive[0].equals("Manevarska lokomotiva"))
                {
                    lokomotive.add(new ManevarskaLokomotiva(Pogon.valueOf(opisLokomotive[1]),Integer.parseInt(opisLokomotive[2]),opisLokomotive[3]));
                    opisKompozicije="Manevarska";
                }
                else if(opisLokomotive[0].equals("Univerzalna lokomotiva"))
                {
                    lokomotive.add(new UniverzalnaLokomotiva(Pogon.valueOf(opisLokomotive[1]),Integer.parseInt(opisLokomotive[2]),opisLokomotive[3]));
                }
                else
                {
                    throw new NepravilnaKonfiguracijaVozaException();
                }
            }
            for(int i=0;i<brojVagona;i++)
            {
                String[] opisVagona=(strings[brojLokomotiva+2+i].split("#"));
                if(opisVagona[0].equals("Teretni vagon")&& !(opisKompozicije.equals("Teretna"))&& !(opisKompozicije.equals("Univerzalna")))
                {
                    throw new NepravilnaKonfiguracijaVozaException();
                }
               else if(opisVagona[0].equals("Putnicki vagon")&& !(opisKompozicije.equals("Putnicka")) && !(opisKompozicije.equals("Univerzalna")))
                {
                    throw new NepravilnaKonfiguracijaVozaException();
                }
                else if(opisVagona[0].equals("Vagon posebne namjene")&& !(opisKompozicije.equals("Manevarska"))&& !(opisKompozicije.equals("Univerzalna")))
                {
                    throw new NepravilnaKonfiguracijaVozaException();
                }
                if(opisVagona[0].equals("Teretni vagon"))
                {
                    vagoni.add(new TeretniVagon(Integer.parseInt(opisVagona[1]),opisVagona[2],Integer.parseInt(opisVagona[3])));
                }
                else if(opisVagona[0].equals("Vagon posebne namjene"))
                {
                    vagoni.add(new VagonPosebneNamjene(Integer.parseInt(opisVagona[1]),opisVagona[2]));
                }
                else if(opisVagona[0].equals("Putnicki vagon"))
                {
                    if(opisVagona[1].equals("restoran"))
                    {
                        vagoni.add(new PutnickiVagonRestoran(Integer.parseInt(opisVagona[2]),opisVagona[3],opisVagona[4]));
                    }
                    else if(opisVagona[1].equals("sa lezajima"))
                    {
                        vagoni.add(new PutnickiVagonSaLezajima(Integer.parseInt(opisVagona[2]),opisVagona[3],Integer.parseInt(opisVagona[4])));
                    }
                    else if(opisVagona[1].equals("sa sjedistima"))
                    {
                        vagoni.add(new PutnickiVagonSaSjedistima(Integer.parseInt(opisVagona[2]),opisVagona[3],Integer.parseInt(opisVagona[4])));
                    }
                    else if(opisVagona[1].equals("za spavanje"))
                    {
                        vagoni.add(new PutnickiVagonZaSpavanje(Integer.parseInt(opisVagona[2]),opisVagona[3]));
                    }
                    else
                    {
                        throw new NepravilnaKonfiguracijaVozaException();
                    }

                }
                else
                {
                    throw new NepravilnaKonfiguracijaVozaException();
                }

            }

            voz=new Voz(lokomotive,vagoni,mapa,brzina,polaznaStanica,odredisnaStanica,root);

        }
        catch(Exception e)
        {
            throw new NepravilnaKonfiguracijaVozaException();
        }
        return voz;

    }

}
