package com.sda.vendingmachine.gui;

import com.sda.vendingmachine.persistence.entities.Item;
import com.sda.vendingmachine.service.Coin;
import com.sda.vendingmachine.service.IVendingMachine;
import com.sda.vendingmachine.service.ItemType;
import com.sda.vendingmachine.service.VendingMachineImpl;
import com.sda.vendingmachine.service.exceptions.NotFullPaidException;
import com.sda.vendingmachine.service.exceptions.NotSufficientChangeException;
import com.sda.vendingmachine.service.exceptions.SoldOutException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.Table;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import static com.sda.vendingmachine.service.ItemType.CHOCOLATE_BAR;
import static javafx.geometry.Pos.BOTTOM_LEFT;
import static javafx.geometry.Pos.CENTER_RIGHT;


public class JavaFxApp extends Application {

    private IVendingMachine vendingMachine;
    private ItemType selectedItem;
    private Coin selectedCoin;
    private int selectedQuantity;
    private double currentAmount;

    public void start(Stage primaryStage) throws Exception {

        vendingMachine = new VendingMachineImpl();

        Stage window = primaryStage;
        window.setTitle("VENDING MACHINE");

        Text welcomeText = new Text("Welcome");

        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.TOP_CENTER);
        ListView<String> itemsList = new ListView<>();
        fillListView(itemsList);

        itemsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String name = newValue.split(":")[0];
                selectedItem = ItemType.valueOf(name);
                try {
                    vendingMachine.selectProduct(selectedItem);
                } catch (SoldOutException e) {
                    e.printStackTrace();
                    showAlert(e.getMessage());
                } catch (NotFullPaidException e) {
                    e.printStackTrace();
                    showAlert(e.getMessage());
                } catch (NotSufficientChangeException e) {
                    e.printStackTrace();
                    showAlert(e.getMessage());
                }
            }
        });

        hBox.getChildren().add(itemsList);

        GridPane moneyInsChangeGridPane = new GridPane();
        moneyInsChangeGridPane.setAlignment(Pos.BOTTOM_RIGHT);
        moneyInsChangeGridPane.setVgap(15);
        moneyInsChangeGridPane.add(new Label("Money Inserted: "), 0, 0);
        moneyInsChangeGridPane.add(new Label("Change: "), 0, 1);
        Label insertedMoney = new Label();
        moneyInsChangeGridPane.add(insertedMoney, 1, 0);
        Label change = new Label();
        moneyInsChangeGridPane.add(change, 1, 1);
        hBox.getChildren().add(moneyInsChangeGridPane);

        AnchorPane root = new AnchorPane();

        Button purchaseButton = new Button("PURCHASE");
        purchaseButton.setAlignment(BOTTOM_LEFT);

        purchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vendingMachine.purchaseItem(selectedItem);
                itemsList.getItems().clear();
                fillListView(itemsList);
                change.setText(String.valueOf(currentAmount - selectedItem.getPrice()));
            }
        });

        Button clearButton = new Button("CLEAR");
        clearButton.setOnAction(event -> {
            System.out.println("Choice cleared");
        });

        HBox hBoxLeftSideButton = new HBox(10, purchaseButton, clearButton);
        root.getChildren().addAll(hBoxLeftSideButton);
        AnchorPane.setLeftAnchor(hBoxLeftSideButton, 10d);
        AnchorPane.setBottomAnchor(hBoxLeftSideButton, 10d);

        HBox hbButons = new HBox();
        hbButons.setPadding(new Insets(10,10,10,10));
        Button button4 = new Button("X");
        button4.setOnMouseClicked(event -> System.exit(0));
        button4.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        button4.setStyle("-fx-base: #f42811;");
        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10,1);
        hbButons.getChildren().addAll(spacer, button4);

        VBox comboVertical = new VBox(15);

        Button insertButton = new Button("Insert");
        insertButton.setAlignment(Pos.CENTER);

        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vendingMachine.insertMoney(selectedCoin, selectedQuantity);
                currentAmount += selectedCoin.getValue() * selectedQuantity;
                insertedMoney.setText(String.valueOf(currentAmount));
            }
        });

        ComboBox<String> comboBoxCoin = new ComboBox<String>();
        comboBoxCoin.getItems().addAll("PENNY", "NICKEL", "DIME", "QUARTER");
        comboBoxCoin.setPromptText("Choose coin");

        comboBoxCoin.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    selectedCoin = Coin.valueOf(newValue);
                }
        );

        TextField inputInsert = new TextField();
        inputInsert.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

               selectedQuantity = Integer.valueOf(newValue);
            }
        });


        comboVertical.setAlignment(Pos.CENTER_LEFT);
        comboVertical.getChildren().addAll(comboBoxCoin,inputInsert,insertButton);
        hBox.getChildren().add(comboVertical);

        VBox mainVbox = new VBox(10);
        mainVbox.setAlignment(Pos.CENTER);
        mainVbox.setPadding(new Insets(10, 30, 30, 30));
        mainVbox.getChildren().addAll(hbButons, welcomeText, hBox, root);
        mainVbox.setSpacing(30);
        Scene scene = new Scene(mainVbox, 1000, 700);
        window.setScene(scene);
        window.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR ON SELECT PRODUCT");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void fillListView(ListView<String> itemsList) {
        vendingMachine.getItems().forEach(item -> {
            String row = item.getName() + ": " + item.getPrice() + " " + item.getQuantity();
            itemsList.getItems().add(row);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}


