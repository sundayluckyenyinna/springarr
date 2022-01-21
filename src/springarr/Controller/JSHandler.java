/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springarr.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** 
 * This class contains the public methods for the bridge between java and javascript codes
 * @author sunday enyinna lucky
 */
public class JSHandler 
{
    /**
     * Method to return the base64 string of chosen image 
     * @return String: base64 String of image
     * @throws IOException 
     */
    public String getImageStream() throws IOException
        {
            String path = "";
             // open a dialog box first for the user to select 
            FileChooser fileChooser = new FileChooser();
            // set the title 
            fileChooser.setTitle("Select a design to upload");
            // set the file to be selected on the limit 
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png","*.jpeg","*.gif")
            );
            File file = fileChooser.showOpenDialog(null);
            
            if(file != null)
            {
               String type = java.nio.file.Files.probeContentType(file.toPath());
               //get html content
               FileInputStream fis = new FileInputStream(file);
               byte[] data = new byte[(int)file.length()];
               fis.read(data);
               String base64data = java.util.Base64.getEncoder().encodeToString(data);
               String prefix = "data:image/jpg;base64,";
               path = prefix + base64data;
                
            }
            return path;
        }
    
    /**
     * Method to open a new page in a new tab 
     * @return 
     */
    
    
    /** Method to get the browser tab pane
     * @return
     * @throws java.io.IOException  */
    public TabPane getBrowserTabPane() throws IOException
    {
        String path = "springarr/View/fxml/Browser.fxml";
        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(path));
        loader.load();
        // get the tabpane
        BrowserController browserPane = loader.getController();
        // return the tab pane 
        TabPane browserTabPane = browserPane.getTabpane();
        return browserTabPane;
    }
    
    /** Method to get the web view of the browser
     * @return 
     * @throws java.io.IOException 
     */
    public WebView getBrowserWebView() throws IOException
    {
        String path = "springarr/View/fxml/Browser.fxml";
        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(path));
        loader.load();
        // get the tabpane
        BrowserController browserPane = loader.getController();
        // get the browser webEngine
        WebView webView = browserPane.getWebView();
        return webView;
    }
    
    public BrowserController getBrowserController() throws IOException
    {
        String path = "springarr/View/fxml/Browser.fxml";
        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(path));
        loader.load();
        // get the tabpane
        BrowserController browserPane = loader.getController();
        return browserPane;
        
    }
    
    public String print()
    {
        return "okay";
    }
}
