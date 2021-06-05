package scenes.medicalstuff;

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
import resource.Job;
import user.MedicalStuff;

public class MedicalstuffController implements Initializable {
    @FXML private AnchorPane main;
    @FXML private TextArea ci_ta;
    @FXML private TextArea co_ta;
    @FXML private TextArea querry_ta;
    @FXML private ComboBox querry_type;
    @FXML private TextField rq_itn;
    @FXML private TextField rq_itb;
    @FXML private TextField rq_itq;
    @FXML private Label ci_status;
    @FXML private Label co_status;
    @FXML private Label rp_status;
    @FXML private Label rq_status;

    @FXML private TableView<Job> table;
    @FXML private TableColumn<Job, String> task;
    @FXML private TableColumn<Job, String> day;
    @FXML private TableColumn<Job, Integer> hour;
    private String filename;
    MedicalStuff m1;


    ObservableList<String> list1 = FXCollections.observableArrayList("Involved Accident Issue", "Technical issue", "Issue with a visitor", "Issue with a stuff");
        
    public ObservableList<Job> list2;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
        querry_type.setItems(list1);
	}
    public void initial(){
        String mystring="";
        try {
            File file= new File("src/files/users/medicalstuff/"+filename+"_job.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                mystring += sc.nextLine()+"\n";
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        list2 = FXCollections.observableArrayList(
        new Job("Sunday",mystring,12),
        new Job("Monday",mystring,12),
        new Job("Tuesday",mystring,12),
        new Job("Wednesday",mystring,12),
        new Job("Thursday",mystring,12),
        new Job("Friday",mystring,12),
        new Job("Saturday",mystring,12)
    );
        day.setCellValueFactory(new PropertyValueFactory<Job, String>("day"));
        task.setCellValueFactory(new PropertyValueFactory<Job, String>("task"));
        hour.setCellValueFactory(new PropertyValueFactory<Job, Integer>("hour"));
        table.setItems(list2);
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

    public void setUser(MedicalStuff u)
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
}
