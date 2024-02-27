package com.example.assignment1java;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChartController {
    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    public void initialize() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Proteins");
        try (Connection connection = new DatabaseConnector().connect();
             PreparedStatement statement = connection.prepareStatement("SELECT food_name, protein_per_100g FROM protein_in_food");
             ResultSet resultSet = statement.executeQuery()) {

            List<Food> foodList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("food_name");
                double protein = resultSet.getDouble("protein_per_100g");
                foodList.add(new Food(name, protein));
                series.getData().add(new XYChart.Data<String, Double>(name, protein));
                System.out.println(name + " " + protein);
            }

            barChart.getData().add(series);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void switchToTable(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProteinApplication.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 440);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
