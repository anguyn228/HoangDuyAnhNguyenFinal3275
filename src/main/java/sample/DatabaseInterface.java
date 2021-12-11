package sample;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseInterface {


    public void add(Customer cus) throws ClassNotFoundException, SQLException;
    public Customer edit(Customer cus, String custno) throws SQLException, ClassNotFoundException;
    public void delete(String custno) throws SQLException;
    public List<Customer> display() throws ClassNotFoundException, SQLException;

}
