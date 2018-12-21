package lk.ijse.jewelryshoprmi.observer;

import java.rmi.Remote;

public interface Observer extends Remote {
    public void update(String message) throws Exception;
}
