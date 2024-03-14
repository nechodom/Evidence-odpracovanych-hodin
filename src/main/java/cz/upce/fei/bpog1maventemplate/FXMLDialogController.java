/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cz.upce.fei.bpog1maventemplate;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author st69936
 */
public class FXMLDialogController implements Initializable {


    @FXML
    private TextField jmeno;
    @FXML
    private TextField pocetHodin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getJmeno() {
        return jmeno;
    }

    public TextField getPocetHodin() {
        return pocetHodin;
    }
}
