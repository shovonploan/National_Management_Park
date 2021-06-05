package resource;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Equipment {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SimpleStringProperty name;
    private SimpleStringProperty brand;
    private SimpleIntegerProperty quantity;

    
    public Equipment(String name, String brand, Integer quantity) {
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }
    public String getName() {
        return name.get();
    }

    public String getBrand() {
        return brand.get();
    }
}
