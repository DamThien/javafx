package com.example.ql_skateboarding;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
public class HelloApplication extends Application {
    Label lbName = new Label("Enter name:");
    TextField tfName = new TextField();


    Label lbBrand = new Label("Enter brand:");
    TextField tfBrand = new TextField();


    Label lbQuantity = new Label("Enter quantity:");
    TextField tfQuantity = new TextField();


    Label lbImage = new Label("Enter image:");
    TextField tfImage = new TextField();


    Label lbPrice = new Label("Enter price:");
    TextField tfPrice = new TextField();


    @Override
    public void start(Stage stage) throws IOException {

        boolean status = false;

        DBConnect connection = new DBConnect();

        VBox root = new VBox();

        VBox skateboardingRoot = new VBox();

        VBox vInsertSkateboarding = new VBox();

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                connection.insertSkateboarding(new Skateboarding(tfName.getText(),tfBrand.getText(),Integer.parseInt(tfQuantity.getText()), tfImage.getText(), Float.parseFloat(tfPrice.getText())));
                getThendisplaySkateboarding(skateboardingRoot, connection);
            }


        });

        Button btnEdit = new Button("Update");
        btnEdit.setOnAction(event -> {
            connection.updateSkateboarding(new Skateboarding(tfName.getText(),tfBrand.getText(),Integer.parseInt(tfQuantity.getText()),tfImage.getText(), Float.parseFloat(tfPrice.getText()),Integer.parseInt(tfName.getId())));
            getThendisplaySkateboarding(skateboardingRoot, connection);
        });

        vInsertSkateboarding.getChildren().addAll(lbName, tfName,lbBrand,tfBrand,lbQuantity,tfQuantity ,lbImage,tfImage,
                lbPrice, tfPrice, btnAdd, btnEdit);

        root.getChildren().addAll(vInsertSkateboarding, skateboardingRoot);

        getThendisplaySkateboarding(skateboardingRoot, connection);

        Scene scene = new Scene(root, 1500, 800);
        stage.setTitle("Welocome !");
        stage.setScene(scene);
        stage.show();
    }



    void displaySkateboarding(DBConnect connection, VBox root, List<Skateboarding>
            skateboardings) {
        root.getChildren().clear();
        for (int i = 0; i < skateboardings.size(); i++) {
            final int finialI = i;
            HBox skateboardingBox = new HBox();
            Label lbId = new Label("" + skateboardings.get(i).id);
            Label lbName = new Label(skateboardings.get(i).name);
            Label lbBrand = new Label("" + skateboardings.get(i).brand);
            Label lbQuantity = new Label(""+skateboardings.get(i).quantity);
            Label lbImage = new Label("" + skateboardings.get(i).image);
            Image image = new Image(""+skateboardings.get(i).image);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            imageView.setPreserveRatio(true);



            Label lbPrice = new Label("" + skateboardings.get(i).price);


            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(actionEvent -> {
                System.out.println("Click delete " + skateboardings.get(finialI).id);

                connection.deleteSkateboarding(skateboardings.get(finialI).id);
                getThendisplaySkateboarding(root, connection);



            });
            Button btnUpdate = new Button("Update");
            btnUpdate.setOnAction(actionEvent->{
                tfName.setText(String.valueOf((skateboardings.get(finialI).name)));
                tfBrand.setText(String.valueOf((skateboardings.get(finialI).brand)));
                tfName.setId(""+skateboardings.get(finialI).id);
                tfQuantity.setText(String.valueOf((skateboardings.get(finialI).quantity)));
                tfImage.setText(String.valueOf((skateboardings.get(finialI).image)));

                tfPrice.setText(String.valueOf((skateboardings.get(finialI).price)));
            });
            skateboardingBox.setSpacing(70);
            skateboardingBox.getChildren().addAll(lbId, lbName,lbBrand,lbQuantity,imageView,lbPrice, btnDelete, btnUpdate);
            root.getChildren().add(skateboardingBox);
        }
    }

    private void getThendisplaySkateboarding(VBox root, DBConnect connection) {
        List<Skateboarding> skateboardings = connection.getSkateboarding();
        displaySkateboarding(connection, root, skateboardings);
    }

    public static void main(String[] args) {
        launch();
    }
}