package lk.ijse.jewelryshoprmi.repository;

import lk.ijse.jewelryshoprmi.repository.custom.impl.CustomerRepoImpl;
import lk.ijse.jewelryshoprmi.repository.custom.impl.JewelryRepoImpl;

public class RepoFactory {
    public enum RepoTypes {
        JEWELRY,CUSTOMER
    }

    private static RepoFactory repoFactory;
    public static RepoFactory getInstance() {
        if (repoFactory == null) {
            repoFactory = new RepoFactory();
        }
        return repoFactory;
    }
    private RepoFactory(){
    }
    public <T extends SuperRepo> T getRepo(RepoTypes types) {
        switch (types) {
            case JEWELRY: return (T) new JewelryRepoImpl();
            case CUSTOMER: return (T) new CustomerRepoImpl();
            default: return null;
        }
    }
}
