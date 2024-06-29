package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class IstorijaKretanjaController {
    @FXML
    private TableView<LinijaTabele> TABELA;
    @FXML
    private TableColumn<LinijaTabele, String> IME;
    @FXML
    private Button PRIKAZKRETANJA;
    @FXML
    private TextArea TEXTAREA;


    public void initialize()
    {
        File file=new File("IstorijaKretanja");
        String[] linije=file.list();
        ArrayList<LinijaTabele> lista=new ArrayList<>();
        for(String linija:linije)
            lista.add(new LinijaTabele(linija));


        IME.setCellValueFactory(
                new PropertyValueFactory<LinijaTabele,String>("vrijednost")
        );

        TABELA.setItems(FXCollections.observableList(lista));

    }
    public void prikazKretanja(){
        LinijaTabele fajl = TABELA.getSelectionModel().getSelectedItem();
        if(fajl!=null) {
            File file = new File("IstorijaKretanja" + File.separator +fajl.getVrijednost());
            try {
                List<String> linije=Files.readAllLines(file.toPath());
                String rez="";
                for(String linija:linije)
                    rez+=linija+"\n";
                TEXTAREA.setText(rez);
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }

    }
}

