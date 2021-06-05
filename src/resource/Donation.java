package resource;

import java.io.File;
import java.io.FileWriter;

public class Donation {
    private String name;
    private String email;
    private String contact;
    private String payment_options;
    private String amount;

    public void setPayment_options(String payment_options) {
        this.payment_options = payment_options;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    @Override
    public String toString() {
        return "name " + name + "\nemail " + email + "\ncontact " + contact + "\npayment_options " + payment_options
                + "\namount" + amount;
    }

    public void saveData() {
        try {
            FileWriter fw = new FileWriter(new File("./src/files/donations/" + name + ".txt"));
            String str = this.toString();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
