package com.dealership.controllers.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch", "unused"})
public class SlideController implements Initializable {
        @FXML
        private BorderPane bp;
        @FXML
        private AnchorPane ap;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
                loadPage("dashboard_view");
        }

        @FXML
        private void homeHandler(MouseEvent event) {
                loadPage("dashboard_view");
        }

        @FXML
        private void userHandler(MouseEvent event) {

                loadPage("user_view");

        }

        @FXML
        private void inventoryHandler(MouseEvent event) {

                loadPage("inventory_view");

        }

        @FXML
        private void saleHandler(MouseEvent event) {

                loadPage("sales_view");

        }

        @FXML
        private void settingHandler(MouseEvent event) {

                loadPage("setting_view");

        }

        private void loadPage(String page) {
                AnchorPane view = null;
                try {
                        view = FXMLLoader.load(
                                        getClass().getResource("/com/dealership/views/admin_view/" + page + ".fxml"));
                        AnchorPane.setTopAnchor(view, 0.0);
                        AnchorPane.setBottomAnchor(view, 0.0);
                        AnchorPane.setLeftAnchor(view, 0.0);
                        AnchorPane.setRightAnchor(view, 0.0);
                } catch (Exception e) {
                        System.out.println(e.toString());
                }
                bp.setCenter(view);
        }

}
