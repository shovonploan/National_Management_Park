package scenes.wearhousestuff;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resource.Equipment;
import resource.Job;
import user.WearhouseStuff;

public class WearhousestuffController implements Initializable {
    @FXML private AnchorPane main;
    @FXML private TextArea ci_ta;
    @FXML private TextArea co_ta;
    @FXML private TextArea v_ta;
    @FXML private TextArea querry_ta;
    @FXML private ComboBox querry_type;
    @FXML private TextField c_name;
    @FXML private TextField c_brand;
    @FXML private TextField c_quantity;
    @FXML private Label ci_status;
    @FXML private Label co_status;
    @FXML private Label rp_status;
    @FXML private Label v_status;

    @FXML private TableView<Job> table;
    @FXML private TableColumn<Job, String> task;
    @FXML private TableColumn<Job, String> day;
    @FXML private TableColumn<Job, Integer> hour;

    @FXML private TableView<Equipment> table2;
    @FXML private TableColumn<Equipment, String> name;
    @FXML private TableColumn<Equipment, String> brand;
    @FXML private TableColumn<Equipment, Integer> quantity;
    private String filename;
    private WearhouseStuff w1;


    ObservableList<String> list1 = FXCollections.observableArrayList("Involved Accident Issue", "Technical issue", "Issue with a visitor", "Issue with a stuff");
        
    public ObservableList<Job> list2;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        name.setCellValueFactory(new PropertyValueFactory<Equipment,String>("name"));
        brand.setCellValueFactory(new PropertyValueFactory<Equipment,String>("brand"));
        quantity.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("quantity"));
        try {
			table2.setItems(getEquipments());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // !table2.setEditable(true);
        // !quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        table2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        querry_type.setItems(list1);
    }
    
    public void initial(){
        String mystring="";
        try {
            File file= new File("src/files/users/guide/"+filename+"_job.txt"); //!chnage
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
    
    public void changeQuantityCellEvent(CellEditEvent edittedCell){
        //! Equipment tempep= table2.getSelectionModel().getSelectedItem();
        //! tempep.setQuantity(Integer.parseInt(edittedCell.getNewValue().toString()));
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

    public void setUser(WearhouseStuff u) 
    {
        w1=u; 
        filename=u.getUsername();
        initial();
    }
    
    public void chech_in_btn(ActionEvent event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss-dd:MM:yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        String tim = "Check in: "+dtf.format(now)+" "+ci_ta.getText() +"\n";
        ci_status.setText("Done");
        w1.saveLog(tim);
    }
    public void chech_out_btn(ActionEvent event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss-dd:MM:yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        String tim = "Check out: "+dtf.format(now)+" "+co_ta.getText() +"\n";
        co_status.setText("Done");
        w1.saveLog(tim);
    }
    public void report_btn(ActionEvent event){
        String tim = querry_type.getValue()+"\n"+querry_ta.getText()+"\n";
        w1.saveReport(tim);
        rp_status.setText("Done");
    }

    public ObservableList<Equipment> getEquipments()throws Exception{
        String temp="";
        String[] tempp;
        ObservableList<Equipment> ep=FXCollections.observableArrayList();
        try {
            File file= new File("src/files/equipment/stock.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                temp= sc.nextLine();
                tempp=temp.split(",");
                ep.add(new Equipment(tempp[0],tempp[1],Integer.parseInt(tempp[2])));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return ep;
    }
    public void c_add_onCLick(){
        Equipment neweq = new Equipment(c_name.getText(),
                                            c_brand.getText(),
                                            Integer.parseInt(c_quantity.getText()));
        table2.getItems().add(neweq);
    }

    public void c_remove_onClick(){
        ObservableList<Equipment> selectedRow, allep;
        allep=table2.getItems();
        selectedRow = table2.getSelectionModel().getSelectedItems();

        for(Equipment as: selectedRow )
            allep.remove(as);
    }
    public void c_repair_onClick(ActionEvent event)throws Exception{
        ObservableList<Equipment> selectedRow, allep;
        allep=table2.getItems();
        selectedRow = table2.getSelectionModel().getSelectedItems();
        String repairdata="";
        for(Equipment as: selectedRow)
        {
            repairdata+=as.getName()+","+as.getBrand()+","+String.valueOf(as.getQuantity())+"\n";
        }
        w1.saveRepair(repairdata);
    }
    public void c_data_save(ActionEvent event)throws Exception{
        ObservableList<Equipment> allep;
        allep=table2.getItems();
        String data="";
        for(Equipment as: allep)
        {
            data+=as.getName()+","+as.getBrand()+","+String.valueOf(as.getQuantity())+"\n";
        }
        w1.saveEquipment(data);
    }

    public void show_btn(ActionEvent event)throws Exception{
        v_ta.setText(w1.getRequest());
    }
    public void rq_save_btn(ActionEvent event)throws Exception{
        String rq_data = v_ta.getText();
        w1.saveRequest(rq_data);
        v_status.setText("Done");
    }
}
