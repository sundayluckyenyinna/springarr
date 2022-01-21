/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springarr.Controller;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author sunday enyinna lucky
 */
public class StaffDashBoardController implements Initializable 
{

    @FXML
    private Menu fileMenu;
    @FXML
    private Circle passportCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
       
         StaffDashboardUtility.populateFileMenu(fileMenu);
    }
   
    
}
