package com.dealership.controllers.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SlideController implements Initializable{
    @FXML 
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    @FXML
    private void homeHandler(MouseEvent event){
            bp.setCenter(ap);
    }
    @FXML
    private void userHandler(MouseEvent event ){
       
            loadPage("user_view");
           
    }
    @FXML
    private void inventoryHandler(MouseEvent event ){
    
            loadPage("inventory_view");
           
    }
    @FXML
    private void saleHandler(MouseEvent event ){

            loadPage("sales_view");
           
    }
    @FXML
    private void settingHandler(MouseEvent event ){
       
            loadPage("setting_view");
           
    }
    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/dealership/views/admin_view/" +  page + ".fxml"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        bp.setCenter(root);
    }

}
