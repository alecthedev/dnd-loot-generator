package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class WeaponMakerController {
    public RadioButton simpleRadio;
    public RadioButton martialRadio;
    public ChoiceBox<String> damageDiceChoice;
    public ChoiceBox<String> reachChoice;
    public ChoiceBox<String> weaponRarityChoice;
    public ChoiceBox<String> damageTypeChoice;
    public Pane weaponMaker;
    public TextField weaponNameInput;
    public TextField numberOfDiceInput;
    public TextArea weaponDescription;
    public ToggleButton attunementToggle;
    public TextField weaponRangeInput;
    public Label requiresAttunementLabel;
    public Label longRangeLabel;
    public ToggleGroup proficiency;
    public HomebrewScreenController homebrewScreenController = new HomebrewScreenController();
    public CheckBox thrownCheckBox;
    public CheckBox ammunitionCheckBox;
    public CheckBox versatileCheckBox;
    public CheckBox twoHandedCheckBox;
    public CheckBox specialCheckBox;
    public CheckBox reachCheckBox;
    public CheckBox netCheckBox;
    public CheckBox loadingCheckBox;
    public CheckBox lightCheckBox;
    public CheckBox lanceCheckBox;
    public CheckBox heavyCheckBox;
    public CheckBox finesseCheckBox;

    public List<String> getProperties() {
        List<String> propertyNamesList = new ArrayList<>();
        CheckBox[] properties = {thrownCheckBox,ammunitionCheckBox,twoHandedCheckBox,versatileCheckBox,specialCheckBox,reachCheckBox,netCheckBox,loadingCheckBox,lightCheckBox,lanceCheckBox,heavyCheckBox,finesseCheckBox};
        for (CheckBox property : properties) {
            if (property.isSelected()) {
                propertyNamesList.add(property.getText());
            }
        }
        return propertyNamesList;
    }

    protected String getProficiency(){
        if (simpleRadio.isSelected()){
            return "Simple";
        }else {
            return "Martial";
        }
    }

    public void checkAttunement() {
        requiresAttunementLabel.setVisible(attunementToggle.isSelected());
    }

    public void setLongRangeLabel() {
        if (thrownCheckBox.isSelected()) {
            longRangeLabel.setText("Long Range: " + Integer.parseInt(weaponRangeInput.getText()) * 3);
            ammunitionCheckBox.setDisable(true);
        } else if (ammunitionCheckBox.isSelected()) {
            longRangeLabel.setText("Long Range: " + Integer.parseInt(weaponRangeInput.getText()) * 4);
            thrownCheckBox.setDisable(true);
        }
        else {
            ammunitionCheckBox.setDisable(false);
            thrownCheckBox.setDisable(false);
        }
    }

    public void setRangeSettings() {
        String input = weaponRangeInput.getText();
        if (!input.isBlank() && homebrewScreenController.checkForNumber(input)) {
            ammunitionCheckBox.setDisable(false);
            thrownCheckBox.setDisable(false);
        } else {
            ammunitionCheckBox.setDisable(true);
            thrownCheckBox.setDisable(true);
            weaponRangeInput.clear();
        }
    }

    public List<String> getRangeProperty(List<String> properties) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).contains("*")) {
                properties.set(i, properties.get(i).substring(1) + " (range " + weaponRangeInput.getText() + "/" + longRangeLabel.getText().substring(12) + ")");
            }
        }
        return properties;
    }

    public void checkNumberOfDiceInput() {
        if (!homebrewScreenController.checkForNumber(numberOfDiceInput.getText())) {
            numberOfDiceInput.clear();
        }
    }

    public String collectWeaponDetails(){
        StringBuilder weaponDetails = new StringBuilder();
        weaponDetails.append("Name: ").append(weaponNameInput.getText()).append("\n");
        weaponDetails.append("Proficiency: ").append(getProficiency()).append("\n");
        weaponDetails.append("Damage Dice: ").append(numberOfDiceInput.getText()).append(damageDiceChoice.getValue()).append("\n");
        weaponDetails.append("Reach: ").append(reachChoice.getValue()).append("\n");
        weaponDetails.append("Damage Type: ").append(damageTypeChoice.getValue()).append("\n");
        weaponDetails.append("Properties: ").append(OutputFormatter.formatProperties(getRangeProperty(getProperties()))).append("\n");
        weaponDetails.append("Rarity: ").append(weaponRarityChoice.getValue()).append("\n");
        weaponDetails.append("Attunement: ").append(attunementToggle.isSelected()).append("\n");
        weaponDetails.append("Description:\n").append(weaponDescription.getText()).append("\n");
        System.out.println(weaponDetails);
        return weaponDetails.toString();
    }

}
