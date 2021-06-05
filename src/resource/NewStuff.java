package resource;

import javafx.beans.property.SimpleStringProperty;

public class NewStuff {
    private SimpleStringProperty name;

    public NewStuff(String name) {
        this.name=new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

}
