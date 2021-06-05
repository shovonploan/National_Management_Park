package scenes.login;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scenes.guardss.GuardController;
import scenes.guide.GuideController;
import scenes.janitor.JanitorController;
import scenes.landscapeprofessional.LandscapeprofessionalController;
import scenes.medicalstuff.MedicalstuffController;
import scenes.ms.MsController;
import scenes.mt.MtController;
import scenes.security.SecurityController;
import scenes.stallstuffmember.StallstuffmemberController;
import scenes.ticketcollector.TicketcollectorController;
import scenes.wearhousestuff.WearhousestuffController;
import user.Guard;
import user.Guide;
import user.Janitor;
import user.LandscapeProfessional;
import user.MaintenanceSupervisor;
import user.MaintenanceTechnician;
import user.MedicalStuff;
import user.Security;
import user.Stallstuffmember;
import user.TicketCollector;
import user.WearhouseStuff;

public class MainController implements Initializable {
    @FXML public ComboBox<String> user_type;
    @FXML private Label status;
    @FXML private TextField usernamefil;
    @FXML private PasswordField passwordfil;
    ObservableList<String> list1 = FXCollections.observableArrayList("Janitor", "LandscapeProfessional",
            "WearhouseStuff", "MaintenanceTechnician", "MaintenanceSupervisor", "TicketCollector", "Guard",
            "StallStuffMember", "Security", "Guide", "MedicalStuff");

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        user_type.setItems(list1);
    }

    public void visitorbtnclick(ActionEvent event) throws Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/visitor/visitor.fxml"));
        Scene scene = new Scene(root, 1040, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/visitor/visitor.css").toExternalForm());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Visitor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loginbtnclick(ActionEvent event) throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/files/users/users.bin"));
            User d;
            while (true) {
                d = (User) in.readObject();
                if (d.isUsername(usernamefil.getText())&&d.isPassword(passwordfil.getText())&&d.isUsertype(this.user_type.getValue()))
                {
                    System.out.println("Sucess");
                    String optionChosen = this.user_type.getValue();
                    if(optionChosen.equalsIgnoreCase("Janitor")){
                        Janitor j1= new Janitor(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logJanitor(j1);
                    }
                    if(optionChosen.equalsIgnoreCase("LandscapeProfessional")){ 
                        LandscapeProfessional l1= new LandscapeProfessional(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logLandscapeProfessional(l1);
                    }
                    if(optionChosen.equalsIgnoreCase("WearhouseStuff")){
                        WearhouseStuff w1= new WearhouseStuff(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logWearhouseStuff(w1);
                    }

                    if(optionChosen.equalsIgnoreCase("MaintenanceTechnician")){
                        MaintenanceTechnician m1= new MaintenanceTechnician(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logMaintenanceTechnician(m1);
                    }
                    if(optionChosen.equalsIgnoreCase("MaintenanceSupervisor")){
                        MaintenanceSupervisor m1= new MaintenanceSupervisor(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logMaintenanceSupervisor(m1);
                    }

                    if(optionChosen.equalsIgnoreCase("TicketCollector")){
                        TicketCollector t1= new TicketCollector(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logTicketCollector(t1);
                    }

                    if(optionChosen.equalsIgnoreCase("Guard")){
                        Guard g1= new Guard(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logGuard(g1);
                    }
                    if(optionChosen.equalsIgnoreCase("StallStuffMember")){
                        Stallstuffmember s1= new Stallstuffmember(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logStallStuffMember(s1);
                    }
                    if(optionChosen.equalsIgnoreCase("Guide")){
                        Guide g1= new Guide(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logGuide(g1);
                    }
                    if(optionChosen.equalsIgnoreCase("MedicalStuff")){
                        MedicalStuff m1= new MedicalStuff(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logMedicalStuff(m1);
                    }
                    if(optionChosen.equalsIgnoreCase("Security")){
                        Security s1= new Security(d.getName(),d.getUsername(),d.getPassword(),d.getUsertype(),d.getContact(),d.getContact());
                        logSecurity(s1);
                    }
                    break;
                }
                else{
                    status.setText("Not Found");
                }
                System.out.println(d.toString());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public void logGuard(Guard u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/guardss/guard.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Guard");
        GuardController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/guardss/guard.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logGuide(Guide u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/guide/guide.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Guide");
        GuideController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/guide/guide.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logJanitor(Janitor u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/janitor/janitor.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Janitor");
        JanitorController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/janitor/janitor.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logMedicalStuff(MedicalStuff u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/medicalstuff/medicalstuff.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Medical Stuff");
        MedicalstuffController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/medicalstuff/medicalstuff.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logStallStuffMember(Stallstuffmember u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/stallstuffmember/stallstuffmember.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Stall Stuff Member");
        StallstuffmemberController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/stallstuffmember/stallstuffmember.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logTicketCollector(TicketCollector u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/ticketcollector/ticketcollector.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Ticket Collector");
        TicketcollectorController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/ticketcollector/ticketcollector.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void logLandscapeProfessional(LandscapeProfessional u)throws  Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/landscapeprofessional/landscapeprofessional.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Landscape Professional");
        LandscapeprofessionalController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/landscapeprofessional/landscapeprofessional.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void  logWearhouseStuff(WearhouseStuff u) throws Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/wearhousestuff/wearhousestuff.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Wearhouse Stuff");
        WearhousestuffController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/wearhousestuff/wearhousestuff.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void  logMaintenanceTechnician(MaintenanceTechnician u) throws Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/mt/mt.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Maintenance Technician");
        MtController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/mt/mt.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void  logMaintenanceSupervisor(MaintenanceSupervisor u) throws Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/ms/ms.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Maintenance Supervisor");
        MsController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/ms/ms.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void  logSecurity(Security u) throws Exception {
        Stage stage = (Stage) usernamefil.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/security/security.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Security");
        SecurityController newcon = loader.getController();
        newcon.setUser(u);
        Scene scene = new Scene(root, 1050, 650);
        scene.getStylesheets().add(getClass().getResource("/scenes/security/security.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
