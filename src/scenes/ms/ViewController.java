package scenes.ms;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ViewController {
    @FXML private Text text;
    
    public void setText(String s){
        text.setText(s);
    }
}
