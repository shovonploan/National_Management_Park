package scenes.visitor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class homepart1Controller {
    @FXML private AnchorPane main;
    public void btn_return(ActionEvent event) throws Exception {
        Stage stage = (Stage) main.getScene().getWindow();
        stage.close();
        System.gc();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("visitor.fxml"));
        Scene scene = new Scene(root, 1040, 650);
        scene.getStylesheets().add(getClass().getResource("visitor.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
