package com.example.controller;

import com.example.model.InventoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<InventoryItem> tableView;

    @FXML
    private TableColumn<InventoryItem, String> nameColumn;

    @FXML
    private TableColumn<InventoryItem, Integer> quantityColumn;

    @FXML
    private TableColumn<InventoryItem, Double> priceColumn;

    private ObservableList<InventoryItem> inventoryList;

    @FXML
    private void initialize() {
        // Initialize the inventory list
        inventoryList = FXCollections.observableArrayList();

        // Set up the columns in the table
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Bind the list to the table
        tableView.setItems(inventoryList);

        // Handle row selection
        tableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> populateFields(newValue));
    }

    private void populateFields(InventoryItem item) {
        if (item != null) {
            nameField.setText(item.getName());
            quantityField.setText(String.valueOf(item.getQuantity()));
            priceField.setText(String.valueOf(item.getPrice()));
        }
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText();
        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(quantityField.getText());
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Quantity must be an integer and Price must be a number.");
            return;
        }

        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Item name cannot be empty.");
            return;
        }

        InventoryItem newItem = new InventoryItem(name, quantity, price);
        inventoryList.add(newItem);
        clearFields();
    }

    @FXML
    private void handleUpdate() {
        InventoryItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String name = nameField.getText();
            int quantity;
            double price;

            try {
                quantity = Integer.parseInt(quantityField.getText());
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Quantity must be an integer and Price must be a number.");
                return;
            }

            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Item name cannot be empty.");
                return;
            }

            selectedItem.setName(name);
            selectedItem.setQuantity(quantity);
            selectedItem.setPrice(price);
            tableView.refresh();
            clearFields();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an item to update.");
        }
    }

    @FXML
    private void handleDelete() {
        InventoryItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            inventoryList.remove(selectedItem);
            clearFields();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an item to delete.");
        }
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
