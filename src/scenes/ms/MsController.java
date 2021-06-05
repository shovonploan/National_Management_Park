package scenes.ms;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resource.NewStuff;
import resource.OldStuff;
import user.MaintenanceSupervisor;


public class MsController implements Initializable {
    @FXML private AnchorPane main;
    @FXML private TextArea ci_ta;
    @FXML private TextArea co_ta;
    @FXML private TextArea rp_ta;
    @FXML private TextArea w_ta;
    @FXML private Label ci_status;
    @FXML private Label co_status;

    @FXML private TableView<NewStuff> table1;
    @FXML private TableView<OldStuff> table2;
    @FXML private TableColumn<NewStuff, String> name1;
    @FXML private TableColumn<OldStuff, String> name;
    @FXML private TableColumn<OldStuff, String> user_type;
    private String filename;
    private MaintenanceSupervisor m1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        name1.setCellValueFactory(new PropertyValueFactory<NewStuff, String>("name"));
        name.setCellValueFactory(new PropertyValueFactory<OldStuff, String>("name"));
        user_type.setCellValueFactory(new PropertyValueFactory<OldStuff, String>("user_type"));
        ObservableList<OldStuff> list5;
        try {
            String mystring="";
            String [] temp;
            File file= new File("src/files/users/new/new.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                mystring += sc.nextLine();
            }
            temp = mystring.split(",");
            for(String t:temp)
            {
                table1.getItems().add(new NewStuff(t));
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/files/users/users.bin"));
            User d;
            while(true) {
                d = (User) in.readObject();
                table2.getItems().add(new OldStuff(d.getName(),d.getUsertype()));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        table1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
    }
    
    public void initial(){
        String mystring="";
        
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

    public void setUser(MaintenanceSupervisor u)
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
    public void view_btn(ActionEvent event)throws Exception{
        NewStuff n = table1.getSelectionModel().getSelectedItem();
        String f=n.getName();
        String p="";
        try {
            File file= new File("src/files/users/new/"+f+".txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                p += sc.nextLine();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/ms/view.fxml"));
        Parent root = loader.load();
        ViewController newcon = loader.getController();
        newcon.setText(p);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void acpt_btn(ActionEvent event)throws Exception{
        NewStuff n = table1.getSelectionModel().getSelectedItem();
        String f=n.getName();
        String p="";
        String []temp;
        try {
            File file= new File("src/files/users/new/"+f+".txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                p += sc.nextLine();
            }
            temp=p.split(",");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public void reject_btn(ActionEvent event)throws Exception{
        ObservableList<NewStuff> ll;
        ll=table1.getItems();
        NewStuff re= table1.getSelectionModel().getSelectedItem();
        ll.remove(re);
    }
}
