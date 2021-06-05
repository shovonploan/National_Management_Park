package scenes.mt;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resource.Equipment;
import user.MaintenanceTechnician;

public class MtController implements Initializable {
    @FXML private AnchorPane main;
    @FXML private TextArea ci_ta;
    @FXML private TextArea co_ta;
    @FXML private TextArea rp_ta;
    @FXML private TextArea rq_ta;
    @FXML private TextArea querry_ta;
    @FXML private ComboBox querry_type;
    @FXML private ComboBox bill_box;
    @FXML private TextField rq_itn;
    @FXML private TextField rq_itb;
    @FXML private TextField rq_itq;
    @FXML private Label ci_status;
    @FXML private Label co_status;
    @FXML private Label rp_status;
    @FXML private Label rq_status;
    @FXML private TextField p_name;
    @FXML private TextField p_brand;
    @FXML private TextField p_quantity;

    @FXML private TableView<Equipment> table;
    @FXML private TableColumn<Equipment, String> name;
    @FXML private TableColumn<Equipment, String> brand;
    @FXML private TableColumn<Equipment, Integer> quantity;
    private String filename;
    private MaintenanceTechnician m1;


    ObservableList<String> list1 = FXCollections.observableArrayList("Involved Accident Issue", "Technical issue", "Issue with a visitor", "Issue with a stuff");
    ObservableList<String> list2 = FXCollections.observableArrayList("Electricity","Water");    
        
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        bill_box.setItems(list2);
        querry_type.setItems(list1);
        name.setCellValueFactory(new PropertyValueFactory<Equipment, String>("name"));
        brand.setCellValueFactory(new PropertyValueFactory<Equipment, String>("brand"));
        quantity.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("quantity"));
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
    public void initial(){
        rp_ta.setText(m1.getReport());                                                                              
        rq_ta.setText(m1.getRequest()); 
    }
    public void setUser(MaintenanceTechnician u) 
    {
        m1=u;
        filename=u.getUsername();
        initial();
    }
    
    public void chech_in_btn(ActionEvent event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss-dd:MM:yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        String tim = "Check in: "+dtf.format(now)+" "+ci_ta.getText() +"\n";
        ci_status.setText("Done");
        m1.saveLog(tim);
    }
    public void chech_out_btn(ActionEvent event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss-dd:MM:yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        String tim = "Check out: "+dtf.format(now)+" "+co_ta.getText() +"\n";
        co_status.setText("Done");
        m1.saveLog(tim);
    }
    public void report_btn(ActionEvent event){
        String tim = querry_type.getValue()+"\n"+querry_ta.getText()+"\n";
        m1.saveReport(tim);
        rp_status.setText("Done");
    }
    public void request_btn(ActionEvent event){
        String tim =  rq_itn.getText()+" "+rq_itb.getText()+" "+rq_itq.getText()+"\n";
        m1.saveRequest(tim);
        rq_status.setText("Done");
    }
    public void c_add_onClick(){
            Equipment neweq = new Equipment(p_name.getText(),
                                                p_brand.getText(),
                                                Integer.parseInt(p_quantity.getText()));
            table.getItems().add(neweq);
        }
}
