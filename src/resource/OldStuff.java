package resource;

import javafx.beans.property.SimpleStringProperty;

public class OldStuff {
    private SimpleStringProperty name;
    private SimpleStringProperty user_type;

    public OldStuff(String name, String user_type) {
        this.name= new SimpleStringProperty(name);
        this.user_type= new SimpleStringProperty(user_type);
    }

    public String getUser_type() {
        return user_type.get();
    }

    public String getName() {
        return name.get();
    }


}
