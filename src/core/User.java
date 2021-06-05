package core;

import java.io.Serializable;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String username;
    protected String password;
    protected String usertype;
    protected String contact;
    protected String address;

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public User() {
        name = "";
        username = "";
        password = "";
        usertype = "";
        contact = "";
        address = "";
    }

    public User(String name, String username, String password, String usertype, String contact, String address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.contact = contact;
        this.address = address;
    }

    @Deprecated
    public String toString() {
        return name + " " + username + " " + password + " " + usertype + " " + contact + " " + address;
    }

    public boolean isUsername(String a) {
        if (a.equalsIgnoreCase(this.username))
            return true;
        else
            return false;
    }

    public boolean isPassword(String a) {
        if (a.equalsIgnoreCase(this.password))
            return true;
        else
            return false;
    }

    public boolean isUsertype(String a) {
        if (a.equalsIgnoreCase(this.usertype))
            return true;
        else
            return false;
    }

}