package user;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import core.User;

public class TicketCollector extends User {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TicketCollector(String name, String username, String password, String usertype, String contact, String address) {
        super(name, username, password, usertype, contact, address);
    }

    public void saveLog(String s) {
        try {
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            DataOutputStream dos = null;
            File file = new File("src/files/users/ticketcollector/" + username + "_log.bin"); // !nnnn
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file, true);
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            dos.writeUTF(s + "\n");
            dos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void saveRequest(String s) {
        try {
            File file = new File("src/files/request.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write(s);
            out.newLine();
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void saveReport(String s) {
        try {
            File file = new File("src/files/report.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write(s);
            out.newLine();
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
