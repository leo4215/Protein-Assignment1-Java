package com.example.assignment1java;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TableController {
    @FXML
    private TableView<Food> table;


    @FXML
    private TableColumn<Food, String> food_table;


    @FXML
    private TableColumn<Food, Double> protein_table;

    @FXML
    protected void initialize() {
        initializeColumns();
        populateTable();
    }


    private void initializeColumns() {
        food_table.setCellValueFactory(cellData -> {
            StringProperty property = new SimpleStringProperty();
            property.setValue(cellData.getValue().name());
            return property;
        });


        protein_table.setCellValueFactory(cellData -> {
            DoubleProperty property = new SimpleDoubleProperty();
            property.setValue(cellData.getValue().protein());
            return property.asObject();
        });
    }


    private void populateTable() {
        try (Connection connection = new DatabaseConnector().connect();
             PreparedStatement statement = connection.prepareStatement("SELECT food_name, protein_per_100g FROM protein_in_food");
             ResultSet resultSet = statement.executeQuery()) {


            List<Food> foodList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("food_name");
                double protein = resultSet.getDouble("protein_per_100g");
                foodList.add(new Food(name, protein));
            }
            table.getItems().addAll(foodList);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void switchToChart(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProteinApplication.class.getResource("chart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
