package model;
import java.sql.*;
public class BeanAdministrators {
    private int administratorId;
    private String administratorName;
    private String password;

    public BeanAdministrators(int administratorId, String administratorName, String password) {
        this.administratorId = administratorId;
        this.administratorName = administratorName;
        this.password = password;
    }
    public BeanAdministrators(int administratorId, String password) {
        this.administratorId = administratorId;
        this.password = password;
    }
    public BeanAdministrators() {
       
    }
    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public String getName() {
        return administratorName;
    }

    public void setName(String name) {
        this.administratorName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Administrator{" +
                "administratorId=" + administratorId +
                ", name='" + administratorName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
