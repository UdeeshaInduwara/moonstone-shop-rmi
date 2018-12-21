package lk.ijse.jewelryshoprmi.generator;

import java.text.NumberFormat;

public class IDGenerator {
    public static String getNewID(String tableName,String columnName,String prefix) throws Exception{
        try {
            String lastID = IDController.getLastID(tableName, columnName);
            int id=Integer.parseInt(lastID.split(prefix)[1]);
            id++;
            NumberFormat numberFormat=NumberFormat.getNumberInstance();
            numberFormat.setMinimumIntegerDigits(3);
            numberFormat.setGroupingUsed(false);
            String newID = numberFormat.format(id);
            return prefix + newID;
        }catch (NullPointerException e){
            return prefix + "001";
        }
    }
}
