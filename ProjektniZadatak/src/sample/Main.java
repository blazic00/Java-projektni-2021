package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mapa.Mapa;

import vozilo.Vozilo;
import vozilo.BrojVozilaIBrzinaCeste;
import zeljeznickaKompozicija.*;

import java.io.*;
import java.util.logging.*;

import java.util.ArrayList;

public class Main extends Application {

    public static GridPane gridPane;

    public static Handler handler;

    {
        try {

            handler = new FileHandler("main.log");
            handler.setFormatter(new SimpleFormatter());
            Logger.getLogger(Main.class.getName()).addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root;
        try {
           root = FXMLLoader.load(getClass().getResource("sample.fxml"));
           gridPane=(GridPane)root.getChildren().get(0);
        }
        catch(IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,ex.fillInStackTrace().toString());
            return;
        }
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));

        gridPane.setGridLinesVisible(true);


        //Postavljanje mape

        MapaGUI.kompletKreacijaPruga(gridPane);
        MapaGUI.kompletKreacijaPuteva(gridPane);
        MapaGUI.kompletKreiranjePrelaza(gridPane);

        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);

    }


}
