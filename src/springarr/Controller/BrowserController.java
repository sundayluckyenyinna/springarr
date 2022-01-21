/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springarr.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import netscape.javascript.JSObject;
import org.apache.tika.Tika;

/**
 * FXML Controller class
 *
 * @author sunday enyinna lucky
 */
public class BrowserController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Button goButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button openButton;
    @FXML
    private Button optionButton;
    @FXML
    private TabPane tabpane;
    @FXML
    private WebView webView;
    @FXML
    private Tab homeTab;

    // Declare the private field of the javascript handler class 
    private JSHandler jsHandler;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private HBox topbar;
    @FXML
    private VBox mainbox;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //set closing policy of tabpane
        this.tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        
        /**
         * set the action for the go button
         */
        this.goButton.setOnAction(event ->{
            loadExternalPage();
        });
        
        /**
         * set the action for the refresh button
         */
        this.refreshButton.setOnAction(event -> {
            refreshCurrentPage();
        });
        
        /**
         * set the action for the home button
         */
        this.homeButton.setOnAction(event -> {
            try 
            {
                goHome();
            } catch (URISyntaxException | MalformedURLException ex) 
            {
                Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Make the progress bar disappear
        // disappear(this.getProgress());
        this.progress.setVisible(false);
        this.progress.setMaxHeight(36);
        this.progress.setMaxWidth(36);
        
        Map<String, Tab> tabMap = this.getTabMap();
        
        
        
    }    

    /**
     * The methods to return all private fields
     * @return 
     */
    public TextField getSearchField() {
        return searchField;
    }

    public Button getGoButton() {
        return goButton;
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    public VBox getMainbox() {
        return mainbox;
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getOpenButton() {
        return openButton;
    }

    public Button getOptionButton() {
        return optionButton;
    }

    public TabPane getTabpane() {
        return tabpane;
    }

    public WebView getWebView() {
        return webView;
    }

    public Tab getHomeTab() {
        return homeTab;
    }

    public ProgressIndicator getProgress() {
        return progress;
    }

    public HBox getTopbar() {
        return topbar;
    }
    
    
    public void loadPage(String path) throws URISyntaxException, MalformedURLException
    {
       String completeHtmlPath = "springarr/View/html/" + path;
       //get the url
       URL relUrl = this.getClass().getClassLoader().getResource(completeHtmlPath).toURI().toURL();
       //convert url to loadable form for webView 
       String loadablePath = relUrl.toExternalForm();
        
       
       boolean con = false;
       Tab similarTab = null;
       for(Tab t : this.getTabpane().getTabs())
       {
           if(((WebView)t.getContent()).getEngine().getLocation().contains(path))
           {
               con = true;
               similarTab = t;
               break;
           }
       }
       
       if(con == false)
       {
           //create a new webView 
           WebView newWebView = new WebView();
            // get the engine 
           WebEngine engine = newWebView.getEngine();
            //load the page using the engine
           engine.load(loadablePath);

           addJavaScriptFunctionality(engine);
           // create a new tab
           Tab newTab = new Tab();
           newTab.setText(getTabText(path));
           newTab.setContent(newWebView);
           this.getTabpane().getTabs().add(newTab);
           this.getTabpane().getSelectionModel().select(newTab);
           
       }
       
       else
       {
           this.getTabpane().getSelectionModel().select(similarTab);
       }
    }
    
    /**
     * This function loads external sites 
     */
    
    public void loadExternalPage()
    {
        // get the text of the above text field 
        String url= this.searchField.getText().trim();
        // check that something is entered
        if(url.length() != 0)
        {
            WebView view = new WebView();
            WebEngine newEngine = view.getEngine();
            //newEngine.setUserAgent("use required/ intended UA string");
            // get loadable string
            String loadable = getLoadableUrl(url);
            // load the url in the engine
            newEngine.load(loadable);
           // this.progress.progressProperty().bind(newEngine.getLoadWorker().progressProperty());
            this.progress.visibleProperty().bind(
               Bindings.when(newEngine.getLoadWorker().progressProperty().lessThan(0).or(
                   newEngine.getLoadWorker().progressProperty().isEqualTo(1)
                )).then(false).otherwise(true)
            );
            
            this.progress.managedProperty().bind(this.progress.visibleProperty());
            
            //set the font smothing type
            view.setFontSmoothingType(FontSmoothingType.GRAY);
            // create a new tab
            Tab tab = new Tab();
            tab.setText(url);
            tab.setContent(view);
            // add the tab to the tabpane
            this.getTabpane().getTabs().add(tab);
            //set the tab selected
            this.getTabpane().getSelectionModel().select(tab);
            
            newEngine.getLoadWorker().stateProperty().addListener((p,o,n)->{
               if(n == Worker.State.SUCCEEDED)
               {
                   
                   String location = newEngine.getLocation();
                   this.searchField.setText(location);
                   
               }
           });
            
            newEngine.locationProperty().addListener((prop,old,n) -> {
              //  System.out.println(n);
//                if(n.endsWith(".pdf"))
//                {
//                    try {
//                        java.net.URL ur = new java.net.URL(n.trim());
//                        InputStream in = ur.openStream();
//                        File outFile = new File("C:/Users/sunday enyinna lucky/Desktop/javafx.pdf");
//                        OutputStream out = new FileOutputStream(outFile);
//                        int value;
//                        while((value = in.read()) != -1)
//                        {
//                            out.write(value);
//                        }
//                        System.out.println("Done");
//                        
//                    } catch (MalformedURLException ex) {
//                        Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                else
               // {
                    try {
                       // System.out.println("No");
                        java.net.URL ur = new java.net.URL(n.trim());
                        HttpURLConnection con = (HttpURLConnection)ur.openConnection();
                        con.setRequestMethod("GET");
                        con.setDoOutput(true);
                        String name = ur.getFile();
                        InputStream content = (InputStream)con.getInputStream();
                        String type = con.getHeaderField("Content-Type");
                        String file = ur.getFile();
                        System.out.println(type);
                        System.out.println("File Name: " + file);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}   
            });
        }
        
        else
        {
            //set the style
            this.searchField.setStyle("-fx-border-width: 0.1mm; -fx-border-color: red");
            this.searchField.setPromptText("Enter a url to load");
            this.searchField.requestFocus();
           // disappear(this.getProgress());
        }
    }
    
    
    public void addJavaScriptFunctionality(WebEngine engine)
    {
        jsHandler = new JSHandler();
        engine.getLoadWorker().stateProperty().addListener((p,o,n) -> {
            if(n == Worker.State.SUCCEEDED)
            {
                JSObject window = (JSObject)engine.executeScript("window");
                window.setMember("javascriptHandler", jsHandler);
                window.setMember("browser", this );
            }
        });
    }

    private String getTabText(String path) 
    {
        String title = "New Tab";
        switch(path)
        {
            case "managementregistrationpage.html": title = "School Registration"; break;
            case "aboutuspage.html": title = "About Us"; break;
            case "contactuspage.html": title = "Contact Us"; break;
            case "managementloginpage.html": title = "Administrator Login"; break;
            case "staffloginpage.html": title = "Teacher Login"; break;
            case "studentloginpage.html": title = "Student Login"; break;
            default: title = "New Tab";break;
        }
       
        return title;
    }


    private String getLoadableUrl(String url) 
    {
        // checking conditions
        if(url.contains("http://") || url.contains("https://"))
        {
            return url.trim();
        }
        else
        {
            String loadableString = "http://" + url;
            return loadableString;
        }
    }

    /**
     * This method refreshes the current page regardless of the page on initial selection
     */
    private void refreshCurrentPage() 
    {
        this.getTabpane().getTabs().stream().filter((tab) -> (tab.isSelected() && tab.getContent() instanceof WebView)).forEachOrdered((tab) -> {
            ((WebView)tab.getContent()).getEngine().reload();
        });
    }
    
    /**
     * This code loads the home page if not existent or simply selects it if already in tab pane
     * @throws URISyntaxException
     * @throws MalformedURLException 
     */
    public void goHome() throws URISyntaxException, MalformedURLException
    {
        String homepath = "springarr/View/html/Homepage.html";
        URL url = this.getClass().getClassLoader().getResource(homepath).toURI().toURL();
        String loadable = url.toExternalForm();
        //look for the first tab which is the home tab
        Tab hometab = this.getTabpane().getTabs().get(0);
        ((WebView)hometab.getContent()).getEngine().load(loadable);
        this.getTabpane().getSelectionModel().select(hometab);
    }
    
    public void loadStaff() throws IOException
    {
        addStaffTab();
    }
    
    public void loadStudent() throws IOException
    {
        addStudentTab();
    }
    
    public void loadAdmin() throws IOException
    {
        addAdminTab();
    }

    /**
     * This code makes a control disappear from the scene graph
     * @param control 
     */
    private void disappear(Control control) 
    {
        control.setPrefWidth(0);
        control.setPrefHeight(0);
        control.setVisible(false);
    }
    
    /**
     * This code makes a control appear from the scene graph 
     * @param control
     * @param width
     * @param height 
     */
    private void appear(Control control, double width, double height)
    {
        control.setPrefWidth(width);
        control.setPrefHeight(height);
        control.setVisible(true);
    }
    
    /**
     * This method add the staff fxml tab to the tabpane
     * @param fxmlUrl
     * @throws IOException 
     */
    private void addStaffTab() throws IOException
    {
        String fxmlUrl = "springarr/View/fxml/StaffDashboard.fxml";
        // create a new tab
        /** Get the Tab that states student sign in */
        ObservableList<Tab> tabs = this.getTabpane().getTabs();
        Tab fxmlTab = new Tab();
        for(Tab tab : tabs)
        {
            String text = tab.getText();
            if(text.equals("Teacher Login"))
            {
                this.getTabpane().getTabs().remove(tab);
                break;
            }
        }
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource(fxmlUrl));
        //set the properties of the root 
        // set the title and the content
        fxmlTab.setText("Staff Dashboard");
        fxmlTab.setContent(root);
        fxmlTab.setOnSelectionChanged(event ->{
            if(!fxmlTab.isSelected())
            {
                this.getMainbox().getChildren().add(0, this.getTopbar());
            }
            else
                this.getMainbox().getChildren().remove(this.getTopbar());
        });
        // add it to the tabpane
        this.getTabpane().getTabs().add(fxmlTab);
        this.getTabpane().getSelectionModel().select(fxmlTab);
       // this.getMainbox().getChildren().remove(this.getTopbar());
    }
    
    /**
     * This method add the staff fxml tab to the tabpane
     * @param fxmlUrl
     * @throws IOException 
     */
    private void addAdminTab() throws IOException
    {
        String fxmlUrl = "springarr/View/fxml/ManagementDashboard.fxml";
         /** Get the Tab that states student sign in */
        ObservableList<Tab> tabs = this.getTabpane().getTabs();
        Tab fxmlTab = new Tab();
        for(Tab tab : tabs)
        {
            String text = tab.getText();
            if(text.equals("Administration Login"))
            {
                this.getTabpane().getTabs().remove(tab);
                break;
            }
        }
        
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource(fxmlUrl));
        //set the properties of the root 
        // set the title and the content
        fxmlTab.setText("Management Dashboard");
        fxmlTab.setContent(root);
        // add it to the tabpane
        this.getTabpane().getTabs().add(fxmlTab);
        this.getTabpane().getSelectionModel().select(fxmlTab);
    }
    
    /**
     * This method add the staff fxml tab to the tabpane
     * @param fxmlUrl
     * @throws IOException 
     */
    private void addStudentTab() throws IOException
    {
        String fxmlUrl = "springarr/View/fxml/Student_DashBoard.fxml";
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource(fxmlUrl));
        // create a new scene and set the root in it
        Scene scene = new Scene(root);
        // get the current stage
        Stage stage = new Stage();
        // set the properties of the stage
        stage.setScene(scene);
        stage.setTitle("Student Dashboard");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    private Map <String, Tab> getTabMap()
    {
        // create an ampty map 
        Map<String, Tab> map = new HashMap<>();
        // get the observable list of tabs
        ObservableList<Tab> tabs = this.getTabpane().getTabs();
        tabs.forEach((tab) -> {
            map.put(tab.getText(), tab);
        });
        return map;
    }
    
}
