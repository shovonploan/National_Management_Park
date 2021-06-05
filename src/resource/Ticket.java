package resource;

import java.io.*;

public class Ticket {
    private String name;
    private String email;
    private String contact;
    private String date;
    private int number_of_tickets;
    private String payment_options;
    private boolean guide;

    public void setGuide(boolean guide) {
        this.guide = guide;
    }

    public String getGuide() {
        if (guide == true)
            return "true";
        else
            return "false";
    }

    public void setPayment_options(String payment_options) {
        this.payment_options = payment_options;
    }

    public void setNumber_of_tickets(int number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }


    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "name " + name + "\nemail " + email + "\ncontact " + contact + "\ndate " + date + "\nnumber_of_tickets "
                + number_of_tickets + "\npayment_options " + payment_options + "\nguide " + this.getGuide();
    }

    public void saveData() {
        try {
            FileWriter fw = new FileWriter(new File("./src/files/tickets/"+name+".txt"));
            String str = this.toString();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
