package springarr.Controller;

import javafx.scene.control.Accordion;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sunday enyinna lucky
 */
public class StaffDashboardUtility 
{
    
      // Static method to receive the File menu option of the  menubar of the dashboard and populate it 
    
    public static void populateFileMenu(Menu fileMenu)
    {
        // create a menu for New and its menu item 
        Menu newMenu = new Menu("New");
        newMenu.getItems().addAll(
                new MenuItem("Student"),
                new MenuItem("Subject"),
                new MenuItem("Exam"),
                new MenuItem("Test")
        );
        
        // create the menu for Open and set its menu items
        Menu openMenu = new Menu("Open");
        openMenu.getItems().addAll(
                new MenuItem("Exam Questions"),
                new MenuItem("Test Questions")
        );
        
        // create the print menu
        Menu printMenu = new Menu("Print");
        // create the exit menu
        Menu exitMenu = new Menu("Exit");
        // add all menu to the ile menu
        fileMenu.getItems().addAll(newMenu, openMenu, printMenu, exitMenu);
    }
    
    // Static method to populate the Personal Menu of the dashboard
    public static void populatePersonalMenu(Menu pMenu)
    {
        String[] options = 
        {
            "Display My Profile", "Change Password", "Change Username","Change Profile Photo",
            "Update Profile"
        };
        // Create the MenuItems array to store the menu items 
        MenuItem[] items = new MenuItem[options.length];
        // Iterate and fill the menu items array
        for(int i=0;i<options.length;i++)
            items[i] = new MenuItem(options[i]);
        //store all items in the personal menu
        pMenu.getItems().addAll(items);
    }
    
    // Static method to populate the Students Menu
     public static void populateStudentMenu(Menu studentMenu)
    {
        String[] options = 
        {
            "Add Student", "Display Student Profile","Update Student Profile Photo","Update Student Profile",
            "View All Students", "Remove Student"
        };
        // Create the MenuItems array to store the menu items 
        MenuItem[] items = new MenuItem[options.length];
        // Iterate and fill the menu items array
        for(int i=0;i<options.length;i++)
            items[i] = new MenuItem(options[i]);
        //store all items in the personal menu
        studentMenu.getItems().addAll(items);
    }
    
     // Static method to fill the subject menu
      public static void populateSubjectMenu(Menu subjectMenu)
    {
        String[] options = 
        {
            "Add Subject", "Update Subject Details", "Remove Subject"
        };
        // Create the MenuItems array to store the menu items 
        MenuItem[] items = new MenuItem[options.length];
        // Iterate and fill the menu items array
        for(int i=0;i<options.length;i++)
            items[i] = new MenuItem(options[i]);
        //store all items in the personal menu
        subjectMenu.getItems().addAll(items);
        // Create the upload submenu
        Menu uploadSubMenu = new Menu("Upload Scores");
        uploadSubMenu.getItems().addAll(
                    new MenuItem("Upload Test(CA) Score"),
                    new MenuItem("Upload Exam Score"),
                    new MenuItem("Upload Both Test and Exam")
        );
       // Add the upload to the subject menu
       subjectMenu.getItems().add(uploadSubMenu);
    }
    
  // Static Method to populate the exam menu
      public static void populateExamMenu(Menu examMenu)
      {
          // create all menu items and submenus 
          MenuItem addItem = new MenuItem("Add Questions");
          MenuItem displayItem = new MenuItem("Display Questions");
          MenuItem updateItem = new MenuItem("Update Exam Details");
          examMenu.getItems().addAll(addItem,displayItem,updateItem);
      }
      
      //static method to populate the test menu
      public static void populateTestMenu(Menu testMenu)
      {
          // create all menu items and submenus 
          MenuItem addItem = new MenuItem("Add Questions");
          MenuItem displayItem = new MenuItem("Display Questions");
          MenuItem updateItem = new MenuItem("Update Exam Details");
          testMenu.getItems().addAll(addItem,displayItem,updateItem);
      }
      
      // static method to populate the Academic Report Menu
      public static void populateAcademicReportMenu(Menu armMenu)
      {
          MenuItem reportItem = new MenuItem("View Term Report");
          MenuItem annualReportItem = new MenuItem("View Annual Reeport");
          MenuItem broadsheetItem = new MenuItem ("View Term Broadsheet");
          MenuItem annualBroadsheetItem = new MenuItem("View Annual Broadsheet");
          armMenu.getItems().addAll(reportItem, annualReportItem,broadsheetItem, annualBroadsheetItem);
      }
      
      
      /* Static Methods to populate the Acordion of the staff Dashboard */
      
      public static void populateAccordion(Accordion accordionPane)
      {
          // get the reference of the personal profile titled pane
          TitledPane personal = accordionPane.getPanes().get(0);
          GridPane personalGrid = new GridPane();
         
      }
}

