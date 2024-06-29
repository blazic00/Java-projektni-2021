package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mapa.Mapa;
import vozilo.BrojVozilaIBrzinaCeste;
import vozilo.Vozilo;
import zeljeznickaKompozicija.Voz;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    @FXML
    private Button POKRENISIMULACIJU;

    public void prikazKretanja(){
        Stage stage=new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("IstorijaKretanja.fxml"));
        }
        catch(IOException ex){
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,ex.fillInStackTrace().toString());
            return;
        }
        stage.setScene(new Scene(root));
        stage.show();





    }


    public void pokreniSimulaciju(){
        POKRENISIMULACIJU.setDisable(true);
        Mapa mapa=new Mapa();
        Citac citac=new Citac();
        //ZeljeznickaKompozicija

//Pustanje vozova iz stanica
        File[] fajloviZaVozove=null;
        try {
            fajloviZaVozove = citac.readLineVoz(mapa, Main.gridPane);
        }
        catch(Exception ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            return;
        }
        ArrayList<File> listaFajlovaZaVozove=new ArrayList<>();
        for(int i=0;i<fajloviZaVozove.length;i++)
            listaFajlovaZaVozove.add(fajloviZaVozove[i]);


        Vozilo vozilo1=null;
        Vozilo vozilo2=null;
        Vozilo vozilo3=null;

        BrojVozilaIBrzinaCeste bvbc1=new BrojVozilaIBrzinaCeste();
        BrojVozilaIBrzinaCeste bvbc2=new BrojVozilaIBrzinaCeste();
        BrojVozilaIBrzinaCeste bvbc3=new BrojVozilaIBrzinaCeste();


        try {
            citac.brzinaCesteIBrojVozila(1, bvbc1);
            citac.brzinaCesteIBrojVozila(2, bvbc2);
            citac.brzinaCesteIBrojVozila(3, bvbc3);
        }
        catch(FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            return;
        }
        catch(IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            return;
        }




        (new Thread()
        {
            public void run()
            {

                int j=listaFajlovaZaVozove.size();
                File fajl = new File("C:\\Users\\mlade\\Desktop\\ProjektniZadatak\\Vozovi");
                File[] files=fajl.listFiles();
                while(true) {
                    fajl = new File("C:\\Users\\mlade\\Desktop\\ProjektniZadatak\\Vozovi");
                    files=fajl.listFiles();
                    Voz voz=null;
                    if(files.length>j) {
                        for (int i = 0; i < files.length; i++) {
                            try {
                                FileReader fileReader = new FileReader(files[i]);
                                BufferedReader readbuffer = new BufferedReader(fileReader);
                                String linijaVoz = readbuffer.readLine();
                                if(!(listaFajlovaZaVozove.contains(files[i]))) {
                                    listaFajlovaZaVozove.add(files[i]);
                                    voz = citac.kreacijaVoza(linijaVoz, mapa, Main.gridPane);
                                    voz.setNazivVoza(files[i].getName());
                                    voz.start();
                                }

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE,ex.fillInStackTrace().toString());
                                return;
                            }
                            catch(IOException ex)
                            {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE,ex.fillInStackTrace().toString());
                                return;
                            }
                            catch(NepravilnaKonfiguracijaVozaException ex)
                            {
                                Logger.getLogger(Main.class.getName()).log(Level.WARNING,ex.fillInStackTrace().toString());
                            }
                        }
                        j+=files.length-j;
                    }
                    else
                    {
                        try
                        {
                            sleep(2000);
                        }
                        catch(InterruptedException ex)
                        {
                            Logger.getLogger(Main.class.getName()).log(Level.INFO,ex.fillInStackTrace().toString());
                            return;
                        }
                    }
                }
            }

        }).start();

        Object lock=new Object();

        (new Thread() {
            public void run() {
                int brojVozila=bvbc1.getBrVozila();
                int br=0;
                while(true){
                for(int i=br;i<brojVozila;i++) {
                    Vozilo vozilo1 = Vozilo.kreacijaVozila(mapa, Main.gridPane, 1, bvbc1.getBrzina());
                    vozilo1.start();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.INFO, e.fillInStackTrace().toString());
                        return;
                    }
                    synchronized (lock) {
                        try {
                            citac.brzinaCesteIBrojVozila(1, bvbc1);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        }
                    }

                }
                    br=brojVozila;
                    synchronized (lock) {
                        try {
                            citac.brzinaCesteIBrojVozila(1, bvbc1);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        }
                    }
                    if (brojVozila < bvbc1.getBrVozila())
                        brojVozila = bvbc1.getBrVozila();

                }
            }
        }).start();

        (new Thread() {
            public void run() {
                int brojVozila=bvbc2.getBrVozila();
                int br=0;
                while(true) {
                    for (int i = br; i < brojVozila; i++) {
                        Vozilo vozilo2 = Vozilo.kreacijaVozila(mapa, Main.gridPane, 2, bvbc2.getBrzina());
                        vozilo2.start();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Logger.getLogger(Main.class.getName()).log(Level.INFO, e.fillInStackTrace().toString());
                            return;
                        }
                        synchronized (lock) {
                            try {
                                citac.brzinaCesteIBrojVozila(2, bvbc2);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                                return;
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                                return;
                            }
                        }

                    }
                    br=brojVozila;
                    synchronized (lock) {
                        try {
                            citac.brzinaCesteIBrojVozila(2, bvbc2);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        }
                    }

                    if (brojVozila < bvbc2.getBrVozila())
                        brojVozila = bvbc2.getBrVozila();
                }

            }
        }).start();

        (new Thread() {
            public void run() {
                int brojVozila=bvbc3.getBrVozila();
                int br=0;
                while(true) {
                    for (int i =br; i < brojVozila; i++) {
                        Vozilo vozilo3 = Vozilo.kreacijaVozila(mapa, Main.gridPane, 3, bvbc3.getBrzina());
                        vozilo3.start();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Logger.getLogger(Main.class.getName()).log(Level.INFO, e.fillInStackTrace().toString());
                            return;
                        }
                        synchronized (lock) {
                            try {
                                citac.brzinaCesteIBrojVozila(3, bvbc3);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                                return;
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                                return;
                            }
                        }

                    }
                    br=brojVozila;
                    synchronized (lock) {
                        try {
                            citac.brzinaCesteIBrojVozila(3, bvbc3);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                            return;
                        }
                    }
                    if (brojVozila < bvbc3.getBrVozila())
                        brojVozila = bvbc3.getBrVozila();

                }
            }
        }).start();

    }
}
