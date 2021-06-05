package scenes.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resource.Donation;
import resource.Ticket;
import user.Visitor;

public class VisitorController implements Initializable {
    // @FXML private ImageView img_1;
    @FXML private AnchorPane main;
    @FXML private TextField purchase_name;
    @FXML private TextField purchase_email;
    @FXML private TextField purchase_contact;
    @FXML private TextField donation_name;
    @FXML private TextField donation_email;
    @FXML private TextField donation_contact;
    @FXML private TextField feed_name;
    @FXML private TextField feed_email;
    @FXML private TextField feed_contact;
    @FXML private TextArea feed;
    @FXML private TextField donation_amount;
    @FXML private DatePicker purchase_date;
    @FXML private ComboBox<String> purchase_people;
    @FXML private ComboBox<String> purchase_option;
    @FXML private ComboBox<String> donation_option;
    @FXML private RadioButton purchase_rb1;
    ObservableList<String> list1 = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> list2 = FXCollections.observableArrayList("bKash","Nogod","Rocket","Credit-card","Cash On Visit");
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // try {
        //     img_1 = new ImageView(new Image(new FileInputStream("1.jpg")));
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }
        purchase_people.setItems(list1);
        purchase_option.setItems(list2);
        donation_option.setItems(list2);
    }

    public void btn_exit(ActionEvent event) {
        System.exit(0);
    }

    public void btn_back(ActionEvent event) throws Exception {
        Stage stage = (Stage) main.getScene().getWindow();
        stage.close();
        System.gc();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/login/Main.fxml"));
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/scenes/login/login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void btn_more_informations(ActionEvent event) throws Exception{
        Stage stage = (Stage) main.getScene().getWindow();
        stage.close();
        System.gc();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("homepart1.fxml"));
        Scene scene = new Scene(root, 1050, 500);
        scene.getStylesheets().add(getClass().getResource("homepart1.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void buyTicket(ActionEvent event) throws Exception
    {
        Ticket t1=new Ticket();
        t1.setName(purchase_name.getText());
        t1.setEmail(purchase_email.getText());
        t1.setContact(purchase_contact.getText());
        t1.setDate(this.purchase_date.getValue().toString());
        t1.setNumber_of_tickets(Integer.parseInt(this.purchase_people.getValue()));
        t1.setPayment_options(this.purchase_people.getValue());
        boolean flag = false;
        if(purchase_rb1.isSelected()){
            t1.setGuide(true);
        }
        t1.saveData();
    }
    public void makeDonation(ActionEvent event)throws Exception{
        Donation d1 = new Donation();
        d1.setName(donation_name.getText());
        d1.setEmail(donation_email.getText());
        d1.setContact(purchase_contact.getText());
        d1.setPayment_options(this.donation_option.getValue());
        d1.setAmount(donation_amount.getText());
        d1.saveData();
    }
    public void makeFeed(ActionEvent event)throws Exception{
        Visitor v1= new Visitor();
        v1.setName(feed_name.getText());
        v1.setEmail(feed_email.getText());
        v1.setContact(feed_contact.getText());
        v1.setFeed(feed.getText());
        v1.saveData();
    }
}
