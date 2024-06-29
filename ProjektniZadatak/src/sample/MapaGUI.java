package sample;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MapaGUI {

    private static void kreiranjePuta(GridPane root,TextField[] textField,int br,boolean redKonstanta,int konstanta,int pocetak) {
        textField = new TextField[br];
        for (int i = 0; i < br; i += 2) {
            textField[i] = new TextField();
            textField[i].setStyle("-fx-background-color: #31bae8");
            textField[i].setMaxWidth(30);
            textField[i].setMaxHeight(30);

            textField[i + 1] = new TextField();
            textField[i + 1].setStyle("-fx-background-color: #31bae8");
            textField[i + 1].setMaxWidth(30);
            textField[i + 1].setMaxHeight(30);
            if(redKonstanta!=true) {
                root.add(textField[i + 1], konstanta, i / 2 + pocetak);
                root.add(textField[i], konstanta+1, i / 2 + pocetak);
            }
            else
            {
                root.add(textField[i + 1], i / 2 + pocetak, konstanta+1);
                root.add(textField[i], i / 2 + pocetak, konstanta);
            }

        }

        }

    private static void kreiranjePruge(GridPane root,TextField[] textField,Integer br,int kolicina,boolean redKonstanta,
                                      int konstanta,int pocetak,boolean predZnak){

        for(int i=br;i<br+kolicina;i++)
        {
            textField[i]=new TextField();
            textField[i].setStyle("-fx-background-color: #a9b0ad");
            textField[i].setMaxWidth(30);
            textField[i].setMaxHeight(30);
            if(redKonstanta!=true) {
                if(predZnak==true)
                     root.add(textField[i], konstanta, pocetak + (i - br));
                else
                    root.add(textField[i], konstanta, pocetak - (i - br));

            }
            else{
                if(predZnak==true)
                    root.add(textField[i], pocetak +(i - br) , konstanta);
                else
                    root.add(textField[i], pocetak -(i - br) , konstanta);
            }
        }
        br+=kolicina;

    }
    public static void kompletKreacijaPruga(GridPane root)
    {
        Integer br=0;
        TextField textField[]= new TextField[150];

        MapaGUI.kreiranjePruge(root,textField,br,1,false,2,29,false);
        MapaGUI.kreiranjePruge(root,textField,br,5,false,2,26,false);
        MapaGUI.kreiranjePruge(root,textField,br,4,false,2,19,false);
        MapaGUI.kreiranjePruge(root,textField,br,4,true,16,2,true);
        MapaGUI.kreiranjePruge(root,textField,br,11,false,5,16,false);
        MapaGUI.kreiranjePruge(root,textField,br,5,true,6,8,true);
        MapaGUI.kreiranjePruge(root,textField,br,5,true,6,15,true);
        MapaGUI.kreiranjePruge(root,textField,br,6,false,19,6,true);
        MapaGUI.kreiranjePruge(root,textField,br,6,true,12,21,true);
        MapaGUI.kreiranjePruge(root,textField,br,4,false,26,12,false);
        MapaGUI.kreiranjePruge(root,textField,br,3,true,9,26,true);
        MapaGUI.kreiranjePruge(root,textField,br,5,false,28,9,false);
        MapaGUI.kreiranjePruge(root,textField,br,6,true,5,28,false);
        MapaGUI.kreiranjePruge(root,textField,br,3,false,23,5,false);
        MapaGUI.kreiranjePruge(root,textField,br,2,true,3,23,false);
        MapaGUI.kreiranjePruge(root,textField,br,3,false,22,3,false);
        MapaGUI.kreiranjePruge(root,textField,br,4,true,1,22,true);
        MapaGUI.kreiranjePruge(root,textField,br,5,false,20,14,true);
        MapaGUI.kreiranjePruge(root,textField,br,7,true,18,20,true);
        MapaGUI.kreiranjePruge(root,textField,br,2,false,26,18,true);
        MapaGUI.kreiranjePruge(root,textField,br,3,false,26,22,true);
        MapaGUI.kreiranjePruge(root,textField,br,3,true,25,27,true);
    }
    public static void kompletKreacijaPuteva(GridPane root)
    {
        TextField[] textField=null;
        MapaGUI.kreiranjePuta(root,textField,12,false,13,0);
        MapaGUI.kreiranjePuta(root,textField,46,false,13,7);
        MapaGUI.kreiranjePuta(root,textField,20,false,7,20);
        MapaGUI.kreiranjePuta(root,textField,4,true,20,0);
        MapaGUI.kreiranjePuta(root,textField,12,true,20,3);
        MapaGUI.kreiranjePuta(root,textField,10,true,20,21);
        MapaGUI.kreiranjePuta(root,textField,6,true,20,27);
        MapaGUI.kreiranjePuta(root,textField,20,false,21,20);

    }
    public static void kompletKreiranjePrelaza(GridPane root)
    {
        TextField[] textField=new TextField[6];
        Integer br=0;
        MapaGUI.kreiranjePrelaza(root,textField,br,2,false,2,20,true);
        MapaGUI.kreiranjePrelaza(root,textField,br,2,false,26,20,true);
        MapaGUI.kreiranjePrelaza(root,textField,br,2,true,6,13,true);


    }
    private static void kreiranjePrelaza(GridPane root,TextField[] textField,Integer br,int kolicina,boolean redKonstanta,
                                         int konstanta,int pocetak,boolean predZnak)
    {
        for(int i=br;i<br+kolicina;i++)
        {
            textField[i]=new TextField();
            textField[i].setStyle("-fx-background-color: #0f0d19");
            textField[i].setMaxWidth(30);
            textField[i].setMaxHeight(30);
            if(redKonstanta!=true) {
                if(predZnak==true)
                    root.add(textField[i], konstanta, pocetak + (i - br));
                else
                    root.add(textField[i], konstanta, pocetak - (i - br));

            }
            else{
                if(predZnak==true)
                    root.add(textField[i], pocetak +(i - br) , konstanta);
                else
                    root.add(textField[i], pocetak -(i - br) , konstanta);
            }
        }
        br+=kolicina;
    }






    }

