package user;

import java.io.File;
import java.io.FileWriter;

public class Visitor {
    private String name;
    private String email;
    private String contact;
    private String feed;

    public String getName() {
        return name;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name=" + name + "\nemail=" + email + "\ncontact=" + contact + "\nfeed=" + feed;
    }

    public void saveData() {
        try {
            FileWriter fw = new FileWriter(new File("./src/files/users/visitor/" + name + ".txt"));
            String str = this.toString();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
