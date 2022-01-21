
package springarr.Controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

/**
 *
 * @author sunday enyinna lucky
 */
public class StudentDashBoardController implements Initializable
{

    @FXML
    private TabPane tabpane;
    @FXML
    private Button dashboardButton;
    @FXML
    private BorderPane mainbox;
    
    /** Getters */

    public TabPane getTabpane() {
        return tabpane;
    }

    public Button getDashboardButton() {
        return dashboardButton;
    }

    public BorderPane getMainbox() {
        return mainbox;
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboardButton.setOnAction(event ->{
            Tab tab = new Tab();
            WebView w = new WebView();
            WebEngine e = w.getEngine();
            try {
                e.load(this.getClass().getClassLoader().getResource("springarr/View/html/test.html").toURI().toURL().toExternalForm());
            } catch (URISyntaxException | MalformedURLException ex) {
                Logger.getLogger(StudentDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            w.setZoom(w.getZoom() + 0.5);
            tab.setText("Test");
            tab.setContent(w);
            this.tabpane.getTabs().add(tab);
            this.tabpane.getSelectionModel().select(tab);
        });
        
        this.setStudentDashboardLayout();
    }
    
    /**
     * This method returns an array of the owner's screen visual bounds
     * @return 
     */
    private double[] getOwnerScreenVisualBounds()
    {
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D screen = primaryScreen.getBounds();
        double[] dimensions = new double[2];
        dimensions[0] = screen.getWidth();
        dimensions[1] = screen.getHeight();
        return dimensions;
    }
    
    private void setStudentDashboardLayout()
    {
        double ownerWidth = this.getOwnerScreenVisualBounds()[0];
        double ownerHeight = this.getOwnerScreenVisualBounds()[1];
        this.getMainbox().setPrefWidth(ownerWidth);
        this.getMainbox().setPrefHeight(ownerHeight);
        this.getMainbox().setMinWidth(ownerWidth);
        this.getMainbox().setMinHeight(ownerHeight);
        this.getMainbox().setMaxWidth(ownerWidth);
        this.getMainbox().setMaxHeight(ownerHeight);
    }
}
