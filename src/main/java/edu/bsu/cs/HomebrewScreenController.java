package edu.bsu.cs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.Objects;


public class HomebrewScreenController {

    public TabPane homebrewPane;

    protected boolean checkForNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception exception) {
            if (!input.isBlank()) {
                displayRangeInputAlert();
            }
            return false;
        }
    }

    protected static void displayRangeInputAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input is not valid");
        alert.setHeaderText("Please enter an whole number.");
        alert.show();
    }

    @FXML
    private void goToMain() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

    @FXML
    private void goToFilters() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FilterScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

}
