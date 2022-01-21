package springarr.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author sunday enyinna lucky
 */
public class Springarr extends Application {
   
    private BrowserController br;
    @Override
    public void start(Stage stage) throws Exception
    {
       initializeApplication(stage);
    }

    /**
     * 
     * @param stage
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public void initializeApplication(Stage stage) throws IOException, URISyntaxException
    {
        // Load the Home page 
        String path = "springarr/View/fxml/Browser.fxml";
        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(path));
        Parent root = loader.load();
        br = loader.getController();
        
        /** create a webView and webEngine and load the home page html page */
        WebView webView = br.getWebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        
        String relPath = "springarr/View/html/Homepage.html";
        URL homeUrl = this.getClass().getClassLoader().getResource(relPath).toURI().toURL();
        String homePagePath = homeUrl.toExternalForm();
        //load the home page in the engine
        webEngine.load(homePagePath);
        // set the zoom property
        webView.setZoom(webView.getZoom() - 0.01);
        /** Create a new tab and put the webView in that tab and the tab in the tabpane */
        Tab homeTab = br.getHomeTab();
        homeTab.setText("Home");
        homeTab.setContent(webView);
        // set its closing policy to unavailable
        homeTab.setClosable(false);
        //Add javascript functionality
        br.addJavaScriptFunctionality(webEngine);
        /** Show the stage */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaxHeight(Double.MAX_VALUE);
        stage.setMaxWidth(Double.MAX_VALUE);
        stage.setMaximized(true);
        stage.setTitle("Springarr");
        stage.show();

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("sun.net.http.allowRestrictedHeaders","true");
        launch(args);
    }
    
}
