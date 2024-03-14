/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cz.upce.fei.bpog1maventemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Lukas
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private TextArea textArea;
    @FXML
    private Button novyZaznam;
    @FXML
    private Button smazatVse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { //proved na začátku životního cyklu
        smazatVse();
        
        pieChart.setAnimated(false);
        pieChart.setLegendSide(Side.TOP);
        pieChart.setTitleSide(Side.TOP);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(10);
    }    

    @FXML
    private void novyZaznamOnAction(ActionEvent event) {
        try {
            Pair<String, Integer> result = zobrazDialog();
            if (result != null){
                String text = String.format("%s; %d\n", result.getKey(),result.getValue());
                textArea.appendText(text);
                pieChart.getData().add(new PieChart.Data(text,result.getValue()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void smazatVseOnAction(ActionEvent event) {
        smazatVse();
    }
    
    private void smazatVse(){
        textArea.clear(); //vymaz dat v textArea
        pieChart.getData(); //vymaz dat v grafu
    }
    
    private Pair<String, Integer> zobrazDialog() throws IOException {
        Dialog<Pair<String,Integer>> dialog = new Dialog<>();
        dialog.setTitle("Zadej nový záznam odpracovaných hodin");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("FXMLDialog.fxml"));
        
        Parent root = loader.load();
        FXMLDialogController controller = loader.getController();
        
        Node btnOK = dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOK.setDisable(true);
        
        ChangeListener clEmptyTextValidation = new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov,
                    String oldValue, 
                    String newValue){
                    btnOK.setDisable(!isInputValid(controller.getJmeno().getText(),
                    controller.getPocetHodin().getText()));
            }
        };
                
        controller.getJmeno().textProperty().addListener(clEmptyTextValidation);
        controller.getPocetHodin().textProperty().addListener(clEmptyTextValidation);
        dialog.getDialogPane().setContent(root);
        
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == ButtonType.OK){
                return new Pair<>(controller.getJmeno().getText(),
                Integer.valueOf(controller.getPocetHodin().getText()));
            }
              return null;
            });
        
        return dialog.showAndWait().get(); //TODO CHANGE
        
    }
    
    private boolean isInputValid(String jmeno, String hodiny){
        int hod;
        
        try{
            hod = Integer.parseInt(hodiny);
        } catch(Exception e){
            hod = -1;
        }
        
        return (jmeno != null && jmeno.trim().length() > 0 &&
                hodiny != null &&hod > 0);
    }
    
}
